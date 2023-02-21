package ph.jsalcedo.edumanager.entity.school.curriculum;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.student.Student;
import ph.jsalcedo.edumanager.entity.student.StudentRepository;
import ph.jsalcedo.edumanager.entity.student.StudentService;
import ph.jsalcedo.edumanager.exceptions.exception.CustomEntityNotFoundException;
import ph.jsalcedo.edumanager.exceptions.exception.DuplicateNameException;
import ph.jsalcedo.edumanager.exceptions.exception.EntityNotOwnedException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * <h1> CurriculumServiceImpl</h1>
 * <section>
 * <h3>Description</h3>
 * <ul>
 *   <li>????</li>
 *   <li>????</li>
 *   <li>????</li>
 * </ul>
 * </section>
 *
 * @author Joshua Salcedo
 * @version 1.0(INCOMPLETE)
 * @created 17/02/2023
 */
@Service
@AllArgsConstructor
public class CurriculumServiceImpl implements CurriculumService{
    private final CurriculumRepository curriculumRepository;
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    /**
     * <h1>findAllCurriculumBySchool</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param school
     * @return
     * @author Joshua Salcedo
     * @created 17/02/2023 - 3:59 pm
     */
    @Override
    public List<Curriculum> findAllCurriculumBySchool(School school) {
        return curriculumRepository.findAllBySchool(school);
    }

    /**
     * <h1>findBySchoolAndCurriculumName</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param school
     * @param curriculumName
     * @return
     * @author Joshua Salcedo
     * @created 17/02/2023 - 4:41 pm
     */
    @Override
    public Curriculum findBySchoolAndCurriculumName(School school, String curriculumName) {
        Optional<Curriculum> optionalCurriculum = curriculumRepository.findCurriculumBySchoolAndCurriculumName(school, curriculumName);
        if(optionalCurriculum.isEmpty())
            throw new CustomEntityNotFoundException("Curriculum name", curriculumName);
        return optionalCurriculum.get();
    }

    /**
     * <h1>existsById</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param id
     * @return
     * @author Joshua Salcedo
     * @created 17/02/2023 - 4:56 pm
     */
    @Override
    public boolean existsById(Long id) {
       return curriculumRepository.existsById(id);
    }

    /**
     * <h1>deleteCurriculumByID</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param id
     * @author Joshua Salcedo
     * @created 17/02/2023 - 5:00 pm
     */
    @Override
    public void deleteCurriculumByID(Long id) {
        curriculumRepository.deleteCurriculumById(id);
    }

    /**
     * <h1>findByID</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param l
     * @return
     * @author Joshua Salcedo
     * @created 18/02/2023 - 1:05 am
     */
    @Override
    public Curriculum findByID(Long l) {
        Optional<Curriculum> optionalCurriculum = curriculumRepository.findById(l);
        if(optionalCurriculum.isEmpty())
            throw new CustomEntityNotFoundException("Curriculum ID ", l);
        return optionalCurriculum.get();
    }

    /**
     * <h1>addStudent</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param ID
     * @param student
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 12:12 am
     */
    @Override
    public Curriculum addStudent(Long ID, Student student) {
        Curriculum curriculum = findByID(ID);
        List<Student> studentList = studentService.findAllStudentsByCurriculum(curriculum);

        Student finalStudent = student;
        studentList.forEach(s-> {

            //Only add student if both curriculum and student have the same school
            if(!curriculum.getSchool().getId().equals(finalStudent.getSchool().getId())){
                throw new EntityNotOwnedException(curriculum.getSchool().getSchoolName(), finalStudent.getName().toString(), "Student ID", finalStudent.getId());
            }
            //Dont add duplicate Student!
            if(s.getId().equals(finalStudent.getId())){
                throw new DuplicateNameException(finalStudent.getName().toString());
            }
        });

        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        if(optionalStudent.isPresent()){
            optionalStudent.get().setCurriculum(curriculum);
            curriculum.getStudentList().add(optionalStudent.get());
            return curriculumRepository.saveAndFlush(curriculum);
        }



//        student.setCurriculum(curriculum);
//        curriculum.getStudentList().add(student);
//
//
//        return curriculumRepository.saveAndFlush(curriculum);
        return null;

    }

    /**
     * <h1>removeStudent</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param ID
     * @param student
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 12:12 am
     */
    @Override
    public Curriculum removeStudent(Long ID, Student student) {
        Curriculum curriculum = findByID(ID);

        curriculum.getStudentList().removeIf(e->
            e.getName().equals(student.getName())
        );
        student.setCurriculum(null);

        return curriculumRepository.saveAndFlush(curriculum);
    }
}
