package ph.jsalcedo.edumanager.entity.school.enrollmentStatus;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.appuser.AppUserRepository;
import ph.jsalcedo.edumanager.entity.school.schooldetails.SchoolDetails;
import ph.jsalcedo.edumanager.entity.school.enrollmentstatus.EnrollmentStatusServiceImpl;
import ph.jsalcedo.edumanager.entity.school.enrollmentstatus.EnrollmentStatusRepository;
import ph.jsalcedo.edumanager.entity.school.schooldetails.SchoolDetailsService;
import ph.jsalcedo.edumanager.entity.school.schooldetails.SchoolDetailsRepository;
import ph.jsalcedo.edumanager.utils.models.person.Address;
import ph.jsalcedo.edumanager.entity.school.enrollmentstatus.EnrollmentStatus;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@Slf4j
class EnrollmentStatusServiceImplTest {
    private final EnrollmentStatusServiceImpl impl;
    private final SchoolDetailsRepository repository;
    private  SchoolDetails schoolDetails;
    private final EnrollmentStatusRepository enrollmentStatusRepository;

    private  EnrollmentStatus enrollmentStatus;

    private final SchoolDetailsService schoolDetailsService;
    private  EnrollmentStatus status;
    private final AppUserRepository appUserRepository;
    @Autowired
    public EnrollmentStatusServiceImplTest(EnrollmentStatusServiceImpl impl, SchoolDetailsRepository repository, EnrollmentStatusRepository enrollmentStatusRepository, SchoolDetailsService schoolDetailsService, AppUserRepository appUserRepository) {
        this.impl = impl;
        this.repository = repository;
        this.enrollmentStatusRepository = enrollmentStatusRepository;

        this.schoolDetailsService = schoolDetailsService;

        this.appUserRepository = appUserRepository;
    }

    @BeforeEach
    void initialize(){
        appUserRepository.deleteAll();
        this.schoolDetails =
                SchoolDetails.builder()
                        .schoolDomain("sample-school.com")
                        .foundedOn(new Date())
                        .founder("Juan Tamad")
                        .schoolAddress(Address.builder("Cagayan de Oro City", "Philippines").build())
                        .schoolName("sample school university")
                        .build();
        this.enrollmentStatus = EnrollmentStatus
                .builder()
                .enrollmentStatus("PENDING")
                .description("student who is pending in enrollment")
                .build();
        this.status = EnrollmentStatus
                .builder()
                .enrollmentStatus("UN-ENROLLED")
                .description("Student who is not enrolled")
                .build();
        schoolDetailsService.addSchoolDetails(schoolDetails);
    }
    @AfterEach
    void reset(){
        schoolDetailsService.deleteAll();
    }


    @Test
    void updateEnrollmentStatusTest() {

//        String name = "new Status";
//
//        EnrollmentStatus enrollmentStatus = new EnrollmentStatus("Enrolled", "Enrolled status");
//        impl.updateEnrollmentStatus(enrollmentStatus, name, null);
//
//        Assertions.assertEquals(enrollmentStatus.getEnrollmentStatus(), name);

    }


    @Test
    void addEnrollmentStatus() {

        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsService.findSchoolDetails(schoolDetails.getSchoolName());
        Assertions.assertTrue(optionalSchoolDetails.isPresent());
        impl.addEnrollmentStatus(optionalSchoolDetails.get(), status);
        Assertions.assertNotNull(optionalSchoolDetails.get().getEnrollmentStatuses());
        Assertions.assertTrue(optionalSchoolDetails.get().getEnrollmentStatuses().size() > 0);
        Optional<EnrollmentStatus> optionalEnrollmentStatus = enrollmentStatusRepository.findByEnrollmentStatus("UN-ENROLLED");
        Assertions.assertTrue(optionalEnrollmentStatus.isPresent());
        Assertions.assertEquals(optionalSchoolDetails.get().getId(), optionalEnrollmentStatus.get().getSchoolDetails().getId());
//                Assertions.assertTrue(optionalSchoolDetails.get()
//                        .getEnrollmentStatuses()
//                        .stream().anyMatch(u-> u.getEnrollmentStatus()
//                                .equalsIgnoreCase(status
//                                        .getEnrollmentStatus()) && u.getSchoolDetails().equals(optionalSchoolDetails.get())));
    }



    @Test
    void deleteEnrollmentStatus() {

        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsService.findSchoolDetails(schoolDetails.getSchoolName());
        Assertions.assertTrue(optionalSchoolDetails.isPresent());

        impl.addEnrollmentStatus(optionalSchoolDetails.get(), enrollmentStatus);
        Assertions.assertEquals(1, optionalSchoolDetails.get().getEnrollmentStatuses().size());
        Assertions.assertTrue(enrollmentStatusRepository.findByEnrollmentStatus(enrollmentStatus.getEnrollmentStatus()).isPresent());
        Optional<EnrollmentStatus> optionalEnrollmentStatus = impl.getEnrollmentStatus(optionalSchoolDetails.get(), enrollmentStatus.getEnrollmentStatus());
        Assertions.assertTrue(optionalEnrollmentStatus.isPresent());

        impl.deleteEnrollmentStatus(optionalSchoolDetails.get(), enrollmentStatus);
//        optionalSchoolDetails.get().getEnrollmentStatuses().remove(enrollmentStatus);
        Assertions.assertEquals(0, optionalSchoolDetails.get().getEnrollmentStatuses().size());
        System.out.println("SIZE = " + enrollmentStatusRepository.findAll().size());
//        Assertions.assertEquals(0, enrollmentStatusRepository.findAll().size());

    }

    @Test
    void getEnrollmentStatus() {
    }

    @Test
    void getAllEnrollmentStatus() {
    }
}