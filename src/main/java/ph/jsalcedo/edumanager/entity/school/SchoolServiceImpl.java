


package ph.jsalcedo.edumanager.entity.school;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.institution.InstitutionRepository;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.school.curriculum.CurriculumService;
import ph.jsalcedo.edumanager.entity.student.Student;
import ph.jsalcedo.edumanager.entity.student.StudentRepository;
import ph.jsalcedo.edumanager.entity.student.StudentService;
import ph.jsalcedo.edumanager.exceptions.exception.CustomEntityNotFoundException;
import ph.jsalcedo.edumanager.exceptions.exception.CustomInvalidNameException;
import ph.jsalcedo.edumanager.exceptions.exception.DuplicateNameException;
import ph.jsalcedo.edumanager.exceptions.exception.EntityNotOwnedException;
import ph.jsalcedo.edumanager.utils.NameChecker;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private final CurriculumService curriculumService;
    private final InstitutionRepository institutionRepository;

    private final StudentService studentService;
    private final StudentRepository studentRepository;





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
    public void updateCurriculum(School school) {
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

        Optional<School> schoolOptional = schoolRepository.findByInstitutionAndSchoolNameEqualsIgnoreCase(institution,name);
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
     * <h1>findLatestSchoolByInstitution</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param institution
     * @return
     * @author Joshua Salcedo
     * @created 17/02/2023 - 5:37 am
     * TODO
     */

    @Override
    public School findLatestSchoolByInstitution(Institution institution) {
       return schoolRepository.findFirstByInstitutionOrderById(institution);
    }

    /**
     * <h1>findLatestSchool</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 17/02/2023 - 5:37 am
     * TODO
     */
    @Override
    public School findLatestSchool() {
       Optional<School> optional =  schoolRepository.findFirstByOrderByIdDesc();
       if(optional.isEmpty())
           throw new RuntimeException("NO school is in the record");
       return optional.get();
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
     * TODO
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
    public void deleteSchoolById(Long id) {
        schoolRepository.deleteById(id);
    }

    /**
     * <h1>getAllCurriculum</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 17/02/2023 - 5:37 am
     * TODO
     */
    @Override
    public List<Curriculum> getAllCurriculum() {
        return null;
    }

    /**
     * <h1>addCurriculum</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param schoolID
     * @param curriculum
     * @author Joshua Salcedo
     * @created 17/02/2023 - 5:37 am
     * TODO
     */
    @Override
    public School addCurriculum(Long schoolID, Curriculum curriculum) {
        School school = findSchoolByID(schoolID);
        List<Curriculum> curriculumList = curriculumService.findAllCurriculumBySchool(school);
        curriculumList.forEach(e->{ // TODO: 16/02/2023 We need to refactor this! Need to make sure that
            if(e.getCurriculumName() .equalsIgnoreCase(curriculum.getCurriculumName()))
                throw new DuplicateNameException(e.getCurriculumName());
        });

        curriculum.setSchool(school);
        school.getCurriculum().add(curriculum);
        return schoolRepository.saveAndFlush(school);
    }

    /**
     * <h1>deleteCurriculum</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param schoolID
     * @param curriculum
     * @author Joshua Salcedo
     * @created 17/02/2023 - 5:37 am
     * TODO
     */
    @Override
    public School deleteCurriculum(Long schoolID, Curriculum curriculum) {
        School school= getSchool(schoolID);

        System.out.println("School name : " + school.getSchoolName());
        System.out.println("Has a total of curriculum:  " + school.getCurriculum().size() );
        AtomicBoolean isFound = new AtomicBoolean(false);

        school.getCurriculum().forEach((e)->{
            if(e.getCurriculumName().equalsIgnoreCase(curriculum.getCurriculumName()))
                isFound.set(true);
        });
        if(!isFound.get())
            throw new EntityNotOwnedException("School", "Curriculum", "ID",curriculum.getId());


        curriculum.getStudentList().forEach(e->{
            e.setCurriculum(null);
        });
        curriculum.setSchool(null);

        school.getCurriculum().removeIf(e->
                e.getCurriculumName().equalsIgnoreCase(curriculum.getCurriculumName()));
//        curriculumService.deleteCurriculumByID(curriculum.getId());





         return schoolRepository.saveAndFlush(school);


    }

    /**
     * <h1>updateCurriculum</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param schoolID
     * @param curriculum
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 1:04 am
     */
    @Override
    public School updateCurriculum(Long schoolID, Curriculum curriculum) {
       School school = deleteCurriculum(schoolID, curriculum);
       school = addCurriculum(schoolID, curriculum);

       return schoolRepository.saveAndFlush(school);
    }

    /**
     * <h1>updateSchool</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param schoolID
     * @param curriculum
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 12:58 am
     */


    /**
     * <h1>addStudent</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param schoolID
     * @param student
     * @return
     * @author Joshua Salcedo
     * @created 21/02/2023 - 11:36 pm
     */
    @Override
    public School addStudent(Long schoolID, Student student) {
        School school = findSchoolByID(schoolID);

        List<Student> studentList = studentService.findAllStudentsBySchool(school);

        studentList.forEach(e->{
            if(e.getName().equals(student.getName())){
                throw new DuplicateNameException(student.getName().toString());
            }
        });

        student.setSchool(school);
        school.getStudents().add(student);
        return schoolRepository.saveAndFlush(school);
    }




    /**
     * <h1>deleteStudent</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param schoolID
     * @param student
     * @return
     * @author Joshua Salcedo
     * @created 21/02/2023 - 11:36 pm
     */
    @Override
    public School deleteStudent(Long schoolID, Student student) {
        School school = getSchool(schoolID);
        student.setSchool(null);
        school.getStudents().removeIf((e->
                e.getName().equals(student.getName())
                ));


        return schoolRepository.saveAndFlush(school);
    }

    /**
     * <h1>findSchoolByInstitutionAndSchoolName</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param institution
     * @param schoolName
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 5:50 am
     */
    @Override
    public School findSchoolByInstitutionAndSchoolName(Institution institution, String schoolName) {
        Optional<School> optionalSchool =  schoolRepository.findByInstitutionAndSchoolNameEqualsIgnoreCase(institution, schoolName);
        if(optionalSchool.isEmpty())
            throw new EntityNotOwnedException(institution.getInstitutionName(), "School", "School Name", schoolName);
        return optionalSchool.get();
    }


    public School getSchool(Long schoolID) {
        Optional<School> optionalSchool = schoolRepository.findById(schoolID);
        if(optionalSchool.isEmpty())
            throw new CustomEntityNotFoundException("School ID", schoolID);

        return optionalSchool.get();
    }


}
