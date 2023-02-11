package ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails;

import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.data.models.person.Address;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SchoolDetailsDaoImplTest {

    private final SchoolDetailsDao schoolDetailsDao;
    private final SchoolDetailsRepository schoolDetailsRepository;
    private SchoolDetails schoolDetails;

    @Autowired
    public SchoolDetailsDaoImplTest(SchoolDetailsDao schoolDetailsDao, SchoolDetailsRepository schoolDetailsRepository) {
        this.schoolDetailsDao = schoolDetailsDao;
        this.schoolDetailsRepository = schoolDetailsRepository;

        this.schoolDetails = SchoolDetails.builder()
                .schoolDomain("sample-school.com")
                .foundedOn(new Date())
                .founder("Juan Tamad")
                .schoolAddress(Address.builder().city("Cagayan de Oro City").build())
                .schoolName("sample school university")
                .build();
        schoolDetailsDao.addSchoolDetails(schoolDetails);
    }


    @Test
    void addSchoolDetails() {

        Assertions.assertTrue(schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName()).isPresent());
    }

    @Test
    void deleteSchoolDetails() {
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());
        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsDao.deleteSchoolDetails(optionalSchoolDetails.get());
            Assertions.assertFalse(schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName()).isPresent());
            schoolDetailsRepository.save(schoolDetails);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }


    @Test
    void findSchoolDetails() {

        Optional<SchoolDetails> id = schoolDetailsDao.findSchoolDetails(1L);
        Optional<SchoolDetails> name = schoolDetailsDao.findSchoolDetails("sample school university");
        Assertions.assertTrue(id.isPresent());
        Assertions.assertTrue(name.isPresent());


    }


    @Test
    void getAllSchoolDetails() {
        List<SchoolDetails> schoolDetailsList = schoolDetailsDao.getAllSchoolDetails();
        System.out.println("Count: " + schoolDetailsList.size());
        Assertions.assertTrue(schoolDetailsList.size() > 0);
    }

    @Test
    void updateSchoolName() {
        String newName = "CITY CENTRAL";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());
        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsDao.updateSchoolName(optionalSchoolDetails.get(), newName);
            Assertions.assertEquals(optionalSchoolDetails.get().getSchoolName(), newName);
            schoolDetailsDao.updateSchoolName(optionalSchoolDetails.get(), "sample school university");
        }


        Assertions.assertTrue(optionalSchoolDetails.isPresent());

    }

    @Test
    void updateSchoolDomain(){
        String newDomain = "CityCentralSchool.com";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());
        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsDao.updateSchoolDomain(optionalSchoolDetails.get(), newDomain );
            Assertions.assertEquals(optionalSchoolDetails.get().getSchoolDomain(), newDomain);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void updateFounder(){
        String newFounder = "joshua salcedo";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsDao.updateFounder(optionalSchoolDetails.get(), newFounder );
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
            schoolDetailsDao.updateFounderOn(optionalSchoolDetails.get(), newFoundedOn );
            Assertions.assertEquals(optionalSchoolDetails.get().getFoundedOn(), newFoundedOn);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void updateSchoolAddress(){
        Address address = Address.builder().city("Cagayan de Oro City").country("Philippines").build();
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsDao.updateAddress(optionalSchoolDetails.get(), address );
            Assertions.assertEquals(optionalSchoolDetails.get().getSchoolAddress(), address);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void studentIDPattern(){
        String newSchoolIDPattern = "_";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsDao.updateStudentIDPattern(optionalSchoolDetails.get(), newSchoolIDPattern );
            Assertions.assertEquals(optionalSchoolDetails.get().getStudentIDPattern(), newSchoolIDPattern);
        }

        Assertions.assertTrue(optionalSchoolDetails.isPresent());
    }

    @Test
    void schoolIDPattern(){
        String newPattern= "=";
        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());

        if (optionalSchoolDetails.isPresent()) {
            schoolDetailsDao.updateEmployeeIDPattern(optionalSchoolDetails.get(), newPattern );
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