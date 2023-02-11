package ph.jsalcedo.edumanager.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
