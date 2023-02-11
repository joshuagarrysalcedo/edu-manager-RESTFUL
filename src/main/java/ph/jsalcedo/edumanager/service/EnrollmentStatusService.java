package ph.jsalcedo.edumanager.data.models.entity.school.enrollmentStatus;

import ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails.SchoolDetails;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EnrollmentStatusDao {
    void updateEnrollmentStatus(EnrollmentStatus enrollmentStatus , String StatusName, String description);
    void addEnrollmentStatus(SchoolDetails schoolDetails, EnrollmentStatus enrollmentStatus);
    void deleteEnrollmentStatus(SchoolDetails schoolDetails, EnrollmentStatus enrollmentStatus);

    EnrollmentStatus getEnrollmentStatus(SchoolDetails schoolDetails, String statusName);
    Set<EnrollmentStatus> getAllEnrollmentStatus(SchoolDetails details);
}
