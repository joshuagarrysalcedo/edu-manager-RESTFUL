package ph.jsalcedo.edumanager.service;

import ph.jsalcedo.edumanager.entity.SchoolDetails;
import ph.jsalcedo.edumanager.entity.EnrollmentStatus;

import java.util.Optional;
import java.util.Set;

public interface EnrollmentStatusService {
    void updateEnrollmentStatus(EnrollmentStatus enrollmentStatus , String StatusName, String description);
    void addEnrollmentStatus(SchoolDetails schoolDetails, EnrollmentStatus enrollmentStatus);
    void deleteEnrollmentStatus(SchoolDetails schoolDetails, EnrollmentStatus enrollmentStatus);
    void deleteByID(Long id);

    Optional<EnrollmentStatus> getEnrollmentStatus(SchoolDetails schoolDetails, String statusName);
    Set<EnrollmentStatus> getAllEnrollmentStatus(SchoolDetails details);
}
