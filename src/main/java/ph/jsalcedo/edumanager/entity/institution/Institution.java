package ph.jsalcedo.edumanager.entity.institution;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.springframework.security.core.userdetails.UserDetails;
import ph.jsalcedo.edumanager.entity.school.School;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

/**
 * <h1> Institution Entity</h1>
 * <section>
 * <h3>Description</h3>
 * <ul>
 *   <li>The "Institution" class represents a school or educational organization as a whole, which can have multiple schools or branches within it.</li>
 *   <li>This relationship can be used to store and manage information about the user associated with the institution, such as their name, contact information, login credentials, and other relevant details. </li>
 *   <li>the "Institution" class has a one-to-one relationship with the {@link ph.jsalcedo.edumanager.entity.appuser.AppUser AppUser} class</li>
 * </ul>
 * </section>
 *
 * @author Joshua Salcedo
 * @version 1.0(COMPLETE)
 * @created 15/02/2023
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table
@EqualsAndHashCode
@Builder
public class Institution {
    @Id
    @SequenceGenerator(
            name = "institution_sequence",
            sequenceName = "institution_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "institution_sequence"
    )
    private  Long id;

    private String institutionName;

    @JsonManagedReference
    @OneToMany
            (mappedBy = "institution"
            , targetEntity = School.class
            , fetch = FetchType.EAGER
            , cascade = CascadeType.ALL
            , orphanRemoval = true)
    private List<School> schools;

    //TODO add a One-To-One Relationship with AppUser
}
