package ph.jsalcedo.edumanager.entity.school.schoolDetails;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.school.schooldetails.SchoolDetailsRepository;
import ph.jsalcedo.edumanager.utils.models.person.Address;
import ph.jsalcedo.edumanager.entity.school.schooldetails.SchoolDetails;

import java.util.Date;

@SpringBootTest
class SchoolDetailsTest {
    private final SchoolDetailsRepository schoolDetailsRepository;

    @Autowired
    public SchoolDetailsTest(SchoolDetailsRepository schoolDetailsRepository) {
        this.schoolDetailsRepository = schoolDetailsRepository;
    }

    @Test
    void getEnrollmentStatuses() {
        schoolDetailsRepository.save( SchoolDetails.builder()
                .schoolDomain("sample-school.com")
                .foundedOn(new Date())
                .founder("Juan Tamad")
                .schoolAddress(Address.builder("Cagayan de Oro City", "Philippines").build())
                .schoolName("sample school university")
                .build());
       Assertions.assertNotNull(schoolDetailsRepository.findBySchoolName("sample school university").get().getEnrollmentStatuses());

    }


}