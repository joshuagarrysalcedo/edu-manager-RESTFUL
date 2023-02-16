package ph.jsalcedo.edumanager.entity.school;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.institution.InstitutionRepository;
import ph.jsalcedo.edumanager.entity.institution.InstitutionService;
import ph.jsalcedo.edumanager.exceptions.exception.DuplicateSchoolNameException;

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
        institutionService.add(this.institution);

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
    void test1(String newDuplicateName){


//        Assertions.assertEquals(5, this.institution.getSchools().size());
//        Assertions.assertEquals(1, institutionService.count());
//        Assertions.assertEquals(5, schoolRepository.findAll().size());

//        Assertions.assertEquals(5, schoolRepository.findAllByInstitution(institution).size());
        Exception exception = Assertions.assertThrows(DuplicateSchoolNameException.class, ()->{
            Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();
            Assertions.assertTrue(var.isPresent());
            System.out.println("Here at Institution ID: " +var.get().getId());

           School school = schoolService.findSchoolByInstitutionAndName(var.get(), "Lone Pine School");

           System.out.println("ID : " + school.getId());
           school.setSchoolName(newDuplicateName);
           schoolService.updateSchool(school);
        });
    }


}