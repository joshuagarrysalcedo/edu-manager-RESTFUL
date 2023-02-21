package ph.jsalcedo.edumanager.entity.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.configuration.JwtService;
import ph.jsalcedo.edumanager.entity.appuser.AppUser;
import ph.jsalcedo.edumanager.entity.appuser.AppUserRepository;
import ph.jsalcedo.edumanager.entity.appuser.AppUserServiceImpl;
import ph.jsalcedo.edumanager.entity.institution.Institution;
import ph.jsalcedo.edumanager.utils.models.person.Name;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AppUserServiceImpl appUserService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(Name.builder()
                        .firstName(request.getName().getFirstName())
                        .lastName(request.getName().getLastName())
                        .build())
                .country(request.getCountry())
                .companyName(request.getCompanyName())
                .appUserRole(request.getAppUserRole())
                .institution(Institution.builder()
                        .institutionName(request.getCompanyName())
                        .build())
                .enabled(true)
                .locked(false)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        System.out.println();
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken
                            (request.getEmail(), request.getPassword())
            );

           final UserDetails appUser = appUserService.loadUserByUsername(request.getEmail());
            var jwtToken = jwtService.generateToken(appUser);

            //   System.out.println(jwtToken);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }
}
