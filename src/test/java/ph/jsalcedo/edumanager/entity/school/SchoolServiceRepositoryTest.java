package ph.jsalcedo.edumanager.entity.school;

import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ph.jsalcedo.edumanager.entity.appuser.AppUserRepository;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.institution.InstitutionRepository;
import ph.jsalcedo.edumanager.entity.institution.InstitutionService;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.school.curriculum.CurriculumRepository;
import ph.jsalcedo.edumanager.entity.school.curriculum.CurriculumService;
import ph.jsalcedo.edumanager.entity.student.Student;
import ph.jsalcedo.edumanager.exceptions.exception.CustomInvalidNameException;
import ph.jsalcedo.edumanager.exceptions.exception.DuplicateNameException;
import ph.jsalcedo.edumanager.utils.models.person.Name;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

/**
 * <h1> SchoolServiceRepositoryTest</h1>
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
 * @created 17/02/2023
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SchoolServiceRepositoryTest {

    private final SchoolService schoolService;

    private final InstitutionService institutionService;
    private final InstitutionRepository institutionRepository;
    private final SchoolRepository schoolRepository;
    private final Faker faker;
    private final CurriculumRepository curriculumRepository;
    private final CurriculumService curriculumService;
    private final AppUserRepository appUserRepository;

    @Autowired
    public SchoolServiceRepositoryTest(SchoolService schoolService, InstitutionService institutionService, InstitutionRepository institutionRepository, SchoolRepository schoolRepository, Faker faker, CurriculumRepository curriculumRepository, CurriculumService curriculumService, AppUserRepository appUserRepository) {
        this.schoolService = schoolService;
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
        this.schoolRepository = schoolRepository;
        this.faker = faker;
        this.curriculumRepository = curriculumRepository;
        this.curriculumService = curriculumService;
        this.appUserRepository = appUserRepository;
    }

    @BeforeEach
    void initialize(){
        appUserRepository.deleteAll();
    }

    @Test
    void injectedComponentsAreNotNull(){
        Assertions.assertThat(schoolService).isNotNull();
        Assertions.assertThat(institutionService).isNotNull();
    }

    @Test
    void shouldBeAbleToCreateInstitutionEntity(){
        Institution institution = Institution.builder().institutionName("Xavier University").build();
        Assertions.assertThat(institution.getId()).isNull();
        institutionRepository.save(institution);
        Assertions.assertThat(institution.getId()).isNotNull();
    }

    @AfterEach
    void reset(){
        institutionRepository.deleteAll();
        schoolRepository.deleteAll();
        curriculumRepository.deleteAll();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Ateneo, Lasalle, 2",
            "GGG, AAA, 2"
    })
    void shouldTestInstitutionService(String inst1, String inst2, int length){
        Institution var1 = Institution.builder().institutionName(inst1).build();
        Institution var2 = Institution.builder().institutionName(inst2).build();

        System.out.println("Before Saving : ");
        System.out.println("Var 1 ID: " + (var1.getId() == null ? "null" : var1.getId()));
        System.out.println("Var 2 ID: " + (var2.getId() == null ? "null" : var1.getId()));

        institutionService.create(var1);
        institutionService.create(var2);
        Assertions.assertThat(institutionService.count() == length).isTrue();
        Assertions.assertThat(institutionRepository.findAll().size() == length).isTrue();

        System.out.println("After Saving : ");
        System.out.println("Var 1 ID: " + var1.getId());
        System.out.println("Var 2 ID: " + var2.getId());
    }
    @ParameterizedTest
    @CsvSource(value = {
            "Xavier College;Xavier HighSchool;Xavier Grade School, 3",
            "111;aaa;bbb, 2",
            "aaa;aaa;b, 1",
            "aBa;aBa;121, 1",
            "123;432;111, 0"
    })
    void shouldTestInstitutionToAddSchool(String schools, int schoolSize){
        System.out.println("<-----------------Start of Test----------------------->");
        System.out.println();
        System.out.println();
        System.out.println();
        Institution institution = Institution.builder()
                .institutionName(faker.university().name())
                .schools(new ArrayList<>())
                .build();
        institutionService.create(institution);
        String[] schoolNames = schools.split(";");
        School[] schoolArr = new School[schoolNames.length];


        //Populating the School
        for(int i = 0; i < schoolNames.length; i++){
            schoolArr[i] = School.builder().schoolName(schoolNames[i]).build();
        }





        Assertions.assertThat(institution.getId() == null).isFalse();

        System.out.println("Institution After Saving: ");
        System.out.println(institution.getSchools());
        printLoading();

        //Printing the School to be added
        System.out.println(institution.getInstitutionName() + " wants to add the following schools :");
        Arrays.stream(schoolArr).forEach((e)->{
            System.out.println(e.getSchoolName());
        });

        System.out.println("InstitutionService checking if All Schools are valid");
        printLoading();

        for(School school :schoolArr){
            try{
                institution =  institutionService.addSchool(institution.getId(), school);
                School finalSchool = school;
                Optional<School> optionalSchool = institution.getSchools().stream()
                        .filter((e)-> e.getSchoolName().equalsIgnoreCase(finalSchool.getSchoolName())).findFirst();
                Assertions.assertThat(optionalSchool.isPresent()).isTrue();
                school = optionalSchool.get();
                System.out.println(school.getSchoolName() + " is accepted!");
            }catch (DuplicateNameException | CustomInvalidNameException e1){
                System.out.println(school.getSchoolName() + " is rejected!");
            }
        }

        System.out.println("After checking here are all the accepted Schools");
        System.out.println("Number of accepted School : " + institution.getSchools().size());

        institution.getSchools().forEach((e)->{
            System.out.println(e.getSchoolName());
        });
        printLoading();



        System.out.println("Institution After Adding school: ");
        System.out.println(institution.getSchools());







        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("<----------------- End  of Test----------------------->");
    }

    @Test
    void shouldBeAbleToAddACurriculum(){
        System.out.println("<-----------------Start of Test----------------------->");
        System.out.println();
        System.out.println();
        System.out.println();
        Institution institution = Institution.builder()
                .institutionName(faker.university().name())
                .build();
        institution = institutionService.create(institution);

        Assertions.assertThat(institution.getSchools()).isNull();
        School school = School.builder().schoolName(faker.university().name()).build();
        institution = institutionService.addSchool(institution.getId(), school);

        System.out.println("Institution after Adding School : ");
        System.out.println("Institution After Saving: ");
        System.out.println(institution.getSchools());
        printLoading();
        Assertions.assertThat(institution.getSchools()).isNotNull();


        school = schoolService.findSchoolByInstitutionAndName(institution, school.getSchoolName());

        for(int i = 0; i < 5; i++){
            Curriculum curriculum = Curriculum.builder().curriculumName(faker.educator().course()).build();
             school = schoolService.addCurriculum(schoolService.findSchoolByInstitutionAndName(institution, school.getSchoolName()).getId(), curriculum);
        }


        System.out.println("School after Adding curriculum");
        System.out.println("School : " + school.getSchoolName());
        System.out.println(school.getCurriculum());


        System.out.println("Curriculum Size before deleting : " + school.getCurriculum().size());
        Assertions.assertThat(school.getCurriculum().size()).isEqualTo(5);

        Curriculum curriculum = curriculumService.findBySchoolAndCurriculumName(school, school.getCurriculum().get(0).getCurriculumName());
        System.out.println("Curriculum : " + curriculum);


        school = schoolService.deleteCurriculum(school.getId(), curriculum);


        System.out.println("Curriculum Size after deleting : " + school.getCurriculum().size());
        System.out.println("New Curriculums : " + school.getCurriculum());
       Assertions.assertThat(school.getCurriculum().size()).isEqualTo(4);
    }




    @Test
    void shouldBeAbleToAddStudent(){
        System.out.println("<-----------------Start of Test----------------------->");
        System.out.println();
        System.out.println();
        System.out.println();
        Institution institution = Institution.builder()
                .institutionName(faker.university().name())
                .build();
        institution = institutionService.create(institution);

        Assertions.assertThat(institution.getSchools()).isNull();
        School school = School.builder().schoolName(faker.university().name()).build();
        institution = institutionService.addSchool(institution.getId(), school);

        System.out.println("Institution after Adding School : ");
        System.out.println("Institution After Saving: ");
        System.out.println(institution.getSchools());
        printLoading();
        Assertions.assertThat(institution.getSchools()).isNotNull();
        String[] curriculumNames = {
                "Philosophy of Physics",
                "Electrical Engineering",
                "Aesthetics",
                "Philosophy of Action",
                "Financial Economics"
        };

        school = schoolService.findSchoolByInstitutionAndName(institution, school.getSchoolName());

        for(int i = 0; i < curriculumNames.length; i++){
            Curriculum curriculum = Curriculum.builder().curriculumName(curriculumNames[i]).build();
            school = schoolService.addCurriculum(schoolService.findSchoolByInstitutionAndName(institution, school.getSchoolName()).getId(), curriculum);
        }


        System.out.println("School after Adding curriculum");
        System.out.println("School : " + school.getSchoolName());
        System.out.println(school.getCurriculum());


        System.out.println("Adding students");
        for(int i = 0; i < 100; i++){
            Student student = Student.builder().name(
                    Name.builder()
                            .firstName(faker.name().firstName())
                            .middleName(faker.name().lastName())
                            .lastName(faker.name().lastName())
                            .build()
            ).build();
            school = schoolService.addStudent(school.getId(), student);
        }
        System.out.println("Should have 100 students!");
        System.out.println("Quantity: " + school.getStudents().size());
        school.getStudents().forEach(e->{
            System.out.println(e.getName());
        });
        Assertions.assertThat(school.getStudents().size()).isEqualTo(100);

        school = schoolService.deleteStudent(school.getId(), school.getStudents().get(0));
        System.out.println("Should have 99 students");
        System.out.println("Quantity: " + school.getStudents().size());
        Assertions.assertThat(school.getStudents().size()).isEqualTo(99);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("<----------------- End  of Test----------------------->");

    }

    @Test
    void shouldBeAbleToAddCurriculum(){
        Random random = new Random();
        System.out.println("<-----------------Start of Test----------------------->");
        System.out.println();
        System.out.println();
        System.out.println();
        Institution institution = Institution.builder()
                .institutionName(faker.university().name())
                .build();
        institution = institutionService.create(institution);

        Assertions.assertThat(institution.getSchools()).isNull();
        School school = School.builder().schoolName(faker.university().name()).build();
        institution = institutionService.addSchool(institution.getId(), school);

        System.out.println("Institution after Adding School : ");
        System.out.println("Institution After Saving: ");
        System.out.println(institution.getSchools());
        printLoading();
        Assertions.assertThat(institution.getSchools()).isNotNull();


        school = schoolService.findSchoolByInstitutionAndName(institution, school.getSchoolName());
        String[] curriculumNames = {
                "Philosophy of Physics",
                "Electrical Engineering",
                "Aesthetics",
                "Philosophy of Action",
                "Financial Economics"
        };
        for(int i = 0; i < curriculumNames.length; i++){
            Curriculum curriculum = Curriculum.builder().curriculumName(curriculumNames[i]).build();
            school = schoolService.addCurriculum(schoolService.findSchoolByInstitutionAndName(institution, school.getSchoolName()).getId(), curriculum);
        }


        System.out.println("School after Adding curriculum");
        System.out.println("School : " + school.getSchoolName());
        System.out.println(school.getCurriculum());


        System.out.println("Adding students");
        for(int i = 0; i < 100; i++){
            Student student = Student.builder().name(
                    Name.builder()
                            .firstName(faker.name().firstName())
                            .middleName(faker.name().lastName())
                            .lastName(faker.name().lastName())
                            .build()
            ).build();
//            school.getCurriculum().get(0) = curriculumService.addStudent(school.getCurriculum().get(0).getId(), student);
            school = schoolService.addStudent(school.getId(), student);
        }
        System.out.println("Should have 100 students!");
        System.out.println("Quantity: " + school.getStudents().size());
//        school.getStudents().forEach(e->{
//            System.out.println(e.getName());
//        });
        Assertions.assertThat(school.getStudents().size()).isEqualTo(100);


        school.getCurriculum().forEach(System.out::println);
        System.out.println("Adding Curriculum to each student");
        printLoading();
        for(int i = 0; i < school.getStudents().size(); i++){
            int index = random.nextInt(school.getCurriculum().size());
            Curriculum curriculum = curriculumService.findByID(school.getCurriculum().get(index).getId());
            curriculum = curriculumService.addStudent(school.getCurriculum().get(index).getId(), school.getStudents().get(i));
//            school = schoolService.updateCurriculum(school.getId(), curriculum);
//            System.out.println(curriculum);
        }
        school = schoolService.findSchoolByID(school.getId());
        printLoading();
        school.getStudents().forEach(System.out::println);

        printLoading();
        school.getCurriculum().forEach(System.out::println);



//        int curriculumSize = school.getCurriculum().size();
//
//        for(int i = 0; i < school.getStudents().size(); i++){
//            int index = random.nextInt(curriculumSize);
//            Curriculum curriculum = curriculumService.findBySchoolAndCurriculumName(school, school.getCurriculum().get(index).getCurriculumName());
//            Long id = curriculum.getId();
//
//            Curriculum temp = curriculumService.addStudent(id, school.getStudents().get(i));
//            school = schoolService.updateCurriculum(school.getId(), curriculum);
//        }
//
//
//
//        System.out.println("Students should have a curriculum");
//



        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("<----------------- End  of Test----------------------->");
    }



















    private void printLoading(){
        StringBuilder loading = new StringBuilder();
        for(int i = 0; i < 5; i++){
            loading.append(".");
            System.out.println(loading);
        }
    }
}
