package ph.jsalcedo.edumanager.entity.institution;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import ph.jsalcedo.edumanager.entity.school.School;

import java.util.ArrayList;
import java.util.List;

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
//    @GenericGenerator(
//            name = "institution_sequence",
//            strategy = "ph.jsalcedo.edumanager.utils.sequence.ResettableSequenceStyleGenerator",
//            parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "1")
//            ,@org.hibernate.annotations.Parameter(name = "initialize_context", value = "1")}
//    )
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






}
