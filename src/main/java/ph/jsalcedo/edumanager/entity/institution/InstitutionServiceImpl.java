package ph.jsalcedo.edumanager.entity.institution;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.query.InvalidJpaQueryMethodException;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.SchoolService;
import ph.jsalcedo.edumanager.exceptions.exception.*;
import ph.jsalcedo.edumanager.utils.NameChecker;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * <h1> <code>InstitutionServiceImpl</code></h1>
 * <ul>
 *   <li><b>Service</b> class for managing institutions in the application.</li>
 *   <li>This class provides methods for performing <b>CRUD</b> (Create, Read, Update, Delete)x operations on Institution entities, as well as additional methods for querying and managing institutions.</li>
 *   <li>The class implementation of {@link InstitutionService} with a data access layer to persist and retrieve {@link Institution} data.</li>
 *   <li>This service class encapsulates <b>business logic</b> related to {@link Institution} and provides a centralized location for managing institution-related operations. It is intended to be used by other parts of the application (such as controllers or other services) to perform operations on Institution entities.</li>
 * </ul>
 * <p>
 *     <b>Note:</b> Implementation is not yet complete
 * </p>
 * @created 16/02/2023
 * @author Joshua Salcedo
 * @see InstitutionServiceImpl#create(Institution) <b>CREATE</b> institution
 * @see InstitutionServiceImpl#count() <b>TOTAL</b> institutions
 * @see InstitutionServiceImpl#all()  <b>ALL</b> institutions
 * @see InstitutionServiceImpl#deleteInstitution(Long)  <b>DELETE</b>  institution
 * @see InstitutionServiceImpl#update(Institution)   <b>UPDATE</b>  institution
 * @see InstitutionServiceImpl#getInstitution(Long)   <b>GET</b>  institution
 * @version 1.0 (Complete)
 */
@Service
@AllArgsConstructor
@Qualifier("institutionService")
public class InstitutionServiceImpl implements InstitutionService{



    /**
     The repository for managing persistence of {@link Institution} entities.
     */
    private final InstitutionRepository institutionRepository;

    /**

     This field is an instance of the {@link SchoolService} class and is used to access its methods.
     It is used by this class to perform operations on School entities.
     */
    private final SchoolService schoolService;






    /**
     * <h1>CREATE institution</h1>
     * <p>This method adds a new {@link Institution} entity to the system.
     * The name of the institution is validated against a set of business rules encapsulated in the {@link NameChecker} class.
     * If the name is invalid, a {@link CustomInvalidNameException} is thrown.
     * If the name is valid, the {@link Institution} entity is persisted to the data layer using the {@link InstitutionRepository} object.
     * If there is an error accessing the data layer, a {@link DataAccessException} is thrown.</p>
     * <b>Note:</b> TODO: 16/02/2023 we need to add more conditions!
     * <dl>
     *     <dt>Conditions</dt>
     *     <dd>Name must be valid</dd>
     *     <dd>
     *     </dd>
     * </dl>
     * @exception CustomInvalidNameException   will throw if the {@link Institution#getInstitutionName() <code>institutionName</code>} attribute is invalid.
     * @param institution instance of the {@link Institution} entity to be persisted in the database.
     * @author Joshua Salcedo
     * @created 16/02/2023 - 10:22 pm
     * @see NameChecker#isNameInvalid(String) <code>isNameInvalid(String)</code>
     *
     */
    @Override
    public void create(@NotNull Institution institution){
        if((NameChecker.isNameInvalid((institution.getInstitutionName())))){
            throw new CustomInvalidNameException(institution.getInstitutionName());
        }
        institutionRepository.save(institution);
    }







    /**
     * <h1>COUNT all institutions</h1>
     * Returns the total number of Institution entities in the database by calling the <code>findAll() </code>method on the <code>institutionRepository</code>
     * and getting the size of the resulting list.This allows us to count all the number of instances without the reference of the repository
     * <p>
     *     <b>Note:</b> this method is considered complete!
     * </p>
     * @return the total number of Institution entities in the database.
     * @author Joshua Salcedo
     * @created 16/02/2023 - 10:21 pm
     * @see InstitutionRepository
     */
    @Override
    public int count() {
        return institutionRepository.findAll().size();
    }








    /**
     * <h1>GET all institutions</h1>
     *
     * @return List ALL institutions
     * @author Joshua Salcedo
     * @created 16/02/2023 - 10:20 pm
     */
    @Override
    public List<Institution> all() {
        return institutionRepository.findAll();
    }






    /**
     * <h1>DELETE institution!</h1>
     * Deletes the Institution entity with the specified ID from the database.
     * If an institution with the specified ID does not exist, a CustomEntityNotFoundException is thrown
     * @param id the {@link Institution#getId() ID} of the Institution entity to be deleted
     * @exception CustomInvalidNameException if an Institution entity with the specified ID does not exist
     * @author Joshua Salcedo
     * @created 16/02/2023 - 10:17 pm
     *
     */
    @Override
    public void deleteInstitution(Long id) {
        if(!institutionRepository.existsById(id))
            throw new CustomEntityNotFoundException("Institution ID" ,id);

        institutionRepository.deleteById(id);
    }









