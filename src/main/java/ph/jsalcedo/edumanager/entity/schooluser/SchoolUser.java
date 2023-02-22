package ph.jsalcedo.edumanager.entity.schooluser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * <h1> SchoolUser</h1>
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
public class SchoolUser implements UserDetails {
    /**
     * <h1>getAuthorities</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 6:14 am
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * <h1>getPassword</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 6:14 am
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * <h1>getUsername</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 6:14 am
     */
    @Override
    public String getUsername() {
        return null;
    }

    /**
     * <h1>isAccountNonExpired</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 6:14 am
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * <h1>isAccountNonLocked</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 6:14 am
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * <h1>isCredentialsNonExpired</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 6:14 am
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * <h1>isEnabled</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 6:14 am
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
