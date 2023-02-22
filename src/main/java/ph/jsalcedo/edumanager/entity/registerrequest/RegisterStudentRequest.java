package ph.jsalcedo.edumanager.entity.registerrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.jsalcedo.edumanager.utils.models.enums.Gender;
import ph.jsalcedo.edumanager.utils.models.enums.MaritalStatus;
import ph.jsalcedo.edumanager.utils.models.enums.Nationality;
import ph.jsalcedo.edumanager.utils.models.enums.Student_Status;

import java.util.Date;

/**
 * <h1> RegisterStudentRequest</h1>
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
 * @created 22/02/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterStudentRequest {

    private String firstName;
    private String middleName;
    private String lastName;

    private String phoneNUmber;
    private String email;

    private Date birthDate;

    private Gender gender;
    private Nationality nationality;

    private MaritalStatus maritalStatus;

    private String religion;

    private String city;
    private String country;
    private String stateOrProvince;
    private String streetAddress;
    private String zipOrPostalCode;

    private Student_Status studentStatus;

}
