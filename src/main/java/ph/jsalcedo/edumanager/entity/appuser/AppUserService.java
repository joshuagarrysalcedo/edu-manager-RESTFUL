package ph.jsalcedo.edumanager.entity.appuser;

import ph.jsalcedo.edumanager.entity.appuser.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    List<AppUser> getAllUsers();
    AppUser getAppuser(String email);
    void saveAppUser(AppUser appUser);

    boolean doesEmailExists(String email);

    AppUser getAppuser(Long id);
}
