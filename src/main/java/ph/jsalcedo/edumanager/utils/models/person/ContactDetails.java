package ph.jsalcedo.edumanager.data.models.person;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContactDetails {
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    public ContactDetails() {
    }
}