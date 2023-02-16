

package ph.jsalcedo.edumanager.entity.school;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ph.jsalcedo.edumanager.entity.institution.Institution;
/**
 *<h1> School </h1>
 *<p>This class implements the school entity for the application</p>
 * <p>This class has a Many-to-One relation ship from the {@link Institution} class</p>
 *
 * <b>Note:</b> School name of the same institution must not be duplicate!
 * <br/>
 * <h2>@created 16/02/2023 - 4:30 am</h2>
 * <h2>@author Joshua Salcedo</h2>
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table
@Builder
@ToString

public class School {
    @Id
    @SequenceGenerator(
            name = "school_sequence",
            sequenceName = "school_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "school_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Institution.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Institution institution;

    private String schoolName;

}
