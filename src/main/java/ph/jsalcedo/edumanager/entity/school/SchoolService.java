package ph.jsalcedo.edumanager.entity.school;

import ph.jsalcedo.edumanager.entity.institution.Institution;

import java.util.List;

public interface SchoolService {

    void updateSchool(School school);


    List<School> findSchoolsByInstitution(Institution institution);
    School findSchoolByInstitutionAndName(Institution institution , String name);

    School findSchoolByID(Long id);

    List<School> findAllByInstitution(Institution institution);

    void deleteById(Long id);
}
