package ph.jsalcedo.edumanager.utils.models.person;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Name {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    public Name() {
    }

    @Override
    public String toString() {
        return String.format("%s %s. %s", getFirstName(), getMiddleName().charAt(0), getLastName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return firstName.equalsIgnoreCase(name.firstName) && middleName.equalsIgnoreCase(name.middleName) && lastName.equalsIgnoreCase(name.lastName);
    }


}