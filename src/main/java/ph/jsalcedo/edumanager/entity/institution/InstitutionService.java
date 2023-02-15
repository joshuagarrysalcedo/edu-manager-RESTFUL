package ph.jsalcedo.edumanager.entity.institution;

import org.springframework.transaction.annotation.Transactional;
import ph.jsalcedo.edumanager.entity.school.School;

import java.util.List;
import java.util.Optional;

public interface InstitutionService {
    @Transactional
    void add(Institution institution);
    int count();

    List<Institution> all();
    @Transactional
    void deleteInstitution(Long id);
    @Transactional
    void update(Institution institution);

    Institution getInstitution(Long id);

    @Transactional

    void addSchool(Institution institution, School school);

}
