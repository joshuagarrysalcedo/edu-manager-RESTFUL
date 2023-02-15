package ph.jsalcedo.edumanager.entity.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.jsalcedo.edumanager.entity.institution.Institution;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    boolean existsBySchoolNameAndAndInstitution(String schoolName, Institution institution);
    List<School> findAllByInstitution(Institution institution);
}
