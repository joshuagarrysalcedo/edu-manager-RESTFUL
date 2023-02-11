package ph.jsalcedo.edumanager.data.models.entity.school.schoolDetails;


import org.springframework.transaction.annotation.Transactional;
import ph.jsalcedo.edumanager.data.models.person.Address;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SchoolDetailsDao {
    void addSchoolDetails(SchoolDetails schoolDetails);
    void deleteSchoolDetails(SchoolDetails schoolDetails);
    void updateSchoolName(SchoolDetails schoolDetails, String newName);

    Optional<SchoolDetails> findSchoolDetails(String name);
    Optional<SchoolDetails> findSchoolDetails(Long id);
    List<SchoolDetails> getAllSchoolDetails();

    @Transactional
    void updateSchoolDomain(SchoolDetails schoolDetails, String newDomain);
    @Transactional
    void updateFounder(SchoolDetails schoolDetails, String newFounder);
    @Transactional
    void updateFounderOn(SchoolDetails schoolDetails, Date newFoundedOn);
    @Transactional
    void updateAddress(SchoolDetails schoolDetails, Address address);
    @Transactional
    void updateStudentIDPattern(SchoolDetails schoolDetails, String newSchoolIDPattern);
    @Transactional
    void updateEmployeeIDPattern(SchoolDetails schoolDetails, String newPattern);
}
