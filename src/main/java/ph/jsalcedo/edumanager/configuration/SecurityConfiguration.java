package ph.jsalcedo.edumanager.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ph.jsalcedo.edumanager.entity.auth.CustomAuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/index", "/index**", "", "/", "/api/v1/auth/register",
                        "/api/v1/auth/authenticate", "/api/v1/auth/users", "/login", "/api/v1/auth/**", "/api/v1/auth/user/**", "/api/v1/auth/user/",
                        "/api/v1/school-admin", "/api/v1/school-admin/*", "/api/v1/school-admin/*/schools", "/api/v1/school-admin/*/*/*/curriculums",
                        "/api/v1/school-admin/*/*/*/curriculum",
                        "/api/v1/school-admin/*/*/*/students",
                        "/api/v1/school-admin/*/*/*/student",
                        "/api/v1/school-admin/*/*/*/*/enroll",
                        "/api/v1/school-admin/*/*/*/curriculums/delete")
//                .requestMatchers("/", "/index")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin
                        (form->
                        {
                            try {
                                form
                                        .permitAll();
//                                        .loginPage("http://localhost:3000/")
//                                        .loginProcessingUrl("/perform_login")
//                                        .defaultSuccessUrl("/index", true)
//                                        .failureHandler(authenticationFailureHandler())
//                                        .and()
//                                        .logout();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });


        return http.build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }
}
