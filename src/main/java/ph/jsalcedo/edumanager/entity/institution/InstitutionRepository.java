package ph.jsalcedo.edumanager.entity.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    boolean existsByInstitutionNameAndId(String name, Long id);

//    @Query("select t from Institution t order by t.id desc")
    Optional<Institution> findFirstByOrderByIdDesc();

    Optional<Institution> findByInstitutionNameAndId(String name, Long id);

}
