package ph.jsalcedo.edumanager.utils.models.person;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ph.jsalcedo.edumanager.utils.models.enums.ErrorMessage;

@Embeddable
@Getter
@Setter
@Builder(builderMethodName = "hiddenBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Column(name = "street_address")
    @Size(min = 2, message = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE)

    private String streetAddress;

    @Column(name = "city")
    @NotNull(message = ErrorMessage.Constants.EMPTY_FIELD_REQUIRED_MESSAGE)
    @Size(min = 2, message = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE)

    private String city;

    @Column(name = "state_or_province")
    @Size(min = 2, message = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE)

    private String stateOrProvince;

    @Column(name = "zip_or_postal_code")
    @Size(min = 2, message = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE)

    private String zipOrPostalCode;

    @Column(name = "country")
    @NotNull(message = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE)
    @Size(min = 2, message = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE)

    private String country;


    public void setStreetAddress(String streetAddress) {
        validateLength(streetAddress);
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        validateLength(city);
        this.city = city;
    }

    public void setStateOrProvince(String stateOrProvince) {
        validateLength(stateOrProvince);
        this.stateOrProvince = stateOrProvince;
    }

    public void setZipOrPostalCode(String zipOrPostalCode) {
        validateLength(zipOrPostalCode);
        this.zipOrPostalCode = zipOrPostalCode;
    }

    public void setCountry(String country) {
        validateLength(country);
        this.country = country;
    }

    public static AddressBuilder builder(String city, String country){
       return hiddenBuilder().city(city).country(country);
   }

   private void validateLength(String var){

        if(var.isBlank()){
           throw new NullPointerException(ErrorMessage.Constants.EMPTY_FIELD_REQUIRED_MESSAGE);
       }
       if(var.length() < 2){
            throw new IllegalArgumentException(ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE);
        }


   }

}