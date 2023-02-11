package ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolDetailsRepository extends JpaRepository<SchoolDetails, Long> {


    Optional<SchoolDetails> findBySchoolName(String schoolName);


}
