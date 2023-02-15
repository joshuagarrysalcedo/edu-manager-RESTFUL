package ph.jsalcedo.edumanager.configuration;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import ph.jsalcedo.edumanager.controller.auth.AuthenticationRequest;
import ph.jsalcedo.edumanager.controller.auth.AuthenticationService;
import ph.jsalcedo.edumanager.controller.auth.RegisterRequest;
import ph.jsalcedo.edumanager.entity.appuser.AppUser;
import ph.jsalcedo.edumanager.repository.AppUserRepository;
import ph.jsalcedo.edumanager.service.AppUserService;
import ph.jsalcedo.edumanager.utils.models.enums.AppUserRole;
import ph.jsalcedo.edumanager.utils.models.person.Name;

import java.util.Random;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final AppUserRepository repository;
    private final AppUserService userService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired Faker faker, @Autowired Random ran , @Autowired AuthenticationService authenticationService){
        return args->{
            for(int i = 0; i < 100; i++){
                String companyName = faker.university().name();

                AppUserRole[] appUserRoles = AppUserRole.values();
                RegisterRequest request = RegisterRequest.builder()
                        .name(Name.builder()
                                .firstName(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .build())
                        .companyName(companyName)
                        .country("Philippines")
                        .email(faker.bothify(String.format("????##@%s.com", companyName.replace(" ", "").toLowerCase())))
                        .locked(false)
                        .enabled(true)
                        .appUserRole(appUserRoles[ran.nextInt(appUserRoles.length)])
                        .password("1234")
                        .build();
                authenticationService.register(request);
            }
        };


    }

    @Bean

    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public Faker faker(){
        return new Faker();
    }
    @Bean
    public Random random(){
        return new Random();
    }
}
