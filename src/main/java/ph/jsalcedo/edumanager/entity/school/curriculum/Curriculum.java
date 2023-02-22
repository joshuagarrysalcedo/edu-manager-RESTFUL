package ph.jsalcedo.edumanager.entity.school.curriculum;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.student.Student;

import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private School school;



    @OneToMany(
            mappedBy = "curriculum",
            targetEntity = Student.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Student> studentList = new ArrayList<>();
    @JsonIgnore
    public List<Student> getStudentList() {
        return studentList;
    }

    @JsonIgnore
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", curriculumName='" + curriculumName + '\'' +
                ", school=" + (school.getSchoolName() == null ? "NULL" : school.getSchoolName()) +
                ", enrolled students= " + (studentList == null ? "NULL" : studentList.size()) +
                ", Students = " + studentList +
                '}';
    }
}
