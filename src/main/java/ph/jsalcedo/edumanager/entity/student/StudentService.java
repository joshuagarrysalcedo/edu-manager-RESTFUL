package ph.jsalcedo.edumanager.entity.student;

import ph.jsalcedo.edumanager.entity.auth.AuthenticationResponse;
import ph.jsalcedo.edumanager.entity.auth.RegisterRequest;
import ph.jsalcedo.edumanager.entity.registerrequest.RegisterStudentRequest;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.utils.models.person.Name;

import java.util.List;

/**
 * <h1> StudentService</h1>
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
 * @created 21/02/2023
 */
public interface StudentService {

    List<Student> findAllStudentsBySchool(School school);
    Student findBySchoolAndStudentName(School school, Name name);

    Student findByStudentID(Long ID);


    List<Student> findAllStudentsByCurriculum(Curriculum curriculum);

    Student registerStudent(RegisterStudentRequest request);
}
