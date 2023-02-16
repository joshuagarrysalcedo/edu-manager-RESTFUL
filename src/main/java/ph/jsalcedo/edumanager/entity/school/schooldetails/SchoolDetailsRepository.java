package ph.jsalcedo.edumanager.entity.school.schooldetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.jsalcedo.edumanager.entity.school.School;

import java.util.Optional;

@Repository
public interface SchoolDetailsRepository extends JpaRepository<SchoolDetails, Long> {


    Optional<SchoolDetails> findBySchoolName(String schoolName);




}
