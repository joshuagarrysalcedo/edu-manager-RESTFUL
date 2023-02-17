package ph.jsalcedo.edumanager.entity.school;

import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;

import java.util.List;
import java.util.Optional;

/**
 * <h1> ${NAME}</h1>
 * <section>
 * <h3>Description</h3>
 * <ul>
 *   <li>????</li>
 *   <li>????</li>
 *   <li>????</li>
 * </ul>
 * </section>
 *
 * @author Joshua Salcedo
 * @version 1.0(INCOMPLETE)
 * @created 17/02/2023
 */
public interface SchoolService {

    void updateSchool(School school);


    List<School> findSchoolsByInstitution(Institution institution);
    School findSchoolByInstitutionAndName(Institution institution , String name);

    School findSchoolByID(Long id);

    School findLatestSchoolByInstitution(Institution institution);
    School findLatestSchool();



    List<School> findAllByInstitution(Institution institution);

    void deleteSchoolById(Long id);



    List<Curriculum> getAllCurriculum();

    School addCurriculum(Long schoolID, Curriculum curriculum);
    School deleteCurriculum(Long schoolID, Curriculum curriculum);

}
