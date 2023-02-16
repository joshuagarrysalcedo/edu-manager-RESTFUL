package ph.jsalcedo.edumanager.entity.school;

import ph.jsalcedo.edumanager.entity.institution.Institution;

public interface SchoolService {

    void updateSchool(School school);
    void deleteSchool(Long schoolID);

    void findSchoolByInstitution(Institution institution);
    School findSchoolByInstitutionAndName(Institution institution , String name);

    School findSchoolByID(Long id);
}
