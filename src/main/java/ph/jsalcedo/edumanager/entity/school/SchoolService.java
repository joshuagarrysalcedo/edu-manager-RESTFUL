package ph.jsalcedo.edumanager.entity.school;

import org.springframework.transaction.annotation.Transactional;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.student.Student;

import java.util.List;

/**
 * <h1> ${NAME}</h1>
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
public interface SchoolService {

    void updateCurriculum(School school);


    List<School> findSchoolsByInstitution(Institution institution);
    School findSchoolByInstitutionAndName(Institution institution , String name);

    School findSchoolByID(Long id);

    School findLatestSchoolByInstitution(Institution institution);
    School findLatestSchool();



    List<School> findAllByInstitution(Institution institution);

    void deleteSchoolById(Long id);



    List<Curriculum> getAllCurriculum();

    @Transactional

    School addCurriculum(Long schoolID, Curriculum curriculum);
    @Transactional
    School deleteCurriculum(Long schoolID, Curriculum curriculum);
    @Transactional
    School updateCurriculum(Long schoolID, Curriculum curriculum);

    @Transactional
    School addStudent(Long schoolID, Student student);
    @Transactional
    School deleteStudent(Long schoolID, Student student);

    School findSchoolByInstitutionAndSchoolName(Institution institution, String schoolName);
}
