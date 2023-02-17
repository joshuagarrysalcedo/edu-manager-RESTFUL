package ph.jsalcedo.edumanager.entity.institution;

import org.springframework.transaction.annotation.Transactional;
import ph.jsalcedo.edumanager.entity.school.School;

import java.util.List;

/**
 * <h1>InstitutionService Interface</h1>
 * <h3>Description</h3>
 * <ul>
 * <li>This interface provides the business logic for managing the {@link Institution} Entity.</li>
 * <li>It defines methods for creating, retrieving, updating, and deleting institution records.</li>
 * <li>This interface is intended to be implemented by {@link InstitutionServiceImpl}
 *  that provide specific functionality for working with institutions
 *   such as storing them in a database or retrieving them from an API.</li>
 *   <li>Note that this interface does not define the properties or fields of an institution.
 *   Instead, it defines the methods for working with those properties and fields once they have been defined.</li>
 *</ul>
 *</section>
 * @created 15/02/2023
 * @author Joshua Salcedo
 * @version 1.0 (Complete)
 */
public interface InstitutionService {
    @Transactional
    Institution create(Institution institution);
    int count();
    List<Institution> all();
    @Transactional
    void deleteInstitution(Long id);
    @Transactional
    void update(Institution institution);
    Institution getInstitution(Long institutionID);
    @Transactional
    Institution addSchool(Long id, School school);

    @Transactional
    void deleteSchool(Long id , School school);
}
