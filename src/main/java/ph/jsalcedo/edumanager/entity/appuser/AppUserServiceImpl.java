package ph.jsalcedo.edumanager.entity.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.entity.appuser.AppUser;
import ph.jsalcedo.edumanager.entity.appuser.AppUserRepository;
import ph.jsalcedo.edumanager.entity.appuser.AppUserService;
import ph.jsalcedo.edumanager.exceptions.exception.CustomEntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements UserDetailsService , AppUserService {
    private final static String USER_NOT_FOUND_MESSAGE =
            "user with email %s not found";
    private final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getAppuser(String email) {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
       if(appUser.isEmpty())  throw new IllegalStateException("Email can't be found");
       return appUser.get();
    }

    @Override
    public void saveAppUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public boolean doesEmailExists(String email) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        return optionalAppUser.isPresent();
    }

    @Override
    public AppUser getAppuser(Long id) {
        return appUserRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    /**
     * <h1>findByIdAndInstitutionInstitutionName</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param id
     * @param institution_institutionName
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 5:20 am
     */
    @Override
    public AppUser findByIdAndInstitutionInstitutionName(Long id, String institution_institutionName) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByIdAndInstitution_InstitutionName(id, institution_institutionName);
        if(optionalAppUser.isEmpty())
            throw new CustomEntityNotFoundException("AppUser ID",id);

        return optionalAppUser.get();
    }
}
