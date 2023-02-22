package ph.jsalcedo.edumanager.configuration;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ph.jsalcedo.edumanager.entity.appuser.AppUser;
import ph.jsalcedo.edumanager.entity.auth.AuthenticationService;
import ph.jsalcedo.edumanager.entity.auth.RegisterRequest;
import ph.jsalcedo.edumanager.entity.appuser.AppUserRepository;
import ph.jsalcedo.edumanager.entity.appuser.AppUserService;
import ph.jsalcedo.edumanager.entity.institution.InstitutionService;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.SchoolService;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.school.curriculum.CurriculumService;
import ph.jsalcedo.edumanager.entity.student.Student;
import ph.jsalcedo.edumanager.utils.models.enums.AppUserRole;
import ph.jsalcedo.edumanager.utils.models.person.Address;
import ph.jsalcedo.edumanager.utils.models.person.Name;

import java.util.Random;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final AppUserRepository repository;
    private final AppUserService userService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired Faker faker,
                                               @Autowired Random ran ,
                                               @Autowired AuthenticationService authenticationService,
                                               @Autowired InstitutionService institutionService,
                                               @Autowired SchoolService schoolService,
                                               @Autowired AppUserService appUserService,
                                               @Autowired CurriculumService curriculumService){
        return args->{
            Random random = new Random();
            for(int i = 0; i < 5; i++){
                String companyName = faker.company().name();


                RegisterRequest request = RegisterRequest.builder()
                        .name(Name.builder()
                                .firstName(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .build())
                        .companyName(companyName)
                        .country("Philippines")
                        .email(faker.bothify(String.format("????##@%s.com", companyName.replace(" ", "").toLowerCase())))
                        .locked(false)
                        .enabled(true)
                        .appUserRole(AppUserRole.USER_ADMIN)
                        .password("1234")
                        .build();
                authenticationService.register(request);
            }

            String companyName = "Ateneo";


            RegisterRequest request = RegisterRequest.builder()
                    .name(Name.builder()
                            .firstName("Joshua")
                            .lastName("Salcedo")
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

            School school = School
                    .builder()
                    .schoolName("Xavier University")
                    .build();

            institutionService.addSchool(appUser.getInstitution().getId(), school);
            appUser = appUserService.getAppuser("joshuagarrysalcedo@gmail.com");

            school = appUser.getInstitution().getSchools().get(0);

            String[] curriculumNames = {
                    "Philosophy of Physics",
                    "Electrical Engineering",
                    "Aesthetics",
                    "Philosophy of Action",
                    "Financial Economics"
            };

            System.out.println("Curriculum");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            for (String curriculumName : curriculumNames) {
                Curriculum curriculum = Curriculum
                        .builder()
                        .curriculumName(curriculumName)
                        .build();
                school = schoolService.addCurriculum(school.getId(), curriculum);
            }



            System.out.println("Adding Students");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
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

            System.out.println("Enrolling students to curriculum");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");

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
            });

            school.getStudents().forEach(System.out::println);

            System.out.println("AppUserID : " + appUser.getId());
            System.out.println("Institution Name : " + appUser.getInstitution().getInstitutionName());
            System.out.println("School name : " + school.getSchoolName());
            System.out.println("School ID: " + school.getId());
        };




    }

    @Bean

    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public Faker faker(){
        return new Faker();
    }
    @Bean
    public Random random(){
        return new Random();
    }



}
