package ph.jsalcedo.edumanager.entity.institution;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.SchoolRepository;
import ph.jsalcedo.edumanager.exceptions.exception.CustomInvalidNameException;
import ph.jsalcedo.edumanager.exceptions.exception.DuplicateSchoolNameException;
import ph.jsalcedo.edumanager.exceptions.exception.InstitutionNotFoundException;
import ph.jsalcedo.edumanager.utils.NameChecker;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Qualifier("institutionService")
public class InstitutionServiceImpl implements InstitutionService{

    private final InstitutionRepository institutionRepository;
    private final SchoolRepository schoolRepository;
    @Override
    public void add(Institution institution){
        if((!NameChecker.isNameValid((institution.getInstitutionName())))){
            throw new CustomInvalidNameException(institution.getInstitutionName());
        }
        institutionRepository.save(institution);
    }

    @Override
    public int count() {
        return institutionRepository.findAll().size();
    }

    @Override
    public List<Institution> all() {
        return institutionRepository.findAll();
    }

    @Override
    public void deleteInstitution(Long id) {
        if(!institutionRepository.existsById(id))
            throw new InstitutionNotFoundException(id);

        institutionRepository.deleteById(id);
    }

    @Override
    public void update(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public Institution getInstitution(Long id) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if(optionalInstitution.isEmpty())
            throw new InstitutionNotFoundException(id);
        return optionalInstitution.get();
    }

    @Override
    public void addSchool(Long institutionID, School school) {
        Institution institution = getInstitution(institutionID);
        List<School> schools = schoolRepository.findAllByInstitution(institution);
        schools.forEach(e->{
            if(e.getSchoolName() .equalsIgnoreCase(school.getSchoolName()))
                throw new DuplicateSchoolNameException(e.getSchoolName());
        });

        school.setInstitution(institution);
        institution.getSchools().add(school);
        institutionRepository.save(institution);

    }




}
