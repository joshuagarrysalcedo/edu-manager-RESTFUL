package ph.jsalcedo.edumanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ph.jsalcedo.edumanager.entity.SchoolDetails;
import ph.jsalcedo.edumanager.entity.EnrollmentStatus;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EnrollmentStatusRepository extends JpaRepository<EnrollmentStatus, Long> {


    Set<EnrollmentStatus> findAllBySchoolDetails(SchoolDetails schoolDetails);

    boolean existsByEnrollmentStatusAndAndSchoolDetails(EnrollmentStatus enrollmentStatus, SchoolDetails schoolDetails);

    @Transactional
    void deleteByEnrollmentStatus(String enrollmentStatus);

    int countAllBySchoolDetails(SchoolDetails schoolDetails);
    Optional<EnrollmentStatus> findByEnrollmentStatus(String enrollmentStatus);

    Set<EnrollmentStatus> findAllBySchoolDetailsSchoolName(String schoolName);

    EnrollmentStatus findEnrollmentStatusByEnrollmentStatus(String status);

    @Transactional
    void deleteByEnrollmentStatusAndSchoolDetails(String enrollmentStatus, SchoolDetails schoolDetails);

    Optional<EnrollmentStatus> findEnrollmentStatusByEnrollmentStatusAndSchoolDetails(String enrollmentStatus, SchoolDetails schoolDetails);

}
