


package ph.jsalcedo.edumanager.entity.school;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.exception.DuplicateSchoolNameException;

import java.util.List;
import java.util.Optional;

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
     * @param school
     * @author Joshua Salcedo
     * @created 16/02/2023 - 2:58 pm
     */
    @Override
    public void  updateSchool(School school) {
        List<School> schoolList = schoolRepository.findAllByInstitution(school.getInstitution());

        for(School s : schoolList){

            // CHECKS THAT NO DUPLICATE SCHOOL NAME!
            if(isSchoolNameDuplicate(s.getSchoolName(), school.getSchoolName())
                    && !s.getId().equals(school.getId()))
                throw new DuplicateSchoolNameException(school.getSchoolName());
        }
    }


    /**
     * <h1>Duplicate Name Checker</h1>
     * <p>This Utility method ensures that the program has the same standards for what is considered a duplicate String!.</p>
     * <p>this is a helper method to compare the Strings</p>
     * <b>@returns</b>
     *<dl>
     *     <dt>True</dt>
     *     <dd>'  xx' and 'XX'</dd>
     *     <dd>'x_x' and 'xx'</dd>
     *     <dd>'xx' and 'x x'</dd>
     *     <dt>False</dt>
     *     <dd>'xy' and 'xx'</dd>
     *     <dd>'x school' and `x university`</dd>
     *</dl>
     *
     * @author Joshua Salcedo
     * @created 16/02/2023 - 3:11 pm
     */
    private boolean isSchoolNameDuplicate(String schoolName, String newSchoolName) {
        schoolName = schoolName.replace("[^a-zA-Z0-9]", "").trim();
        newSchoolName = newSchoolName.replace("[^a-zA-Z0-9]", "").trim();
        System.out.println("Old School Name " + schoolName);
        System.out.println("New School Name " + newSchoolName);
        return schoolName.equalsIgnoreCase(newSchoolName);
    }

    @Override
    public void deleteSchool(Long schoolID) {

    }

    @Override
    public void findSchoolByInstitution(Institution institution) {

    }

    @Override
    public School findSchoolByInstitutionAndName(Institution institution, String name) {

        Optional<School> schoolOptional = schoolRepository.findByInstitutionAndSchoolName(institution,name);
        if(schoolOptional.isEmpty())
            throw new EntityNotFoundException("NOT FOUND");
        return schoolOptional.get();
    }

    /**
     * <h1>findSchoolByID</h1>
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


}
