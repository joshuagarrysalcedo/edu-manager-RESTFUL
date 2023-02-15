package ph.jsalcedo.edumanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.jsalcedo.edumanager.entity.school.SchoolDetails;

import java.util.Optional;

@Repository
public interface SchoolDetailsRepository extends JpaRepository<SchoolDetails, Long> {


    Optional<SchoolDetails> findBySchoolName(String schoolName);



}
