package ph.jsalcedo.edumanager.api.school.details;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ph.jsalcedo.edumanager.data.models.entity.school.enrollmentStatus.EnrollmentStatus;
import ph.jsalcedo.edumanager.data.models.entity.school.enrollmentStatus.EnrollmentStatusService;
import ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails.SchoolDetailsService;
import ph.jsalcedo.edumanager.data.models.entity.student.Student;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RestController
@RequestMapping("api/v1/school/details")
@AllArgsConstructor
@CrossOrigin
public class SchoolDetailsController {
//    private final SchoolDetailsService schoolDetailsService;
//    private final EnrollmentStatusService enrollmentStatusService;
//    @PostMapping(value = "/{schoolName}/enrollment-status/add")
//    public String addEnrollmentStatus(@PathVariable("schoolName")String schoolName, @RequestBody EnrollmentStatus enrollmentStatus){
//        schoolDetailsService.addEnrollmentStatus(schoolName, enrollmentStatus);
//        return "Enrollment status has been added";
//    }
//
//    @GetMapping(value = "/{schoolName}/enrollment-status/all", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Set<EnrollmentStatus> getAllEnrollmentStatus(@PathVariable("schoolName") String schoolName){
////        schoolDetailsService.generateDefaultEnrollmentStatus(schoolDetailsService.findBySchoolName(schoolName));
//        return schoolDetailsService.findBySchoolName(schoolName).getEnrollmentStatuses();
//    }
//
//    @PostMapping(value = "/{schoolName}/enrollment-status/default-enrollment-status")
//    public void loadDefaultEnrollmentStatus(@PathVariable("schoolName")String schoolName){
//         schoolDetailsService.generateDefaultEnrollmentStatus(schoolDetailsService.findBySchoolName(schoolName));
//    }
}