    /**
     * <h1>UPDATE the institution</h1>
     * Updates an existing Institution entity in the system with the provided data.
     * Before saving the changes to the database, we need to validates first that the changes are valid and meet certain conditions.
     * <b>Note:</b>
     * <dl>
     *     <dt>Conditions</dt>
     *     <dd>Institution name must be valid!</dd>
     *
     *     <dd>@// TODO: 16/02/2023  </dd>
     * </dl>
     *
     * @param institution the {@link Institution}  entity with the updated data
     * @author Joshua Salcedo
     * @created 16/02/2023 - 9:53 pm
     * @throws CustomInvalidNameException if the updated Institution name is invalid
     * @throws DataAccessException if there is an error accessing the data layer
     * @see NameChecker
     * @since 1.0
     *
     */
    @Override
    public void update(Institution institution) {
        if(NameChecker.isNameInvalid(institution.getInstitutionName()))
            throw new CustomInvalidNameException(institution.getInstitutionName());

        institutionRepository.save(institution);
    }








    /**
     * <h1>GET institution</h1>
     * Retrieves the <code>Institution</code> entity with the specified ID from the database.
     *
     * @param id the {@link Institution#getId() ID}  of the Institution entity to retrieve.
     * @return Institution the {@link Institution}  entity with the specified ID.
     * @throws  InstitutionNotFoundException if an Institution entity with the specified ID does not exist in the database
     * @throws IllegalArgumentException if the specified ID is null.
     * @throws DataAccessException if there is an error accessing the database.
     * @author Joshua Salcedo
     * @created 16/02/2023 - 9:52 pm
     */
    @Override
    public Institution getInstitution(Long id) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if(optionalInstitution.isEmpty())
            throw new InstitutionNotFoundException(id);
        return optionalInstitution.get();
    }






    /**
     * <h1>ADD school!</h1>
     * <ul>
     *     <li>Adds a school to the list of schools owned by a given institution.</li>
     *     <li>Before adding the school, we need to check if every field is valid</li>
     * <b>Note:</b> // TODO: 16/02/2023 We need to add more conditions!
     *
     * @param school the {@link School school} to be added in the list
     * @param institutionID the {@link Institution#getId() ID}  of the institution that owns the list of schools
     * @throws DuplicateNameException if there is already a school in the list with the same name as the new school. <b>Note:School name may be duplicated as long as they don't belong to the same institution</b>
     *
     * @throws IllegalArgumentException if the institution does not exist
     *
     * @throws NullPointerException if the school or institutionID is null
     * @author Joshua Salcedo
     * @created 16/02/2023 - 9:49 pm
     */
    @Override
    public void addSchool(Long institutionID, School school) {
        Institution institution = getInstitution(institutionID);
        List<School> schools = schoolService.findAllByInstitution(institution);
        schools.forEach(e->{ // TODO: 16/02/2023 We need to refactor this! Need to make sure that
            if(e.getSchoolName() .equalsIgnoreCase(school.getSchoolName()))
                throw new DuplicateNameException(e.getSchoolName());
        });

        school.setInstitution(institution);
        institution.getSchools().add(school);
        institutionRepository.save(institution);

    }








    /**
     * <h1>DELETE school</h1>
     * <ul>
     *   <li>This method deletes a school from the database by removing its relationship to an {@link Institution} and making the institution null.</li>
     *   <li>It takes in the ID of the institution and the {@link School} to be deleted as parameters.</li>
     *   <li>If the institution ID does not exist in the database, it throws a {@link CustomEntityNotFoundException}.</li>
     *   <li>If the school is not associated with the institution specified by the ID, it throws an {@link EntityNotOwnedException}.</li>
     * </ul>
     * @param id The {@link Institution#getId() ID} of the institution to which the school is associated.
     * @param school The  {@link School school} to be deleted.
     * @throws CustomEntityNotFoundException If the institution ID does not exist in the database.
     * @throws EntityNotOwnedException If the school is not associated with the institution specified by the ID.
     * @author Joshua Salcedo
     * @created 16/02/2023 - 9:30 pm
     * @see Institution
     * @see School
     * @see CustomEntityNotFoundException
     * @see EntityNotOwnedException
     */
    @Override
    public void deleteSchool(@NotNull Long id, @NotNull School school) {
        if(!institutionRepository.existsById(id))
            throw new CustomEntityNotFoundException("Institution ID" ,id);
        Institution institution = getInstitution(id);

        school.setInstitution(null);

        AtomicBoolean isFound = new AtomicBoolean(false);
        institution.getSchools().forEach((e)->{
            if(e.getSchoolName().equalsIgnoreCase(school.getSchoolName()))
                isFound.set(true);
        });
        if(!isFound.get())
            throw new EntityNotOwnedException("Institution", "School", "schoolID",school.getId());
        schoolService.deleteById(school.getId());
        institutionRepository.save(institution);
    }


}
