package ph.jsalcedo.edumanager.entity.school.schooldetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import ph.jsalcedo.edumanager.entity.school.enrollmentstatus.EnrollmentStatus;
import ph.jsalcedo.edumanager.utils.models.person.Address;

import java.util.*;

@Entity
@Table
@Getter
@Setter
@Builder
public class SchoolDetails {
    @Id
    @SequenceGenerator(
            name = "school_details_sequence",
            sequenceName = "school_details_sequence",
            allocationSize = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "school_details_sequence"
    )
    private Long id;
    @Nonnull
    private String schoolName;
    @Temporal(TemporalType.DATE)
    @Nullable
    private Date foundedOn;
    @Nullable
    private String founder;
    @Embedded
    Address schoolAddress;

    private String studentIDPattern;
    private String employeeIDPattern;
    private String schoolDomain;

    @JsonManagedReference
    @OneToMany(mappedBy = "schoolDetails", targetEntity = EnrollmentStatus.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    private  Set<EnrollmentStatus> enrollmentStatuses = new HashSet<>();



    public SchoolDetails(Long id, String schoolName, Date foundedOn, String founder, Address schoolAddress, String studentIDPattern, String employeeIDPattern, String schoolDomain, Set<EnrollmentStatus> enrollmentStatuses) {
        this.id = id;
        this.schoolName = schoolName;
        this.foundedOn = foundedOn;
        this.founder = founder;
        this.schoolAddress = schoolAddress;
        this.studentIDPattern = studentIDPattern;
        this.employeeIDPattern = employeeIDPattern;
        this.schoolDomain = schoolDomain;
        this.enrollmentStatuses = enrollmentStatuses;
    }

    public SchoolDetails() {

//        ph.jsalcedo.edumanager.data.models.enums.EnrollmentStatus[] enrollmentStatus = ph.jsalcedo.edumanager.data.models.enums.EnrollmentStatus.values();
//
//        for (ph.jsalcedo.edumanager.data.models.enums.EnrollmentStatus status : enrollmentStatus) {
//            enrollmentStatuses.add(new EnrollmentStatus(
//                    StringFormatter.screamingSnakeCaseToCapitalize(status.name()),
//                    status.getDescription(),
//                    this));
//        }


    }


    public SchoolDetails(String schoolName, Date foundedOn, String founder, Address schoolAddress, String studentIDPattern, String employeeIDPattern, String schoolDomain, Set<EnrollmentStatus> enrollmentStatuses) {
        this.schoolName = schoolName;
        this.foundedOn = foundedOn;
        this.founder = founder;
        this.schoolAddress = schoolAddress;
        this.studentIDPattern = studentIDPattern;
        this.employeeIDPattern = employeeIDPattern;
        this.schoolDomain = schoolDomain;
        this.enrollmentStatuses = enrollmentStatuses;
    }




}
