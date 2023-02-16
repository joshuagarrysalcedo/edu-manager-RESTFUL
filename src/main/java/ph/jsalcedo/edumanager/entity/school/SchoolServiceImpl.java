


package ph.jsalcedo.edumanager.entity.school;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.exceptions.exception.CustomEntityNotFoundException;
import ph.jsalcedo.edumanager.exceptions.exception.CustomInvalidNameException;
import ph.jsalcedo.edumanager.exceptions.exception.DuplicateNameException;
import ph.jsalcedo.edumanager.utils.NameChecker;

import java.util.List;
import java.util.Optional;

/**
 * <h1> SCHOOL SERVICE</h1>
 * <dl>
 * <dt>PURPOSE</dt>
 * <dd><b>Implementation for all Business Rules for School Entity </b></dd>
 * <dt>TYPE</dt>
 * <dd><b>SERVICE CLASS</b></dd>
 * </dl>
 * <b>Note:</b> *
 * <h2>@created 16/02/2023</h2>
 * <h2>@author Joshua Salcedo</h2>
 */
@Service
@AllArgsConstructor
public class SchoolServiceImpl implements SchoolService{
    private final static String ENTITY_NOT_FOUND_EXCEPTION_MESSAGE = "Entity with {%s : %s} is not found";
    private final SchoolRepository schoolRepository;









    /**
     * <h1>Update School</h1>
     * <b>Note:</b>
     * this method has certain if statements that will make the method throw an exception
     * <dl>
     *     <dt>Conditions</dt>
     *     <dd>School name with the same Institution must be <b>unique!</b></dd>
     *     <dd>School name must be <b>valid!</b></dd>
     * </dl>
     *
     * @author Joshua Salcedo
     * @created 16/02/2023 - 2:58 pm
     */
    @Override
    public void  updateSchool(School school) {
        List<School> schoolList = schoolRepository.findAllByInstitution(school.getInstitution());

        for(School s : schoolList){
            if(NameChecker.isNameInvalid(school.getSchoolName()))
                throw new CustomInvalidNameException(school.getSchoolName());
            // CHECKS THAT NO DUPLICATE SCHOOL NAME!
            if(NameChecker.isNameDuplicate(s.getSchoolName(), school.getSchoolName())
                    && !s.getId().equals(school.getId()))
                throw new DuplicateNameException(school.getSchoolName());
        }
        schoolRepository.save(school);
    }













    /**
     * <h1>RETRIEVE School by Institution</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return List of all schools by their institution
     * @author Joshua Salcedo
     * @created 16/02/2023 - 9:46 pm
     */
    @Override
    public List<School> findSchoolsByInstitution(Institution institution) {
       return schoolRepository.findAllByInstitution(institution);
    }
















    /**
     * <h1>FIND school by ID AND INSTITUTION</h1>
     *
     * @author Joshua Salcedo
     * @created 16/02/2023 - 9:45 pm
     */
    @Override
    public School findSchoolByInstitutionAndName(Institution institution, String name) {

        Optional<School> schoolOptional = schoolRepository.findByInstitutionAndSchoolName(institution,name);
        if(schoolOptional.isEmpty())
            throw new EntityNotFoundException("NOT FOUND");
        return schoolOptional.get();
    }














    /**
     * <h1>Find School by ID</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param id must exist in the db otherwise it will throw
     * @return a school according to their ID based on the Schema
     * @author Joshua Salcedo
     * @created 16/02/2023 - 2:49 pm
     */
    @Override
    public School findSchoolByID(Long id) {
        Optional<School> schoolOptional = schoolRepository.findById(id);
        if(schoolOptional.isEmpty())
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_EXCEPTION_MESSAGE, "School ID", Long.toString(id)));
        return schoolOptional.get();
    }

    /**
     * <h1>findAllByInstitution</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param institution
     * @return
     * @author Joshua Salcedo
     * @created 17/02/2023 - 12:43 am
     */
    @Override
    public List<School> findAllByInstitution(Institution institution) {
       return schoolRepository.findAllByInstitution(institution);
    }

    /**
     * <h1>deleteById</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param id
     * @author Joshua Salcedo
     * @created 17/02/2023 - 12:44 am
     */
    @Override
    public void deleteById(Long id) {
        schoolRepository.deleteById(id);
    }


}
