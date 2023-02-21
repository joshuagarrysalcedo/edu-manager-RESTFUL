package ph.jsalcedo.edumanager.entity;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ph.jsalcedo.edumanager.entity.appuser.AppUser;
import ph.jsalcedo.edumanager.entity.appuser.AppUserRepository;
import ph.jsalcedo.edumanager.entity.appuser.AppUserService;
import ph.jsalcedo.edumanager.entity.auth.AuthenticationService;
import ph.jsalcedo.edumanager.entity.auth.RegisterRequest;
import ph.jsalcedo.edumanager.entity.institution.InstitutionRepository;
import ph.jsalcedo.edumanager.entity.institution.InstitutionService;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.SchoolRepository;
import ph.jsalcedo.edumanager.entity.school.SchoolService;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.school.curriculum.CurriculumRepository;
import ph.jsalcedo.edumanager.entity.school.curriculum.CurriculumService;
import ph.jsalcedo.edumanager.entity.student.Student;
import ph.jsalcedo.edumanager.utils.models.enums.AppUserRole;
import ph.jsalcedo.edumanager.utils.models.person.Address;
import ph.jsalcedo.edumanager.utils.models.person.Name;

import java.util.Random;

/**
 * <h1> AppUserTest</h1>
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
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppUserTest {

    private final SchoolService schoolService;

    private final InstitutionService institutionService;
    private final InstitutionRepository institutionRepository;
    private final SchoolRepository schoolRepository;
    private final Faker faker;
    private final CurriculumRepository curriculumRepository;
    private final CurriculumService curriculumService;
    private final AppUserService appUserService;
    private final AuthenticationService authenticationService;
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserTest(SchoolService schoolService, InstitutionService institutionService, InstitutionRepository institutionRepository, SchoolRepository schoolRepository, Faker faker, CurriculumRepository curriculumRepository, CurriculumService curriculumService, AppUserService appUserService, AuthenticationService authenticationService, AppUserRepository appUserRepository) {
        this.schoolService = schoolService;
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
        this.schoolRepository = schoolRepository;
        this.faker = faker;
        this.curriculumRepository = curriculumRepository;
        this.curriculumService = curriculumService;
        this.appUserService = appUserService;
        this.authenticationService = authenticationService;
        this.appUserRepository = appUserRepository;
    }

    @BeforeEach
    void initialize(){
        appUserRepository.deleteAll();
    }
    @Test
    void shouldSeeInstitutionFromAppUser(){
        Random random = new Random();
        String companyName = faker.company().name();
        RegisterRequest request = RegisterRequest.builder()
                .name(Name.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .build())
                .companyName(companyName)
                .country("Philippines")
                .email("joshuagarrysalcedo@gmail.com")
                .locked(false)
                .enabled(true)
                .appUserRole(AppUserRole.USER_ADMIN)
                .password("1234")
                .build();
        authenticationService.register(request);

        AppUser appUser = appUserService.getAppuser("joshuagarrysalcedo@gmail.com");
        Assertions.assertNotNull(appUser);


        System.out.println(appUser.getInstitution());

        System.out.println("Asserting that Schools is 0");
        System.out.println("School size : " + appUser.getInstitution().getSchools().size());
        Assertions.assertEquals(0, appUser.getInstitution().getSchools().size());


        School school = School.builder()
                .schoolName(faker.university().name())
                .build();
        institutionService.addSchool(appUser.getInstitution().getId(), school);
        appUser = appUserService.getAppuser("joshuagarrysalcedo@gmail.com");

        System.out.println("Asserting that School is 1");
        System.out.println("School size : " + appUser.getInstitution().getSchools().size());
        System.out.println(appUser.getInstitution().getSchools());
        school =  appUser.getInstitution().getSchools().get(0);
        Assertions.assertEquals(1, appUser.getInstitution().getSchools().size());


        System.out.println("Asserting that " + school.getSchoolName() + " has curriculum size of 0");
        System.out.println("Curriculum size : " + school.getCurriculum().size());
        Assertions.assertEquals(0, school.getCurriculum().size());

        System.out.println("Asserting that " + school.getSchoolName() + " has student size of 0");
        System.out.println("Student size : " + school.getStudents().size());
        Assertions.assertEquals(0, school.getStudents().size());


        String[] curriculumNames = {
                "Orthodontics",
                "Organology",
                "Crystallography",
                "Philosophy of Space and Time",
                "Physical Cosmology"
        };

        System.out.println("Adding curriculum :");
        for (String curriculumName : curriculumNames) {
            Curriculum curriculum = Curriculum.builder()
                    .curriculumName(curriculumName)
                    .build();

            school = schoolService.addCurriculum(school.getId(), curriculum);
        }

        appUser = appUserService.getAppuser("joshuagarrysalcedo@gmail.com");
        school = appUser.getInstitution().getSchools().get(0);


        System.out.println("Asserting that " + school.getSchoolName() + " has curriculum size of 5");
        System.out.println("Curriculum size : " + school.getCurriculum().size());
        school.getCurriculum().forEach(System.out::println);
        Assertions.assertEquals(5, school.getCurriculum().size());

        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("Adding Students");

        for(int i = 0; i < 100; i++){
            Student student = Student.builder()
                    .name(
                            Name
                                    .builder()
                                    .firstName(faker.name().firstName())
                                    .middleName(faker.name().lastName())
                                    .lastName(faker.name().lastName())
                                    .build()
                    ).address(
                            Address
                                    .builder("Cagayan de Oro City", "Philippines")
                                    .build()
                    )
                    .build();
            school = schoolService.addStudent(school.getId(), student);
        }


        appUser = appUserService.getAppuser("joshuagarrysalcedo@gmail.com");
        school = appUser.getInstitution().getSchools().get(0);

        System.out.println("Asserting that " + school.getSchoolName() + " has student size of 100");
        System.out.println("Student size : " + school.getStudents().size());
        school.getStudents().forEach(System.out::println);
        Assertions.assertEquals(100, school.getStudents().size());

        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("Enrolling Curriculum to Students");
        int size = school.getCurriculum().size();

        for(int i = 0; i < school.getStudents().size(); i++){
            int index = random.nextInt(size);
            Curriculum curriculum = curriculumService.findByID(school.getCurriculum().get(index).getId());
            curriculumService.addStudent(curriculum.getId(), school.getStudents().get(i));

        }
        appUser = appUserService.getAppuser("joshuagarrysalcedo@gmail.com");
        school = appUser.getInstitution().getSchools().get(0);



        System.out.println("Asserting that Enrolled Students is not empty");
        school.getCurriculum().forEach(e->{
            System.out.println("Curriculum " + e.getCurriculumName() + "Enrolled Students Size: " + e.getStudentList().size());
            Assertions.assertTrue(e.getStudentList().size() > 0);
        });

        school.getStudents().forEach(System.out::println);

    }

}
