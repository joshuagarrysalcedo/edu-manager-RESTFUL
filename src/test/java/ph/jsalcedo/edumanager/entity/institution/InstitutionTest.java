package ph.jsalcedo.edumanager.entity.institution;

import jakarta.persistence.SequenceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.logging.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.exception.DuplicateSchoolNameException;
import ph.jsalcedo.edumanager.exception.InstitutionNotFoundException;
import ph.jsalcedo.edumanager.utils.models.enums.ErrorMessage;
import ph.jsalcedo.edumanager.utils.sequence.ResettableSequenceStyleGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.rmi.server.LogStream.log;

@SpringBootTest
@Slf4j
class InstitutionTest {

    private final InstitutionService institutionService;
    private final InstitutionRepository institutionRepository;
    private Institution institution;
    @Autowired
    public InstitutionTest(InstitutionService institutionService, InstitutionRepository institutionRepository) {
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
    }

    @BeforeEach()
    void initialize(){

      this.institution = Institution.builder().institutionName("InstitutionTest").build();
        institutionService.add(institution);
    }

    @AfterEach
    void reset(){
        ResettableSequenceStyleGenerator.resetAllInstances();
        institutionRepository.deleteAll();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1sfa",
            "@21321",
            "`!",
            "()sdfas",
            "66666",
            "__",
            "_",
            "1111"
    })
    void shouldThrowIllegalStateExceptionIfInvalidName(String test){
        Institution institution = Institution.builder().institutionName(test).build();



        Exception exception = Assertions.assertThrows(IllegalStateException.class, ()->{
            institutionService.add(institution);
        });

        String result = exception.getMessage();

        Assertions.assertEquals(ErrorMessage.Constants.INVALID_NAME, result);
    }



    @ParameterizedTest
    @CsvSource(value = {
            "Hello",
            "CompanyName",
            "SampleName",
            "Merry1233",
            "OMG66666",
            "ZZZ",
            "Whatsup",
            "OH no"
    })
    void shouldReturnTrueBecauseNamesAreValid(String test){
        Institution institution = Institution.builder().institutionName(test).build();

            institutionService.add(institution);
        Assertions.assertEquals(2, institutionService.count());
    }

    @Test
    void institutionShouldNotHaveANullSchoolList(){
        System.out.println("Here at institutionShouldNotHaveANullSchoolList ID: " + institution.getId());
      Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();

      Assertions.assertTrue(var.isPresent());
        Assertions.assertNotNull(var.get().getSchools());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "100000",
            "-1",
            "4444",
            "101"
    })
    void shouldThrowAndErrorForDeletingARecordThatDoesNotExists(String test){
        Exception exception = Assertions.assertThrows(InstitutionNotFoundException.class, ()->{
            institutionService.deleteInstitution(Long.parseLong(test));
        });
        String expected = String.format("Cannot find Institution with id %s", test);
        String result = exception.getMessage();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldBeAbleToDeleteARecordFromRepo(){
        List<Institution> institutionList = institutionService.all();
        institutionList.forEach(i-> System.out.println(i.getInstitutionName() + " " + i.getId()));
        Assertions.assertEquals(1, institutionService.count());
        institutionService.deleteInstitution(1L);
//        Assertions.assertEquals(0, institutionService.count());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ssss;ssss",
            "city central;city central",
            "cItY cEntral;city CENTRAL",
            "ggg;ggg"
    })
    void thisTestShouldThrowADuplicateSchoolNameException(String test){
        String[] names = test.split(";");
        Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();
        Assertions.assertTrue(var.isPresent());

        System.out.println("Here at thisTestShouldThrowADuplicateSchoolNameException ID : " + var.get().getId());
        Exception exception = Assertions.assertThrows(DuplicateSchoolNameException.class, ()->{
            for (String name : names) {
                School school = School.builder().schoolName(name).build();
                institutionService.addSchool(var.get().getId(), school);
            }
        });

        String expected = String.format("Error: School [%s] name already exists", names[0]);
        String result = exception.getMessage();

        Assertions.assertEquals(expected, result);

    }


    @ParameterizedTest
    @CsvSource(value = {
            "zzz;ad, 2",
            "zzz;ad;asfas;dfas, 4",
            "aaaaazzz; , 1",
            ";, 0",
            "ssdfsdfasfasfsadf;dfgqqrqwrq;asfasf, 3"
    })
    void thisShouldTestWhetherOrNotTheDatabaseIsUpdatedAfterAdding(String test, String count){
        String[] names = test.split(";");
        Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();
        Assertions.assertTrue(var.isPresent());

        for (String name : names) {
            School school = School.builder().schoolName(name).build();
            institutionService.addSchool(var.get().getId(), school);
        }

        Assertions.assertEquals(names.length, Integer.parseInt(count));
    }


    // TODO: 16/02/2023 Transfer this test to the SchoolServiceTest! 
    
//    @ParameterizedTest
//    @CsvSource({
//            "ccs, ssc",
//            "ggs, ssg",
//            "omg, mgo"
//    })
//    void thisShouldChangeTheNameOfTheSchool(String oldVar, String newVar){
//        Institution institution = Institution.builder("add school test");
//        institutionService.add(institution);
//
//        Assertions.assertFalse(institution.getSchools().stream().anyMatch(i->
//            i.getSchoolName().equalsIgnoreCase(oldVar)));
//
//        institutionService.addSchool(institution, School.builder().schoolName(oldVar).build());
//
//        Assertions.assertTrue(institution.getSchools().stream().anyMatch(i->
//                i.getSchoolName().equalsIgnoreCase(oldVar)));
//
//        Long schoolID = institution.getSchools().stream()
//                .filter(school -> school.getSchoolName().equalsIgnoreCase(oldVar))
//                .map(School::getId)
//                .findFirst()
//                .orElse(null);
//
//        Assertions.assertNotNull(schoolID);
//
//        institutionService.updateSchool(institution, schoolID, School.builder().schoolName(newVar).build());
//
//        Assertions.assertFalse(institution.getSchools().stream().anyMatch(i->
//                i.getSchoolName().equalsIgnoreCase(oldVar)));
//
//        Assertions.assertTrue(institution.getSchools().stream().anyMatch(i->
//                i.getSchoolName().equalsIgnoreCase(newVar)));
//    }
}

