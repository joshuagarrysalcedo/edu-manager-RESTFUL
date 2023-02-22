package ph.jsalcedo.edumanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ph.jsalcedo.edumanager.entity.appuser.AppUser;
import ph.jsalcedo.edumanager.entity.appuser.AppUserService;
import ph.jsalcedo.edumanager.entity.registerrequest.CurriculumRemoveRequest;
import ph.jsalcedo.edumanager.entity.registerrequest.EnrollCourseRequest;
import ph.jsalcedo.edumanager.entity.registerrequest.RegisterCurriculumRequest;
import ph.jsalcedo.edumanager.entity.registerrequest.RegisterStudentRequest;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.SchoolService;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.school.curriculum.CurriculumService;
import ph.jsalcedo.edumanager.entity.student.Student;
import ph.jsalcedo.edumanager.entity.student.StudentService;
import ph.jsalcedo.edumanager.utils.models.enums.AppUserRole;

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
    private final StudentService studentService;
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

    @PostMapping(value = "/{id}/{institutionName}/{schoolName}/student", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student registerStudent(
            @PathVariable("id") Long appUserID,
            @PathVariable("institutionName") String institutionName,
            @PathVariable("schoolName") String schoolName,
            @RequestBody RegisterStudentRequest studentRequest){
        AppUser appUser = appUserService.findByIdAndInstitutionInstitutionName(appUserID, institutionName);
        if(appUser.getAppUserRole() == AppUserRole.STUDENT || appUser.getAppUserRole() == AppUserRole.TEACHER){
            throw new RuntimeException(appUser.getAppUserRole() + " can't register student");
        }
        System.out.println("Student  " + studentRequest);

        schoolName = schoolName.replace("-", " ");
        School school = schoolService.findSchoolByInstitutionAndSchoolName(appUser.getInstitution(), schoolName);
        Student student = studentService.registerStudent(studentRequest);

        school = schoolService.addStudent(school.getId(), student);
        return studentService.findBySchoolAndStudentName(school, student.getName());
    }

    @PostMapping(value = "/{id}/{institutionName}/{schoolName}/curriculum", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Curriculum registerCurriculum(@PathVariable("id") Long appUserID,
                                         @PathVariable("institutionName") String institutionName,
                                         @PathVariable("schoolName") String schoolName,
                                         @RequestBody RegisterCurriculumRequest request){
        AppUser appUser = appUserService.findByIdAndInstitutionInstitutionName(appUserID, institutionName);
        schoolName = schoolName.replace("-", " ");

        School school = schoolService.findSchoolByInstitutionAndSchoolName(appUser.getInstitution(), schoolName);

        Curriculum curriculum = curriculumService.registerCurriculum(request);
        school = schoolService.addCurriculum(school.getId(), curriculum);
        return curriculumService.findBySchoolAndCurriculumName(school, curriculum.getCurriculumName());
    }

    @PutMapping(value = "/{id}/{institutionName}/{schoolName}/students/enroll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Curriculum enrollStudent(@PathVariable("id") Long appUserID,
                                @PathVariable("institutionName") String institutionName,
                                @PathVariable("schoolName") String schoolName,
                                @RequestBody EnrollCourseRequest enrollCourseRequest){

//        AppUser appUser = appUserService.findByIdAndInstitutionInstitutionName(appUserID, institutionName);
//        schoolName = schoolName.replace("-", " ");
//
//        schoolName = schoolName.replace("-", " ");
//        School school = schoolService.findSchoolByInstitutionAndSchoolName(appUser.getInstitution(), schoolName);
//
//
//        Student student = studentService.findByStudentID(enrollCourseRequest.getStudentID());

       Curriculum curriculum =   curriculumService.enrollStudent(enrollCourseRequest);

        System.out.println("Curriculum Name: " + curriculum.getCurriculumName());
        System.out.println("Enrolled Students : " + curriculum.getStudentList());

//        Curriculum curriculum = curriculumService.addStudent(enrollCourseRequest.getCurriculumID(), student);

        return curriculum;
    }

    @DeleteMapping(value = "/{id}/{institutionName}/{schoolName}/curriculums/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public School deleteCurriculum(@PathVariable("id") Long appUserID,
                                       @PathVariable("institutionName") String institutionName,
                                       @PathVariable("schoolName") String schoolName,
                                       @RequestBody CurriculumRemoveRequest request){
        AppUser appUser = appUserService.findByIdAndInstitutionInstitutionName(appUserID, institutionName);
        schoolName = schoolName.replace("-", " ");

        School school = schoolService.findSchoolByInstitutionAndSchoolName(appUser.getInstitution(), schoolName);
        Curriculum curriculum = curriculumService.findBySchoolAndCurriculumName(school, request.getCurriculumName());
        return schoolService.deleteCurriculum(request.getSchoolID(), curriculum);
    }

    //TODO implement deleteStudent

}
