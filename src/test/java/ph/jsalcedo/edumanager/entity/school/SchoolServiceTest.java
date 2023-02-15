package ph.jsalcedo.edumanager.entity.school;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.institution.InstitutionRepository;
import ph.jsalcedo.edumanager.entity.institution.InstitutionService;
import ph.jsalcedo.edumanager.exception.DuplicateSchoolNameException;

@SpringBootTest
class SchoolServiceTest {
    private final SchoolService schoolService;
    private final SchoolRepository schoolRepository;
    private final InstitutionService institutionService;
    private Institution institution;
    private final InstitutionRepository institutionRepository;

    @Autowired
    public SchoolServiceTest(SchoolService schoolService, SchoolRepository schoolRepository, InstitutionService institutionService, InstitutionRepository institutionRepository) {
        this.schoolService = schoolService;
        this.schoolRepository = schoolRepository;
        this.institutionService = institutionService;
        this.institutionRepository = institutionRepository;
    }

    @BeforeEach
    void initialize(){
        this.institution = Institution.builder("test institution");
        institutionService.add(this.institution);
    }
    @AfterEach
    void reset(){
        schoolRepository.deleteAll();
        institutionRepository.deleteAll();
    }


}