package ph.jsalcedo.edumanager.entity.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.utils.models.enums.Student_Status;
import ph.jsalcedo.edumanager.utils.models.person.Name;
import ph.jsalcedo.edumanager.utils.models.person.Person;


import java.util.UUID;
@Getter
@ToString
@Setter
@Entity
@Table
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaAuditing
@EqualsAndHashCode
public  class Student extends Person {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;


    @Enumerated(EnumType.STRING)
    private Student_Status studentStatus;

    @Embedded
    private Name name;


    @ManyToOne(targetEntity = School.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private School school;

    @ManyToOne(targetEntity = Curriculum.class, cascade = CascadeType.ALL)
    @JoinColumn(name="curriculum_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Curriculum curriculum;
    @Override
    public String toString() {
        return String.format("ID: %s Name: %s Curriculum: %s",
                this.id,
                this.name.toString(),
                this.curriculum == null ? "Null" : curriculum.getCurriculumName()
                );
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }


    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
}
