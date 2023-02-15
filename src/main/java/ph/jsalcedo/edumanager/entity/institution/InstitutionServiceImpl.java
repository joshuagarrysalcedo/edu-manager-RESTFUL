package ph.jsalcedo.edumanager.entity.institution;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.SchoolRepository;
import ph.jsalcedo.edumanager.exception.DuplicateSchoolNameException;
import ph.jsalcedo.edumanager.exception.InstitutionNotFoundException;
import ph.jsalcedo.edumanager.utils.models.enums.ErrorMessage;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@AllArgsConstructor
@Qualifier("institutionService")
public class InstitutionServiceImpl implements InstitutionService{

    private final InstitutionRepository institutionRepository;
    private final SchoolRepository schoolRepository;
    @Override
    public void add(Institution institution){
        if((isNotValid(institution.getInstitutionName()))){
            throw new IllegalStateException(ErrorMessage.Constants.INVALID_NAME);
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

    }

    @Override
    public Institution getInstitution(Long id) {
        return null;
    }

    @Override
    public void addSchool(Institution institution, School school) {
        List<School> schools = schoolRepository.findAllByInstitution(institution);
        schools.forEach(e->{
            if(e.getSchoolName() .equalsIgnoreCase(school.getSchoolName()))
                throw new DuplicateSchoolNameException(e.getSchoolName());
        });

        school.setInstitution(institution);
        institution.getSchools().add(school);
        institutionRepository.save(institution);

    }


    private boolean isNotValid(String institutionName) {
        String regex = "^[A-Za-z][A-Za-z0-9&\\s.'()-]{0,99}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(institutionName);

        return !matcher.find();
    }
}
