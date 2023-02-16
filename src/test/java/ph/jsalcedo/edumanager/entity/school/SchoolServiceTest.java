package ph.jsalcedo.edumanager.entity.school;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.institution.InstitutionRepository;
import ph.jsalcedo.edumanager.entity.institution.InstitutionService;
import ph.jsalcedo.edumanager.exceptions.ExceptionMessage;
import ph.jsalcedo.edumanager.exceptions.exception.CustomInvalidNameException;
import ph.jsalcedo.edumanager.exceptions.exception.DuplicateNameException;

import java.util.Optional;

@SpringBootTest
class SchoolServiceTest {
    private final SchoolService schoolService;
    private final SchoolRepository schoolRepository;
    private final InstitutionService institutionService;

    private Institution institution;

    private final InstitutionRepository institutionRepository;

    @Autowired
    public SchoolServiceTest(SchoolService schoolService, SchoolRepository schoolRepository, InstitutionService institutionService, InstitutionRepository institutionRepository) {
        this.schoolService = schoolService;
        this.schoolRepository = schoolRepository;
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;


    }

    @BeforeEach
    void initialize(){
        this.institution = Institution.builder().institutionName("SchoolServiceTest").id(1L).build();
        institutionService.create(this.institution);

        Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();
        Assertions.assertTrue(var.isPresent());
        System.out.println("Here at SchoolServiceTest ID: " +var.get().getId());
        String[] schoolArr = {
                "Lone Pine School",
                "Village Kindergarten",
                "Paradise School of Fine Arts",
                "Oceanside High",
                "Coral Coast School of Fine Arts"
        };

        for (String s : schoolArr) {
             institutionService.addSchool(var.get().getId(), School.builder().schoolName(s).build());
        }

    }
    @AfterEach
    void reset(){
        schoolRepository.deleteAll();
        institutionRepository.deleteAll();
    }


    @ParameterizedTest
    @CsvSource(value = {
            "Village Kindergarten",
            "Paradise School of Fine Arts",
            "Oceanside High",
            "Coral Coast School of Fine Arts"
    })
    @DisplayName("This Test should throw a Duplicate School Name Exception when changing a school name that has a duplicate value!")
    void shouldThrowException(String newDuplicateName){

        Exception exception = Assertions.assertThrows(DuplicateNameException.class, ()->{
            Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();
            Assertions.assertTrue(var.isPresent());
            System.out.println("Here at Institution ID: " +var.get().getId());

           School school = schoolService.findSchoolByInstitutionAndName(var.get(), "Lone Pine School");

           System.out.println("ID : " + school.getId());
           school.setSchoolName(newDuplicateName);
           schoolService.updateSchool(school);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "CCS",
            "XU",
            "GGG",
            "SAMPLE SCHOOL",
            "ñino",
            "óóóóóó"
    })
    void shouldUpdateSchool(String newName){
        Optional<School> schoolOptional = schoolRepository.findFirstByOrderByIdDesc();

        Assertions.assertTrue(schoolOptional.isPresent());
        String oldName = schoolOptional.get().getSchoolName();
        System.out.println("OLD SCHOOL NAME : " + oldName);
        schoolOptional.get().setSchoolName(newName);
        System.out.println("NEW SCHOOL NAME: " + schoolOptional.get().getSchoolName());
        schoolService.updateSchool(schoolOptional.get());

        Assertions.assertFalse(schoolRepository.existsBySchoolNameAndAndInstitution(oldName, schoolOptional.get().getInstitution()));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "111CCS",
            "@XU",
            "`GGG",
            "-=SAMPLE SCHOOL",
            "s"
    })
    void shouldThrowCustomInvalidNameExceptionForUpdatingASchoolName(String name){
        String expected = String.format(ExceptionMessage.INVALID_NAME_MESSAGE, name);
        Optional<School> schoolOptional = schoolRepository.findFirstByOrderByIdDesc();
        Assertions.assertTrue(schoolOptional.isPresent());

        schoolOptional.get().setSchoolName(name);
        Exception exception = Assertions.assertThrows(CustomInvalidNameException.class, ()->{
            System.out.println("Expected : \n" + expected);
            schoolService.updateSchool(schoolOptional.get());
        });



        String result = exception.getMessage();

        Assertions.assertEquals(expected, result);
    }
    @Test
    void shouldReturnListOfSchools(){
        Optional<Institution> optionalInstitution = institutionRepository.findFirstByOrderByIdDesc();
        Assertions.assertTrue(optionalInstitution.isPresent());

        Assertions.assertNotNull(optionalInstitution.get().getSchools());

        Assertions.assertEquals(5, optionalInstitution.get().getSchools().size());
    }



}