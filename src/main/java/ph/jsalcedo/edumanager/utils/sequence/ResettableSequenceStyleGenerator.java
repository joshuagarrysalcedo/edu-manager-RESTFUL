package ph.jsalcedo.edumanager.utils.sequence;

import org.hibernate.HibernateException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**
 * <h1> ResettableSequenceStyleGenerator </h1>
 * <p></p>
 * <b>Note:</b>
 * <h2>@created 16/02/2023 - 5:47 pm</h2>
 * <h2>@author Joshua Salcedo</h2>
 */
public class ResettableSequenceStyleGenerator extends SequenceStyleGenerator {

    private static int cycle = 0;
    private int instanceCycle = cycle;

    private Type configure_type = null;
    private Properties configure_params = null;
    private ServiceRegistry configure_serviceRegistry = null;

    private Database registerExportables_database = null;

    private SqlStringGenerationContext initialize_context = null;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {

        configure_type = type;
        configure_params = params;
        configure_serviceRegistry = serviceRegistry;

        super.configure(type, params, serviceRegistry);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        if (instanceCycle != cycle) {
            super.configure(configure_type, configure_params, configure_serviceRegistry);
            super.registerExportables(registerExportables_database);
            super.initialize(initialize_context);
            instanceCycle = cycle;
        }

        return (Serializable) super.generate(session, object);
    }

    @Override
    public void registerExportables(Database database) {

        registerExportables_database = database;

        super.registerExportables(database);
    }

    @Override
    public void initialize(SqlStringGenerationContext context) {

        initialize_context = context;

        super.initialize(context);
    }

    public static void resetAllInstances() {
        cycle++;
    }
}