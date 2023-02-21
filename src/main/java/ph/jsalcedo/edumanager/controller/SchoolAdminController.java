package ph.jsalcedo.edumanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ph.jsalcedo.edumanager.entity.appuser.AppUser;
import ph.jsalcedo.edumanager.entity.appuser.AppUserService;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.SchoolService;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.school.curriculum.CurriculumService;
import ph.jsalcedo.edumanager.entity.student.Student;

import java.util.List;

/**
 * <h1> SchoolAdminController</h1>
 * <section>
 * <h3>Description</h3>
 * <ul>
 *   <li>????</li>
 *   <li>????</li>
 *   <li>????</li>
 * </ul>
 * </section>
 *
 * @author Joshua Salcedo
 * @version 1.0(INCOMPLETE)
 * @created 22/02/2023
 */
@RestController
@RequestMapping("/api/v1/school-admin")
@AllArgsConstructor
@CrossOrigin
public class SchoolAdminController {


    private final AppUserService appUserService;
    private final CurriculumService curriculumService;
    private final SchoolService schoolService;
    @GetMapping(value = "/{id}/{institutionName}/schools", produces = MediaType.APPLICATION_JSON_VALUE)
    List<School> getAllSchools(
            @PathVariable("id") Long appUserID,
            @PathVariable("institutionName") String institutionName
    ){
        System.out.println("Finding App user with id of " + appUserID + "and Institution name of " + institutionName);
        return appUserService.findByIdAndInstitutionInstitutionName(appUserID, institutionName).getInstitution().getSchools();
    }

    @GetMapping(value = "/{id}/{institutionName}/{schoolName}/curriculums")
    List<Curriculum> getAllCurriculum(@PathVariable("id") Long appUserID,
                                      @PathVariable("institutionName") String institutionName,
                                      @PathVariable("schoolName") String schoolName){
        AppUser appUser = appUserService.findByIdAndInstitutionInstitutionName(appUserID, institutionName);
        schoolName = schoolName.replace("-", " ");
        School school = schoolService.findSchoolByInstitutionAndSchoolName(appUser.getInstitution(), schoolName);
        return school.getCurriculum();
    }

    @GetMapping(value = "/{id}/{institutionName}/{schoolName}/students")
    List<Student> getAllStudents(@PathVariable("id") Long appUserID,
                                 @PathVariable("institutionName") String institutionName,
                                 @PathVariable("schoolName") String schoolName){
        AppUser appUser = appUserService.findByIdAndInstitutionInstitutionName(appUserID, institutionName);
        schoolName = schoolName.replace("-", " ");
        School school = schoolService.findSchoolByInstitutionAndSchoolName(appUser.getInstitution(), schoolName);
        return school.getStudents();
    }





}
