/**
*
* The Person class represents a general model of a person in the system.
* It is annotated with @MappedSuperclass to indicate that it is meant to be a base class for entity classes.
* The class uses Lombok annotations (@Getter, @Setter and @Builder) for generating boilerplate code for getters, setters and builder pattern.
* The class has various fields to store the person's details such as name, contact details, birth details, gender, nationality, marital status, religion and address.
* The class also contains 4 inner classes (Name, ContactDetails, BirthDetails, Address) which are annotated with @Embeddable.
* These inner classes are used to store more specific information about the person and are embedded within the main person object.
* These inner classes also use Lombok annotations for generating boilerplate code.
 * */



package ph.jsalcedo.edumanager.data.models.person;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import ph.jsalcedo.edumanager.data.models.enums.Gender;
import ph.jsalcedo.edumanager.data.models.enums.MaritalStatus;
import ph.jsalcedo.edumanager.data.models.enums.Nationality;


import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
public  class Person {
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    //name
    @Embedded
    private Name name;

    @Embedded
    private ContactDetails contactDetails;

    @Embedded
    private BirthDetails birthDetails;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private String religion;
    @Embedded
    private Address address;





    public Person() {
    }
//
//    @Embeddable
//    @Builder
//    public  class Name{
//        @Column(name = "first_name")
//        private String firstName;
//
//        @Column(name = "middle_name")
//        private String middleName;
//
//        @Column(name = "last_name")
//        private String lastName;
//
//        public Name() {
//        }
//    }
//
//
//
//
//
//
//
//
//
//    @Embeddable
//    @Builder
//    public static class ContactDetails {
//        @Column(name = "phone_number")
//        private String phoneNumber;
//
//        @Column(name = "email")
//        private String email;
//
//        public ContactDetails() {
//        }
//    }
//
//
//
//
//
//
//
//
//
//    @Embeddable
//    static
//    class BirthDetails {
//        @Column(name = "birth_date")
//        @Temporal(TemporalType.DATE)
//        private Date birthDate;
//
//        public BirthDetails() {
//        }
//    }
//    @Embeddable
//    static class Address {
//        @Column(name = "street_address")
//        private String streetAddress;
//
//        @Column(name = "city")
//        private String city;
//
//        @Column(name = "state_or_province")
//        private String stateOrProvince;
//
//        @Column(name = "zip_or_postal_code")
//        private String zipOrPostalCode;
//
//        @Column(name = "country")
//        private String country;
//
//        public Address() {
//        }
//    }
}
