package ph.jsalcedo.edumanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ph.jsalcedo.edumanager.repository.SchoolDetailsRepository;
import ph.jsalcedo.edumanager.utils.models.enums.EnrollmentStatus;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(SchoolDetailsRepository schoolDetailsRepository){
        return args -> {

//            EnrollmentStatus status = new EnrollmentStatus("UN ENROLLED", "Student who is not enrolled");
//
//
//            schoolDetailsRepository.save( SchoolDetails.builder()
//                    .schoolDomain("sample-school.com")
//                    .foundedOn(new Date())
//                    .founder("Juan Tamad")
//                    .schoolAddress(new Address().builder().city("Cagayan de Oro City").build())
//                    .schoolName("sample school university")
//                    .build());
////            Optional<SchoolDetails> schoolDetails = schoolDetailsRepository.findBySchoolName("sample school university");
////            schoolDetails.ifPresent(details -> details.getEnrollmentStatuses().add(status));
//
//
//
//
//
//            schoolDetailsRepository.save( SchoolDetails.builder()
//                    .schoolDomain("swaswa.com")
//                    .foundedOn(new Date())
//                    .founder("Swal di bisnoy")
//                    .schoolAddress(new Address().builder().city("Cagayan de Oro City").build())
//                    .schoolName("swaswa university")
//                    .build());


            EnrollmentStatus[] enrollmentStatus = EnrollmentStatus.values();

//            for (ph.jsalcedo.edumanager.data.models.enums.EnrollmentStatus status : enrollmentStatus) {
//                schoolDetails.getEnrollmentStatuses().add(new EnrollmentStatus(
//                        StringFormatter.screamingSnakeCaseToCapitalize(status.name()),
//                        status.getDescription(),
//                        schoolDetails));
//
//                other.getEnrollmentStatuses().add(new EnrollmentStatus(
//                        StringFormatter.screamingSnakeCaseToCapitalize(status.name()),
//                        status.getDescription(),
//                        schoolDetails));
//            }
        };
    }
}
