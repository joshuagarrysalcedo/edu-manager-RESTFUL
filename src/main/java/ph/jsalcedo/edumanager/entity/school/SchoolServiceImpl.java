package ph.jsalcedo.edumanager.entity.school;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.exception.DuplicateSchoolNameException;

@Service
@AllArgsConstructor
public class SchoolServiceImpl implements SchoolService{
    private final SchoolRepository schoolRepository;

    @Override
    public void add(Institution institution, School school) {

    }
}
