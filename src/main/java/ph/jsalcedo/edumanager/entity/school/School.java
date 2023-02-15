package ph.jsalcedo.edumanager.entity.school;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ph.jsalcedo.edumanager.entity.institution.Institution;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table
@Builder
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Institution.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Institution institution;

    private String schoolName;

}
