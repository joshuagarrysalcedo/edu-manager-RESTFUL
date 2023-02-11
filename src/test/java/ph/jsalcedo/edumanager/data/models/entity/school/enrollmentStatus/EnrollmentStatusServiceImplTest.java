package ph.jsalcedo.edumanager.data.models.entity.school.enrollmentStatus;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails.SchoolDetails;
import ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails.SchoolDetailsDao;
import ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails.SchoolDetailsRepository;
import ph.jsalcedo.edumanager.data.models.person.Address;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest
@Slf4j
class EnrollmentStatusDaoImplTest {
    private final EnrollmentStatusDaoImpl impl;
    private final SchoolDetailsRepository repository;
    private final SchoolDetails schoolDetails;
    private final EnrollmentStatusRepository enrollmentStatusRepository;

    private final EnrollmentStatus enrollmentStatus;
    private final SchoolDetailsRepository schoolDetailsRepository;
    private final SchoolDetailsDao schoolDetailsDao;
    private final EnrollmentStatus status;

    @Autowired
    public EnrollmentStatusDaoImplTest(EnrollmentStatusDaoImpl impl, SchoolDetailsRepository repository, EnrollmentStatusRepository enrollmentStatusRepository, SchoolDetailsRepository schoolDetailsRepository, SchoolDetailsDao schoolDetailsDao) {
        this.impl = impl;
        this.repository = repository;
        this.enrollmentStatusRepository = enrollmentStatusRepository;
        this.schoolDetailsRepository = schoolDetailsRepository;
        this.schoolDetailsDao = schoolDetailsDao;
        this.schoolDetails =
                SchoolDetails.builder()
                .schoolDomain("sample-school.com")
                .foundedOn(new Date())
                .founder("Juan Tamad")
                .schoolAddress(Address.builder().city("Cagayan de Oro City").build())
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
        schoolDetailsRepository.save(schoolDetails);


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

        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());
        Assertions.assertTrue(optionalSchoolDetails.isPresent());
        if(optionalSchoolDetails.isPresent()){
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
        }



    @Test
    void deleteEnrollmentStatus() {

        Optional<SchoolDetails> optionalSchoolDetails = schoolDetailsRepository.findBySchoolName(schoolDetails.getSchoolName());
        if(optionalSchoolDetails.isPresent()) {
            impl.addEnrollmentStatus(optionalSchoolDetails.get(), enrollmentStatus);
            Assertions.assertEquals(1, optionalSchoolDetails.get().getEnrollmentStatuses().size());
            Assertions.assertTrue(enrollmentStatusRepository.findByEnrollmentStatus(enrollmentStatus.getEnrollmentStatus()).isPresent());

            impl.deleteEnrollmentStatus(optionalSchoolDetails.get(), enrollmentStatus);

            Assertions.assertEquals(0, optionalSchoolDetails.get().getEnrollmentStatuses().size());
            Assertions.assertEquals(0, enrollmentStatusRepository.findAll().size());
        }

    }

    @Test
    void getEnrollmentStatus() {
    }

    @Test
    void getAllEnrollmentStatus() {
    }
}