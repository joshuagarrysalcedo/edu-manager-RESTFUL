package ph.jsalcedo.edumanager.entity.school.curriculum;

import org.springframework.transaction.annotation.Transactional;
import ph.jsalcedo.edumanager.entity.school.School;

import java.util.List;

/**
 * <h1> CurriculumService</h1>
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
public interface CurriculumService {

    List<Curriculum> findAllCurriculumBySchool(School school);


    Curriculum findBySchoolAndCurriculumName(School finalSchool, String deletedSchoolName);

    boolean existsById(Long id);

    @Transactional
    void deleteCurriculumByID(Long id);

    Curriculum findByID(Long l);
}
