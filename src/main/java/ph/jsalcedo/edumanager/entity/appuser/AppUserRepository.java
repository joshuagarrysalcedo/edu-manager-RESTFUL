package ph.jsalcedo.edumanager.entity.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ph.jsalcedo.edumanager.entity.appuser.AppUser;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByIdAndInstitution_InstitutionName(Long id, String institution_institutionName);
}
