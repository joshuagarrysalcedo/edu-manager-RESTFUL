package ph.jsalcedo.edumanager.entity.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.student.Student;
import ph.jsalcedo.edumanager.utils.models.person.Name;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentBySchoolAndName(School school, Name name);
    List<Student> findAllBySchool(School school);

    List<Student> findAllByCurriculum(Curriculum curriculum);

    Optional<Student> findStudentById(Long id);

    Optional<Student> findStudentByCurriculumAndName(Curriculum curriculum, Name name);
}
