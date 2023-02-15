package ph.jsalcedo.edumanager.utils.models.entity.school;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ph.jsalcedo.edumanager.entity.school.schooldetails.SchoolDetails;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MaritalStatus {
    @Id
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator"
    )
    private Long id;
    private String maritalStatus;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SchoolDetails.class)
    @JoinColumn(name = "schoolDetails_id", unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SchoolDetails schoolDetails;

    @JsonIgnore
    public SchoolDetails getSchoolDetails() {
        return schoolDetails;
    }

    @JsonIgnore
    public void setSchoolDetails(SchoolDetails schoolDetails) {
        this.schoolDetails = schoolDetails;
    }
}
