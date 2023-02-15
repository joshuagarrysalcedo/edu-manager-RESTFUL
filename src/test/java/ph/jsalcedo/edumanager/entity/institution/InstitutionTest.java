package ph.jsalcedo.edumanager.entity.institution;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.exception.DuplicateSchoolNameException;
import ph.jsalcedo.edumanager.exception.InstitutionNotFoundException;
import ph.jsalcedo.edumanager.utils.models.enums.ErrorMessage;

@SpringBootTest
class InstitutionTest {

    private final InstitutionService institutionService;
    private final InstitutionRepository institutionRepository;
    @Autowired
    public InstitutionTest(InstitutionService institutionService, InstitutionRepository institutionRepository) {
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
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
        Institution institution = Institution.builder(test);



        Exception exception = Assertions.assertThrows(IllegalStateException.class, ()->{
            institutionService.add(institution);
        });

        String result = exception.getMessage();

        Assertions.assertEquals(ErrorMessage.Constants.INVALID_NAME, result);
    }

    @AfterEach
    void reset(){
        institutionRepository.deleteAll();
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
        Institution institution = Institution.builder(test);

            institutionService.add(institution);
        Assertions.assertEquals(1, institutionService.count());
    }

    @Test
    void institutionShouldNotHaveANullSchoolList(){
        Institution institution = Institution.builder("Not A Null Institution");

        institutionService.add(institution);

        Assertions.assertNotNull(institution.getSchools());
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
        Institution institution = Institution.builder("SampleName");

        institutionService.add(institution);
        Assertions.assertEquals(1, institutionService.count());
        institutionService.deleteInstitution(1L);
        Assertions.assertEquals(0, institutionService.count());
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
        Institution institution = Institution.builder("add school test");
        institutionService.add(institution);
        Exception exception = Assertions.assertThrows(DuplicateSchoolNameException.class, ()->{
            for (String name : names) {
                School school = School.builder().schoolName(name).build();
                institutionService.addSchool(institution, school);
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
        Institution institution = Institution.builder("add school test");
        institutionService.add(institution);

        for (String name : names) {
            School school = School.builder().schoolName(name).build();
            institutionService.addSchool(institution, school);
        }

        Assertions.assertEquals(names.length, Integer.parseInt(count));
    }


}

