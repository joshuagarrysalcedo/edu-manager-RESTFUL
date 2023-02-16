package ph.jsalcedo.edumanager.entity.institution;

import org.springframework.transaction.annotation.Transactional;
import ph.jsalcedo.edumanager.entity.school.School;

import java.util.List;

public interface InstitutionService {
    @Transactional
    void create(Institution institution);
    int count();

    List<Institution> all();
    @Transactional
    void deleteInstitution(Long id);
    @Transactional
    void update(Institution institution);


    Institution getInstitution(Long institutionID);

    @Transactional
    void addSchool(Long id, School school);


    void deleteSchool(Long id , School school);
}
