package ph.jsalcedo.edumanager.controller.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.jsalcedo.edumanager.utils.models.enums.AppUserRole;
import ph.jsalcedo.edumanager.utils.models.person.Name;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private Name name;
    private String companyName;
    private String country;
    private boolean locked;
    private boolean enabled;
    private AppUserRole appUserRole;

}
