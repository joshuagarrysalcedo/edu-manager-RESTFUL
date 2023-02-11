package ph.jsalcedo.edumanager.utils.models.entity.school.enrollmentStatus;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ph.jsalcedo.edumanager.entity.school.SchoolDetails;
import ph.jsalcedo.edumanager.entity.school.EnrollmentStatus;
import ph.jsalcedo.edumanager.repository.EnrollmentStatusRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class EnrollmentStatusService {

    private final EnrollmentStatusRepository enrollmentStatusRepository;


    public Set<EnrollmentStatus> findAllBySchoolDetailsSchoolName(String schoolName){
        return enrollmentStatusRepository.findAllBySchoolDetailsSchoolName(schoolName);
    }

    public Set<EnrollmentStatus> findAllBySchoolDetails(SchoolDetails schoolDetails){
        return enrollmentStatusRepository.findAllBySchoolDetails(schoolDetails);
    }

    @Transactional
    public void updateEnrollmentStatus(EnrollmentStatus enrollmentStatus){
        EnrollmentStatus status = enrollmentStatusRepository.findById(enrollmentStatus.getId())
                .orElseThrow(()-> new IllegalStateException("Enrollment status does not exists!"));

        if(enrollmentStatus.getEnrollmentStatus() != null && enrollmentStatus.getEnrollmentStatus().length() > 0 && !Objects.equals(status.getEnrollmentStatus(), enrollmentStatus.getEnrollmentStatus())){
            Optional<EnrollmentStatus> findByStatus = enrollmentStatusRepository.findByEnrollmentStatus(enrollmentStatus.getEnrollmentStatus());
            if(findByStatus.isPresent()){
                throw new IllegalStateException("Status already exists!!");
            }
            status.setEnrollmentStatus(enrollmentStatus.getEnrollmentStatus());
        }

        if(status.getDescription() != null && enrollmentStatus.getDescription().length() > 0 && !Objects.equals(status.getDescription(), enrollmentStatus.getDescription())){
            status.setDescription(enrollmentStatus.getDescription());
        }
    }

    public void addEnrollmentStatus(EnrollmentStatus enrollmentStatus){
        enrollmentStatusRepository.save(enrollmentStatus);
    }

    public void addEnrollmentStatus(Set<EnrollmentStatus> enrollmentStatus, SchoolDetails schoolDetails){
        for (EnrollmentStatus status : enrollmentStatus) {
            if (doesItExists(status, schoolDetails)) {
                System.out.println("Enrollment status already exists!");
            }else{
                enrollmentStatusRepository.save(status);
            }
        }
    }

    public boolean doesItExists(EnrollmentStatus enrollmentStatus, SchoolDetails schoolDetails){
        return enrollmentStatusRepository.existsByEnrollmentStatusAndAndSchoolDetails(enrollmentStatus, schoolDetails);
    }

//    public List<EnrollmentStatus> getEnrollmentStatuses(){return enrollmentStatusRepository.findAll();}
//
//    public void addEnrollmentStatus(EnrollmentStatus enrollmentStatus, Long id){
//        Iterator<EnrollmentStatus> iterator = findAllBySchoolDetails(id).iterator();
//        while(iterator.hasNext()){
//            if(iterator.next().getEnrollmentStatus().equalsIgnoreCase(enrollmentStatus.getEnrollmentStatus())){
//                throw new IllegalStateException("Enrollment status already exists!");
//            }
//        }
//        enrollmentStatusRepository.save(enrollmentStatus);
//    }
//
//    public void deleteEnrollmentStatuses(Long id){
//       boolean exists = enrollmentStatusRepository.existsById(id);
//       if(!exists){
//           throw new IllegalStateException("Enrollment Status does not exists!");
//       }
//       enrollmentStatusRepository.deleteById(id);
//    }
//
//    @Transactional
//    public void updateEnrollmentStatus(Long id, String enrollmentStatus, String description){
//        EnrollmentStatus status = enrollmentStatusRepository.findById(id)
//                .orElseThrow(()-> new IllegalStateException("Enrollment status does not exists!"));
//
//        if(enrollmentStatus != null && enrollmentStatus.length() > 0 && !Objects.equals(status.getEnrollmentStatus(), enrollmentStatus)){
//            Optional<EnrollmentStatus> findByStatus = enrollmentStatusRepository.findByEnrollmentStatus(enrollmentStatus);
//            if(findByStatus.isPresent()){
//                throw new IllegalStateException("Status taken!");
//            }
//            status.setEnrollmentStatus(enrollmentStatus);
//        }
//
//        if(description != null && description.length() > 0 && !Objects.equals(status.getDescription(), description)){
//            status.setDescription(description);
//        }
//    }
//
//    public Set<EnrollmentStatus> findAllBySchoolDetails(Long schoolDetails_id){
//        return enrollmentStatusRepository.findAllBySchoolDetails(schoolDetails_id);
//    }
//
//    public void addEnrollmentStatus(Set<EnrollmentStatus> enrollmentStatusList, Long id){
//        for (EnrollmentStatus enrollmentStatus : enrollmentStatusList) {
//
//        }
//
//    }
}


