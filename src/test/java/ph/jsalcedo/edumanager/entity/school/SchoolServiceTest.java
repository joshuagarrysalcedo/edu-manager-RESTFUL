package ph.jsalcedo.edumanager.entity.school;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.institution.InstitutionRepository;
import ph.jsalcedo.edumanager.entity.institution.InstitutionService;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.school.curriculum.CurriculumService;
import ph.jsalcedo.edumanager.exceptions.ExceptionMessage;
import ph.jsalcedo.edumanager.exceptions.exception.CustomEntityNotFoundException;
import ph.jsalcedo.edumanager.exceptions.exception.CustomInvalidNameException;
import ph.jsalcedo.edumanager.exceptions.exception.DuplicateNameException;
import ph.jsalcedo.edumanager.exceptions.exception.EntityNotOwnedException;

import java.util.Optional;

@SpringBootTest
class SchoolServiceTest {
    private final SchoolService schoolService;
    private final SchoolRepository schoolRepository;
    private final InstitutionService institutionService;
    private final CurriculumService curriculumService;

    private Institution institution;

    private final InstitutionRepository institutionRepository;

    @Autowired
    public SchoolServiceTest(SchoolService schoolService, SchoolRepository schoolRepository, InstitutionService institutionService, CurriculumService curriculumService, InstitutionRepository institutionRepository) {
        this.schoolService = schoolService;
        this.schoolRepository = schoolRepository;
        this.institutionService = institutionService;
        this.curriculumService = curriculumService;
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



    ///

    @ParameterizedTest
    @CsvSource(value = {
            "course1;course1",
            "city central;city central",
            "cItY cEntral;city CENTRAL",
            "ggg;ggg"
    })
    void thisTestShouldThrowADuplicateNameException(String test){
        String[] names = test.split(";");
        Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();
        Assertions.assertTrue(var.isPresent());



        School var2 = schoolService.findLatestSchoolByInstitution(var.get());


        System.out.println("Here at thisTestShouldThrowADuplicateNameException ID : " + var2.getId());
        Exception exception = Assertions.assertThrows(DuplicateNameException.class, ()->{
            for (String name : names) {
                Curriculum curriculum = Curriculum.builder().curriculumName(name).build();
                schoolService.addCurriculum(var2.getId(), curriculum);
            }
        });

        String expected = String.format(ExceptionMessage.DUPLICATE_NAME_MESSAGE, names[0]);
        String result = exception.getMessage();

        System.out.println("Testing DuplicateCurriculumName result: " + result);
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
    //TODO
    void thisShouldTestWhetherOrNotTheDatabaseIsUpdatedAfterAdding(String test, String count){
        String[] names = test.split(";");
        Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();
        Assertions.assertTrue(var.isPresent());

        School var2 = schoolService.findLatestSchoolByInstitution(var.get());



        for (String name : names) {
            Curriculum curriculum = Curriculum.builder().curriculumName(name).build();
            schoolService.addCurriculum(var2.getId(), curriculum);
        }
        School school = schoolService.findSchoolByID(var2.getId());
        Assertions.assertEquals(Integer.parseInt(count), school.getCurriculum().size());
    }


//    @Test
//        //TODO
//    void shouldDeleteASchool(){
//        Optional<Institution> var = institutionRepository.findFirstByOrderByIdDesc();
//        Assertions.assertTrue(var.isPresent());
//        School var2 = schoolService.findLatestSchoolByInstitution(var.get());
//
//        System.out.println("School name: " + var2.getSchoolName());
//        String[] curriculumArr = {
//                "First Grade",
//                "Second Grade",
//                "Third Grade",
//                "Fourth Grade",
//                "Fourth Grade"
//        };
//
//
//        for(int i = 0; i < curriculumArr.length; i++){
//            Curriculum curriculum = Curriculum.builder().curriculumName(curriculumArr[i]).build();
//            if(i == 4){
//                Assertions.assertThrows(DuplicateNameException.class, ()->{
//                   schoolService.addCurriculum(var2.getId(), curriculum);
//                });
//            }else{
//                schoolService.addCurriculum(var2.getId(), curriculum);
//            }
//        }
//
//
//          int originalSize = curriculumService.findAllCurriculumBySchool(var2).size();
//          School updatedSchool = schoolService.findLatestSchoolByInstitution(institution);
//        System.out.println("Updated school name " + updatedSchool.getSchoolName());
//        System.out.println("Curriculums : " );
//        for(int i = 0; i < updatedSchool.getCurriculum().size(); i++){
//            System.out.println(updatedSchool.getCurriculum().get(i).getCurriculumName());
//        }
//          //Should have the same size
//          Assertions.assertEquals(originalSize, updatedSchool.getCurriculum().size());
//
//
//
//        System.out.println("Original Length : " + originalSize);
//        String soonToBeDeletedCurriculumName = updatedSchool.getCurriculum().get(0).getCurriculumName();
//
//        Assertions.assertEquals(curriculumArr[0], soonToBeDeletedCurriculumName);
//
//        System.out.println("Soon to be deleted School Name: " + soonToBeDeletedCurriculumName);
//
//
//        schoolService.deleteCurriculum(updatedSchool.getId(), updatedSchool.getCurriculum().get(0));
//
//        System.out.println("Number of Schools from  " + var.get().getInstitutionName() + ": " + var.get().getSchools().size());
//        Optional<Institution> optVar = institutionRepository.findFirstByOrderByIdDesc();
//        Assertions.assertTrue(optVar.isPresent());
//        School schoolAfterDeletingCurriculum = schoolService.findSchoolByInstitutionAndName(optVar.get(),"Lone Pine School");
//
//        System.out.println("Updated School Name after deleting : " + schoolAfterDeletingCurriculum.getSchoolName());
//        System.out.println("Curriculums : " );
//        for(int i = 0; i < schoolAfterDeletingCurriculum.getCurriculum().size(); i++){
//            System.out.println(schoolAfterDeletingCurriculum.getCurriculum().get(i).getCurriculumName());
//        }
//
//
//
//
//        int newLength = curriculumService.findAllCurriculumBySchool(schoolAfterDeletingCurriculum).size();
//
//        System.out.println("New Length :" + newLength);
//        System.out.println("Final Updated School Name : " + schoolAfterDeletingCurriculum.getSchoolName());
//        Assertions.assertThrows(EntityNotOwnedException.class, ()->{
//           Curriculum curriculum = curriculumService.findBySchoolAndCurriculumName(schoolAfterDeletingCurriculum, soonToBeDeletedCurriculumName);
//        });
//
//        Assertions.assertNotEquals(originalSize, newLength);
//
//    }





}