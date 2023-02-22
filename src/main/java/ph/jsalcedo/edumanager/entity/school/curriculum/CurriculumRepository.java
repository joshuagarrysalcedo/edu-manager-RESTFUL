package ph.jsalcedo.edumanager.entity.school.curriculum;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ph.jsalcedo.edumanager.entity.school.School;

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
@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    /**
     * <h1>findAllBySchool</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param school
     * @return
     * @author Joshua Salcedo
     * @created 17/02/2023 - 4:00 pm
     */
        List<Curriculum> findAllBySchool(School school);

        @Modifying

        void deleteCurriculumById( Long Id);
        Optional<Curriculum> findCurriculumBySchoolAndCurriculumName(School school, String curriculumName);


}
