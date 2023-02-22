package ph.jsalcedo.edumanager.entity.institution;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.appuser.AppUserRepository;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.SchoolRepository;
import ph.jsalcedo.edumanager.entity.school.SchoolService;
import ph.jsalcedo.edumanager.exceptions.exception.CustomEntityNotFoundException;
import ph.jsalcedo.edumanager.exceptions.exception.CustomInvalidNameException;
import ph.jsalcedo.edumanager.exceptions.exception.DuplicateNameException;
import ph.jsalcedo.edumanager.exceptions.ExceptionMessage;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class InstitutionTest {

    private final InstitutionService institutionService;
    private final InstitutionRepository institutionRepository;
    private final SchoolRepository schoolRepository;
    private final SchoolService schoolService;
    private Institution institution;
    private final AppUserRepository appUserRepository;
    @Autowired
    public InstitutionTest(InstitutionService institutionService, InstitutionRepository institutionRepository, SchoolRepository schoolRepository, SchoolService schoolService, AppUserRepository appUserRepository) {
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
        this.schoolRepository = schoolRepository;
        this.schoolService = schoolService;
        this.appUserRepository = appUserRepository;
    }

    @BeforeEach()
    void initialize(){
        appUserRepository.deleteAll();
      this.institution = Institution.builder().institutionName("InstitutionTest").build();
        institutionService.create(institution);

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



        Exception exception = Assertions.assertThrows(CustomInvalidNameException.class, ()->{
            institutionService.create(institution);
        });

        String result = exception.getMessage();
        System.out.println(result);
        Assertions.assertEquals(String.format(ExceptionMessage.INVALID_NAME_MESSAGE, test), result);
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

            institutionService.create(institution);
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
        Exception exception = Assertions.assertThrows(CustomEntityNotFoundException.class, ()->{
            institutionService.deleteInstitution(Long.parseLong(test));
        });
        String expected = String.format(ExceptionMessage.ENTITY_NOT_FOUND_MESSAGE, "Institution ID", test);
        String result = exception.getMessage();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldBeAbleToDeleteARecordFromRepo(){
        List<Institution> institutionList = institutionService.all();

        Assertions.assertEquals(1, institutionService.count());
        institutionList.forEach((i)->{
            System.out.println("Institution ID: " + i.getId());
            institutionService.deleteInstitution(i.getId());
        });


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
        Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();
        Assertions.assertTrue(var.isPresent());

        System.out.println("Here at thisTestShouldThrowADuplicateSchoolNameException ID : " + var.get().getId());
        Exception exception = Assertions.assertThrows(DuplicateNameException.class, ()->{
            for (String name : names) {
                School school = School.builder().schoolName(name).build();
                institutionService.addSchool(var.get().getId(), school);
            }
        });

        String expected = String.format(ExceptionMessage.DUPLICATE_NAME_MESSAGE, names[0]);
        String result = exception.getMessage();

        System.out.println("Testing DuplicateSchoolName result: " + result);
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


    @Test
    void shouldDeleteASchool(){
        Optional<Institution> optionalInstitution = institutionRepository.findFirstByOrderByIdDesc();
        Assertions.assertTrue(optionalInstitution.isPresent());

        int originalSize = schoolRepository.findAllByInstitution(optionalInstitution.get()).size();
        School school = schoolService.findSchoolByInstitutionAndName(optionalInstitution.get(), "Lone Pine School");
        Assertions.assertEquals(originalSize, optionalInstitution.get().getSchools().size());


        System.out.println("Original Length : " + originalSize);
        String deletedSchoolName = school.getSchoolName();
        System.out.println("Soon to be deleted School Name");
        institutionService.deleteSchool(optionalInstitution.get().getId(), school);
        int newLength = schoolRepository.findAllByInstitution(optionalInstitution.get()).size();
        System.out.println("New Length :" + newLength);
        Optional<School> deletedSchool = schoolRepository.findByInstitutionAndSchoolNameEqualsIgnoreCase(optionalInstitution.get(), deletedSchoolName);
        Assertions.assertTrue(deletedSchool.isEmpty());

         Assertions.assertNotEquals(originalSize, newLength);
    }



}

