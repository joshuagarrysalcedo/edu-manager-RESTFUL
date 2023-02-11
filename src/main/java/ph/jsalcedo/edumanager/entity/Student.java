package ph.jsalcedo.edumanager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ph.jsalcedo.edumanager.utils.models.enums.Student_Status;
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
public class Student extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Enumerated(EnumType.STRING)
    private Student_Status studentStatus;
    private String course;




}
