package ph.jsalcedo.edumanager.entity.institution;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import ph.jsalcedo.edumanager.entity.school.School;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table
@EqualsAndHashCode
@Builder(builderMethodName = "hiddenBuilder")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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



    public static Institution builder(String name) {
        return hiddenBuilder().institutionName(name).schools(new ArrayList<>()).build();
    }


}
