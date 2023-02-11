package ph.jsalcedo.edumanager.data.models.entity.school.enrollmentStatus;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails.SchoolDetails;
import ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails.SchoolDetailsRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class EnrollmentStatusDaoImpl implements EnrollmentStatusDao{
    private final EnrollmentStatusRepository enrollmentStatusRepository;
    private final SchoolDetailsRepository schoolDetailsRepository;


    @Override
    public void updateEnrollmentStatus(EnrollmentStatus enrollmentStatus, String statusName, String description) {
        if(!(statusName == null || statusName.length() > 0) ){
            enrollmentStatus.setEnrollmentStatus(statusName);
        }

        if(!(description == null || description.length() > 0) ){
            enrollmentStatus.setDescription(description);
        }
        if(enrollmentStatusRepository.existsById(enrollmentStatus.getId())){
            enrollmentStatusRepository.save(enrollmentStatus);
        }

    }

    @Override
    public void addEnrollmentStatus(SchoolDetails schoolDetails, EnrollmentStatus enrollmentStatus) {
//            if(schoolDetails
//                    .getEnrollmentStatuses()
//                    .stream()
//                    .noneMatch(u-> u.getEnrollmentStatus().equalsIgnoreCase(enrollmentStatus.getEnrollmentStatus()))){
        if(schoolDetails.getEnrollmentStatuses().stream().noneMatch(u-> u.getEnrollmentStatus().equalsIgnoreCase(enrollmentStatus.getEnrollmentStatus()))){
            enrollmentStatus.setSchoolDetails(schoolDetails);
            schoolDetails.getEnrollmentStatuses().add(enrollmentStatus);

//                enrollmentStatusRepository.saveAll(schoolDetails.getEnrollmentStatuses());
//               enrollmentStatusRepository.save(enrollmentStatus);
            schoolDetailsRepository.save(schoolDetails);
        }else{
            throw new IllegalStateException("Enrollment status not found");
        }

            }


    @Override
    public void deleteEnrollmentStatus(SchoolDetails schoolDetails, EnrollmentStatus enrollmentStatus) {
        if(schoolDetails.getEnrollmentStatuses().stream().anyMatch(u-> u.getEnrollmentStatus().equals(enrollmentStatus.getEnrollmentStatus()))){
           schoolDetails.getEnrollmentStatuses().remove(enrollmentStatus);
           schoolDetailsRepository.save(schoolDetails);
           enrollmentStatusRepository.deleteById(enrollmentStatus.getId());
        }

    }

    @Override
    public EnrollmentStatus getEnrollmentStatus(SchoolDetails schoolDetails, String statusName) {
        return null;
    }

    @Override
    public Set<EnrollmentStatus> getAllEnrollmentStatus(SchoolDetails details) {
        return null;
    }
}
