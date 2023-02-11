package ph.jsalcedo.edumanager.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.SchoolDetails;
import ph.jsalcedo.edumanager.repository.EnrollmentStatusRepository;
import ph.jsalcedo.edumanager.repository.SchoolDetailsRepository;
import ph.jsalcedo.edumanager.entity.EnrollmentStatus;
import ph.jsalcedo.edumanager.service.EnrollmentStatusService;
import ph.jsalcedo.edumanager.utils.models.enums.ErrorMessage;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class EnrollmentStatusServiceImpl implements EnrollmentStatusService {
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
        if(schoolDetails.getEnrollmentStatuses().stream().anyMatch(u-> u.getEnrollmentStatus().equalsIgnoreCase(enrollmentStatus.getEnrollmentStatus()))){
            throw new IllegalStateException(ErrorMessage.Constants.NOT_FOUND_MESSAGE);
        }
        enrollmentStatus.setSchoolDetails(schoolDetails);
        schoolDetails.getEnrollmentStatuses().add(enrollmentStatus);
        schoolDetailsRepository.save(schoolDetails);
//                enrollmentStatusRepository.saveAll(schoolDetails.getEnrollmentStatuses());
//               enrollmentStatusRepository.save(enrollmentStatus);

            }


    @Override
    public void deleteEnrollmentStatus(SchoolDetails schoolDetails, EnrollmentStatus enrollmentStatus) {
        if(schoolDetails.getEnrollmentStatuses().stream().noneMatch(u-> u.getEnrollmentStatus().equalsIgnoreCase(enrollmentStatus.getEnrollmentStatus()))){
            throw new IllegalStateException(ErrorMessage.Constants.NOT_FOUND_MESSAGE);
        }
//      schoolDetails.getEnrollmentStatuses().stream()
//              .filter(u->u.getEnrollmentStatus().equalsIgnoreCase(enrollmentStatus.getEnrollmentStatus()))
//              .forEach(u-> {
//                  schoolDetails.getEnrollmentStatuses().remove(u);
//              });
//       schoolDetailsRepository.save(schoolDetails);
//        enrollmentStatusRepository.deleteByEnrollmentStatusAndSchoolDetails(enrollmentStatus.getEnrollmentStatus(), schoolDetails);
//        schoolDetails.getEnrollmentStatuses().remove(enrollmentStatus);
//        schoolDetails.getEnrollmentStatuses().removeIf(u-> u.getEnrollmentStatus().equalsIgnoreCase(enrollmentStatus.getEnrollmentStatus()));
        schoolDetails.getEnrollmentStatuses().remove(enrollmentStatus);
//        System.out.println("Size " + schoolDetails.getEnrollmentStatuses().size());
        schoolDetails.getEnrollmentStatuses().forEach(e-> System.out.println(e.getEnrollmentStatus()));
        enrollmentStatus.setSchoolDetails(null);
//        enrollmentStatusRepository.deleteById(enrollmentStatus.getId());
        enrollmentStatusRepository.delete(enrollmentStatus);
//        schoolDetailsRepository.save(schoolDetails);
    }

    @Override
    public void deleteByID(Long id) {
        enrollmentStatusRepository.deleteById(id);
    }

    @Override
    public Optional<EnrollmentStatus> getEnrollmentStatus(SchoolDetails schoolDetails, String statusName) {
        return enrollmentStatusRepository.findEnrollmentStatusByEnrollmentStatusAndSchoolDetails(statusName,schoolDetails);
    }

    @Override
    public Set<EnrollmentStatus> getAllEnrollmentStatus(SchoolDetails details) {
        return null;
    }
}
