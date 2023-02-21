

package ph.jsalcedo.edumanager.entity.school;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.entity.student.Student;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Institution.class)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Institution institution;

    private String schoolName;

    @JsonManagedReference
    @OneToMany
            (mappedBy = "school"
                    , targetEntity = Curriculum.class
                    , fetch = FetchType.EAGER
                    , cascade = CascadeType.ALL
                    , orphanRemoval = true)
    private List<Curriculum> curriculum = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(
            mappedBy = "school",
            targetEntity = Student.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", institution=" + (institution.getInstitutionName() == null ? "NULL": institution.getInstitutionName())  +
                ", schoolName='" + schoolName + '\'' +
                ", curriculum=" + (curriculum == null ? "NULL" : curriculum) +
                '}';
    }

    @JsonIgnore
    public Institution getInstitution() {
        return institution;
    }

    @JsonIgnore
    public List<Student> getStudents() {
        return students;
    }
    @JsonIgnore
    public void setCurriculum(List<Curriculum> curriculum) {
        this.curriculum = curriculum;
    }
    @JsonIgnore
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
