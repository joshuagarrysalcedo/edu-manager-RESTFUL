package ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.service.SchoolDetailsService;
import ph.jsalcedo.edumanager.repository.SchoolDetailsRepository;
import ph.jsalcedo.edumanager.utils.models.enums.ErrorMessage;
import ph.jsalcedo.edumanager.utils.models.person.Address;
import ph.jsalcedo.edumanager.entity.SchoolDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SchoolDetailsServiceImplTest {

    private final SchoolDetailsService schoolDetailsService;
    private final SchoolDetailsRepository schoolDetailsRepository;
    private SchoolDetails schoolDetails;
    @BeforeEach
    void initialize(){
        this.schoolDetails = SchoolDetails.builder()
                .schoolDomain("sample-school.com")
                .foundedOn(new Date())
                .founder("Juan Tamad")
                .schoolAddress(Address.builder("Cagayan de Oro City", "Phillines").build())
                .schoolName("sample school university")
                .build();
        schoolDetailsService.addSchoolDetails(schoolDetails);
    }
    @AfterEach
    void reset(){
        schoolDetailsService.deleteAll();
    }
    @Autowired
    public SchoolDetailsServiceImplTest(SchoolDetailsService schoolDetailsService, SchoolDetailsRepository schoolDetailsRepository) {
        this.schoolDetailsService = schoolDetailsService;
        this.schoolDetailsRepository = schoolDetailsRepository;


    }


    @Test
    void addSchoolDetails() {
        SchoolDetails duplicateName = SchoolDetails.builder().schoolName("sample school university").build();
        SchoolDetails duplicateDomain = SchoolDetails
                .builder()
                .schoolName("duplicate domain school")
                .schoolDomain("sample-school.com")
                .build();
        SchoolDetails duplicateDetails = SchoolDetails
                .builder()
                .schoolName("sample school university")
                .schoolDomain("sample-school.com")
                .build();

        Exception duplicateNameException = Assertions.assertThrows(IllegalStateException.class, ()->{
            schoolDetailsService.addSchoolDetails(duplicateName);
        });

        Exception duplicateDomainException = Assertions.assertThrows(IllegalStateException.class, ()->{
            schoolDetailsService.addSchoolDetails(duplicateDomain);
        });

        Exception duplicateDetailsException = Assertions.assertThrows(IllegalStateException.class, ()->{
            schoolDetailsService.addSchoolDetails(duplicateDetails);
        });
        String duplicateNameErrorMessageResult = duplicateNameException.getMessage();
        String duplicateDomainErrorMessageResult = duplicateDomainException.getMessage();
        String duplicateDetailsErrorMessageResult = duplicateDetailsException.getMessage();
        String expectedErrorMessage = ErrorMessage.Constants.ADDING_SCHOOL_DETAILS_MESSAGE;

        Assertions.assertEquals(duplicateNameErrorMessageResult, expectedErrorMessage);
        Assertions.assertEquals(duplicateDomainErrorMessageResult, expectedErrorMessage);
        Assertions.assertEquals(duplicateDetailsErrorMessageResult, expectedErrorMessage);
        Assertions.assertTrue(schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName()).isPresent());
    }

    @Test
    void deleteSchoolDetails() {
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());
        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsService.deleteSchoolDetails(optionalSchoolDetails.get());
            Assertions.assertFalse(schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName()).isPresent());
            schoolDetailsRepository.save(schoolDetails);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }


    @Test
    void findSchoolDetails() {

        Optional<SchoolDetails> id = schoolDetailsService.findSchoolDetails(schoolDetails.getId());
        Optional<SchoolDetails> name = schoolDetailsService.findSchoolDetails("sample school university");
        Assertions.assertTrue(id.isPresent());
        Assertions.assertTrue(name.isPresent());


    }


    @Test
    void getAllSchoolDetails() {
        List<SchoolDetails> schoolDetailsList = schoolDetailsService.getAllSchoolDetails();
        System.out.println("Count: " + schoolDetailsList.size());
        Assertions.assertTrue(schoolDetailsList.size() > 0);
    }

    @Test
    void updateSchoolName() {
        String newName = "CITY CENTRAL";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());
        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsService.updateSchoolName(optionalSchoolDetails.get(), newName);
            Assertions.assertEquals(optionalSchoolDetails.get().getSchoolName(), newName);
            schoolDetailsService.updateSchoolName(optionalSchoolDetails.get(), "sample school university");
        }


        Assertions.assertTrue(optionalSchoolDetails.isPresent());

    }

    @Test
    void updateSchoolDomain(){
        String newDomain = "CityCentralSchool.com";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());
        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsService.updateSchoolDomain(optionalSchoolDetails.get(), newDomain );
            Assertions.assertEquals(optionalSchoolDetails.get().getSchoolDomain(), newDomain);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void updateFounder(){
        String newFounder = "joshua salcedo";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsService.updateFounder(optionalSchoolDetails.get(), newFounder );
            Assertions.assertEquals(optionalSchoolDetails.get().getFounder(), newFounder);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void updateFoundedOn() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date_String = "22-02-1995";

        Date newFoundedOn = formatter.parse(date_String);
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsService.updateFounderOn(optionalSchoolDetails.get(), newFoundedOn );
            Assertions.assertEquals(optionalSchoolDetails.get().getFoundedOn(), newFoundedOn);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void updateSchoolAddress(){
        Address address = Address.builder("Davao City","Philippines" ).build();
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsService.updateAddress(optionalSchoolDetails.get(), address );
            Assertions.assertEquals(optionalSchoolDetails.get().getSchoolAddress(), address);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void studentIDPattern(){
        String newSchoolIDPattern = "_";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsService.updateStudentIDPattern(optionalSchoolDetails.get(), newSchoolIDPattern );
            Assertions.assertEquals(optionalSchoolDetails.get().getStudentIDPattern(), newSchoolIDPattern);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void schoolIDPattern(){
        String newPattern= "=";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsService.updateEmployeeIDPattern(optionalSchoolDetails.get(), newPattern );
            Assertions.assertEquals(optionalSchoolDetails.get().getEmployeeIDPattern(), newPattern);
        }
        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void getEnrollmentStatuses(){
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        Assertions.assertNotNull(optionalSchoolDetails.get().getEnrollmentStatuses());
    }
}