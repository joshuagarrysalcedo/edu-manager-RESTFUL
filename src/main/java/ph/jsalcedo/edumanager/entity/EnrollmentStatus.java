package ph.jsalcedo.edumanager.data.models.entity.school.enrollmentStatus;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails.SchoolDetails;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EnrollmentStatus {
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
    private String enrollmentStatus;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SchoolDetails.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "schoolDetails_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private SchoolDetails schoolDetails;

//    @JsonIgnore
//    public SchoolDetails getSchoolDetails() {
//        return schoolDetails;
//    }
//
//    @JsonIgnore
//    public void setSchoolDetails(SchoolDetails schoolDetails) {
//        this.schoolDetails = schoolDetails;
//    }



}
