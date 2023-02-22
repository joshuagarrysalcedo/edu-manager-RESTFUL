package ph.jsalcedo.edumanager.entity.registerrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1> CurriculumRemoveRequest</h1>
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
 * @created 22/02/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurriculumRemoveRequest {

    private Long schoolID;
    private String curriculumName;
}
