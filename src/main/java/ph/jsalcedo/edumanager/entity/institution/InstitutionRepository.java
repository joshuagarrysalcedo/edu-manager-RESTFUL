package ph.jsalcedo.edumanager.entity.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1> InstitutionRepository interface</h1>
 * <section>
 * <h3>Description</h3>
 * <ul>
 *   <li>This interface defines the contract for managing institutional data in a persistent storage.</li>
 *   <li>It extends the JpaRepository interface, providing basic <b>CRUD</b> operations, and adds custom methods for querying</li>
 *   <li>Note that this interface is annotated with {@link org.springframework.stereotype.Repository @Repository}
 *   indicating that it is a Spring Data repository. Spring Data repositories are a convenient way to access data
 *   stored in a relational database, providing a high level of abstraction and reducing boilerplate code.</li>
 * </ul>
 * </section>
 *
 * @author Joshua Salcedo
 * @version 1.0(Complete)
 * @created 17/02/2023
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.stereotype.Repository
 * @see Institution
 */
@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    /**
     * <h1>Exists by Name and ID</h1>
     * @param name The name of the Institution to search for.
     * @param id The id of the Institution to search for.
     * @return true if an Institution exists with the given name and id, false otherwise.
     * @author Joshua Salcedo
     * @created 17/02/2023 - 3:11 am
     */
    boolean existsByInstitutionNameAndId(String name, Long id);

    /**
     * <h1>Get the Latest entity</h1>
     * @return The first Institution entity found, ordered by id in descending order, or an empty Optional if no such entity exists.
     * @author Joshua Salcedo
     * @created 17/02/2023 - 3:11 am
     */
    Optional<Institution> findFirstByOrderByIdDesc();


    /**
     * <h1>Find Institution by Name and ID</h1>
     * @param name The name of the Institution to search for.
     * @param id The id of the Institution to search for.
     * @return an Optional of the Institution entity with the given name and id, or an empty Optional if no such entity exists.
     * @author Joshua Salcedo
     * @created 17/02/2023 - 3:11 am
     */
    Optional<Institution> findByInstitutionNameAndId(String name, Long id);

}
