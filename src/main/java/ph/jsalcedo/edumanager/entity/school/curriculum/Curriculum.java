package ph.jsalcedo.edumanager.entity.school.curriculum;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.school.School;

/**
 * <h1> Curriculum</h1>
 * A curriculum is a set of courses, lessons, and learning activities that are designed to help students achieve specific learning goals or objectives. It is essentially a plan for teaching and learning that guides what students will learn, how they will learn it, and how progress and achievement will be assessed. A curriculum typically includes a description of the subjects and topics to be covered, the instructional methods and materials that will be used, the expected learning outcomes for students, and the criteria for evaluating student progress and achievement. Curricula may be developed at various levels, including for individual courses, for a whole program of study, or for an entire educational institution. They are often shaped by educational standards, local or national educational policies, and the needs and interests of students and their communities.
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table
@Builder

public class Curriculum {

    @Id
    @SequenceGenerator(
            name = "curriculum_sequence",
            sequenceName = "curriculum_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "curriculum_sequence"
    )
    private Long id;

    private String curriculumName;

    @ManyToOne(targetEntity = School.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private School school;

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", curriculumName='" + curriculumName + '\'' +
                ", school=" + (school.getSchoolName() == null ? "NULL" : school.getSchoolName()) +
                '}';
    }
}
