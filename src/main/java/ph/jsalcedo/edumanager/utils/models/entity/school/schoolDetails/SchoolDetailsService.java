package ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.data.models.entity.school.enrollmentStatus.EnrollmentStatus;
import ph.jsalcedo.edumanager.data.models.entity.school.enrollmentStatus.EnrollmentStatusService;
import ph.jsalcedo.edumanager.utils.StringFormatter;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SchoolDetailsService {
//    private final SchoolDetailsRepository schoolDetailsRepository;
//    private final EnrollmentStatusService enrollmentStatusService;
//
////    public Set<EnrollmentStatus> enrollmentStatusList(Long id){
////            Optional<SchoolDetails> schoolDetails = schoolDetailsRepository.findById(id);
////            if()
////            return enrollmentStatusService.findAllBySchoolDetails(id);
////    }
//
//    public void addEnrollmentStatus(String schoolName, EnrollmentStatus enrollmentStatus){
//        SchoolDetails schoolDetails = findBySchoolName(schoolName);
//        Set<EnrollmentStatus> enrollmentStatusSet = enrollmentStatusService.findAllBySchoolDetails(schoolDetails);
//
//        Iterator<EnrollmentStatus> iterator = enrollmentStatusSet.iterator();
//        while(iterator.hasNext()){
//            if(iterator.next().getEnrollmentStatus().equalsIgnoreCase(enrollmentStatus.getEnrollmentStatus())){
//                throw new IllegalStateException("Enrollment status already exists!");
//            }
//        }
//
//        schoolDetails.getEnrollmentStatuses().add(enrollmentStatus);
//        enrollmentStatusService.addEnrollmentStatus(schoolDetails.getEnrollmentStatuses());
//    }
//
//    public SchoolDetails findBySchoolName(String schoolName){
//        Optional<SchoolDetails> school =  schoolDetailsRepository.findBySchoolName(schoolName);
//        if(school.isEmpty()){
//            throw new IllegalStateException("School does not exists!");
//        }
//
//        return school.get();
//    }
//
//    public void generateDefaultEnrollmentStatus(SchoolDetails schoolDetails){
//        ph.jsalcedo.edumanager.data.models.enums.EnrollmentStatus[] enrollmentStatus = ph.jsalcedo.edumanager.data.models.enums.EnrollmentStatus.values();
//
//        for (ph.jsalcedo.edumanager.data.models.enums.EnrollmentStatus status : enrollmentStatus) {
//            schoolDetails.getEnrollmentStatuses().add(new EnrollmentStatus(
//                    StringFormatter.screamingSnakeCaseToCapitalize(status.name()),
//                    status.getDescription(),
//                    schoolDetails));
//        }
//
//        enrollmentStatusService.addEnrollmentStatus(schoolDetails.getEnrollmentStatuses(), schoolDetails);
//    }


}
