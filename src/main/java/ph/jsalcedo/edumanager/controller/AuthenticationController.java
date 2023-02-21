package ph.jsalcedo.edumanager.controller;

import com.github.javafaker.App;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.jsalcedo.edumanager.configuration.JwtService;
import ph.jsalcedo.edumanager.entity.appuser.AppUser;
import ph.jsalcedo.edumanager.entity.appuser.AppUserService;
import ph.jsalcedo.edumanager.entity.auth.AuthenticationRequest;
import ph.jsalcedo.edumanager.entity.auth.AuthenticationResponse;
import ph.jsalcedo.edumanager.entity.auth.AuthenticationService;
import ph.jsalcedo.edumanager.entity.auth.RegisterRequest;


import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AppUserService appUserService;
    private final AuthenticationService service;
    private final JwtService jwtService;


    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){
//        userService.saveUser(user);
//        return "User has been saved. Check your email to verify";
       return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(

            @RequestBody AuthenticationRequest request) {

        try {
            return  ResponseEntity.ok(service.authenticate(request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppUser> getUsers(){
        System.out.println("All users!");
        return appUserService.getAllUsers();
    }


//    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public AppUser getAppUser(@PathVariable ("id") Long id){
//        return appUserService.getAppuser(id);
//    }


    @GetMapping(value = "/user/token/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AppUser getUserByToken(@PathVariable("token") String token){
        String email = jwtService.extractUserName(token);
         return appUserService.getAppuser(email);

    }
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AppUser getUser(@PathVariable("id") Long id){
        return appUserService.getAppuser(id);
    }

}
