package ph.jsalcedo.edumanager.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.repository.SchoolDetailsRepository;
import ph.jsalcedo.edumanager.service.SchoolDetailsService;
import ph.jsalcedo.edumanager.utils.models.enums.ErrorMessage;
import ph.jsalcedo.edumanager.utils.models.person.Address;
import ph.jsalcedo.edumanager.entity.school.SchoolDetails;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SchoolDetailsServiceImpl implements SchoolDetailsService {
    private final SchoolDetailsRepository schoolDetailsRepository;
    @Override
    public void addSchoolDetails(SchoolDetails schoolDetails) {
        if(doesItExists(schoolDetails)){
            throw new IllegalStateException(ErrorMessage.Constants.ADDING_SCHOOL_DETAILS_MESSAGE);
        }
        schoolDetailsRepository.save(schoolDetails);
    }

    private boolean doesItExists(SchoolDetails schoolDetails) {
        List<SchoolDetails> list = schoolDetailsRepository.findAll();
        for(SchoolDetails s : list){
            if(s.getSchoolName().equalsIgnoreCase(schoolDetails.getSchoolName())
            || s.getSchoolDomain().equals(schoolDetails.getSchoolDomain())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteSchoolDetails(SchoolDetails schoolDetails) {
        schoolDetailsRepository.deleteById(schoolDetails.getId());
    }

    @Override
    public void updateSchoolName(SchoolDetails schoolDetails, String newName) {
        schoolDetails.setSchoolName(newName);
        schoolDetailsRepository.save(schoolDetails);
    }

    @Override
    public Optional<SchoolDetails> findSchoolDetails(String name) {
        return  schoolDetailsRepository.findBySchoolName(name);

    }

    @Override
    public Optional<SchoolDetails> findSchoolDetails(Long id) {
        return schoolDetailsRepository.findById(id);
    }


    @Override
    public List<SchoolDetails> getAllSchoolDetails() {
        return  schoolDetailsRepository.findAll();

    }

    @Override
    public void updateSchoolDomain(SchoolDetails schoolDetails, String newDomain) {
        schoolDetails.setSchoolDomain(newDomain);
        schoolDetailsRepository.save(schoolDetails);
    }

    @Override
    public void updateFounder(SchoolDetails schoolDetails, String newFounder) {
        schoolDetails.setFounder(newFounder);
        schoolDetailsRepository.save(schoolDetails);
    }

    @Override
    public void updateFounderOn(SchoolDetails schoolDetails, Date newFoundedOn) {
        schoolDetails.setFoundedOn(newFoundedOn);
        schoolDetailsRepository.save(schoolDetails);
    }

    @Override
    public void updateAddress(SchoolDetails schoolDetails, Address address) {
        schoolDetails.setSchoolAddress(address);
        schoolDetailsRepository.save(schoolDetails);
    }

    @Override
    public void updateStudentIDPattern(SchoolDetails schoolDetails, String newSchoolIDPattern) {
        schoolDetails.setStudentIDPattern(newSchoolIDPattern);
        schoolDetailsRepository.save(schoolDetails);
    }

    @Override
    public void updateEmployeeIDPattern(SchoolDetails schoolDetails, String newPattern) {
        schoolDetails.setEmployeeIDPattern(newPattern);
        schoolDetailsRepository.save(schoolDetails);
    }

    @Override
    public void deleteAll() {
        schoolDetailsRepository.deleteAll();
    }


}
