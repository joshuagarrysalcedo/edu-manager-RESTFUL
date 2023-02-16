package ph.jsalcedo.edumanager.entity.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.jsalcedo.edumanager.entity.institution.Institution;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    boolean existsBySchoolNameAndAndInstitution(String schoolName, Institution institution);
    List<School> findAllByInstitution(Institution institution);

    Optional<School> findFirstByOrderByIdDesc();

    Optional<School> findByInstitutionAndSchoolName(Institution institution, String schoolName);
}
