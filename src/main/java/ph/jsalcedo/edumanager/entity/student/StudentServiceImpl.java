package ph.jsalcedo.edumanager.entity.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ph.jsalcedo.edumanager.configuration.JwtService;
import ph.jsalcedo.edumanager.entity.auth.AuthenticationResponse;
import ph.jsalcedo.edumanager.entity.registerrequest.RegisterStudentRequest;
import ph.jsalcedo.edumanager.entity.school.School;
import ph.jsalcedo.edumanager.entity.school.SchoolService;
import ph.jsalcedo.edumanager.entity.school.curriculum.Curriculum;
import ph.jsalcedo.edumanager.exceptions.exception.CustomEntityNotFoundException;
import ph.jsalcedo.edumanager.utils.models.enums.Student_Status;
import ph.jsalcedo.edumanager.utils.models.person.Address;
import ph.jsalcedo.edumanager.utils.models.person.BirthDetails;
import ph.jsalcedo.edumanager.utils.models.person.ContactDetails;
import ph.jsalcedo.edumanager.utils.models.person.Name;

import java.util.List;
import java.util.Optional;

/**
 * <h1> StudentServiceImpl</h1>
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
 * @created 21/02/2023
 */
@Service
@AllArgsConstructor

public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    /**
     * <h1>findAllStudentsBySchool</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param school
     * @return
     * @author Joshua Salcedo
     * @created 21/02/2023 - 11:29 pm
     */
    @Override
    public List<Student> findAllStudentsBySchool(School school) {

        return studentRepository.findAllBySchool(school);
    }

    /**
     * <h1>findBySchoolAndStudentName</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @return
     * @author Joshua Salcedo
     * @created 21/02/2023 - 11:29 pm
     */
    @Override
    public Student findBySchoolAndStudentName(School school, Name name) {
        Optional<Student> var = studentRepository.findStudentBySchoolAndName(school, name);
        if(var.isEmpty())
            throw new CustomEntityNotFoundException("Student Name", name);

        return var.get();
    }

    /**
     * <h1>findByStudentID</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param ID
     * @return
     * @author Joshua Salcedo
     * @created 21/02/2023 - 11:29 pm
     */
    @Override
    public Student findByStudentID(Long ID) {
        Optional<Student> var = studentRepository.findStudentById(ID);

        if(var.isEmpty())
            throw new CustomEntityNotFoundException("Student ID: " , ID);

        return var.get();
    }

    /**
     * <h1>findAllStudentsByCurriculum</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param curriculum
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 12:15 am
     */
    @Override
    public List<Student> findAllStudentsByCurriculum(Curriculum curriculum) {
        return studentRepository.findAllByCurriculum(curriculum);
    }

    /**
     * <h1>registerStudent</h1>
     * <p>Explain here!</p>
     * <b>Note:</b>
     *
     * @param request
     * @return
     * @author Joshua Salcedo
     * @created 22/02/2023 - 9:28 am
     */
    @Override
    public Student registerStudent(RegisterStudentRequest request) {
        return Student.builder()
                .name(Name.builder()
                        .firstName(request.getFirstName())
                        .middleName(request.getMiddleName())
                        .lastName(request.getLastName())
                        .build())
                .contactDetails(ContactDetails.builder()
                        .email(request.getEmail())
                        .phoneNumber(request.getPhoneNUmber())
                        .build())
                .address(Address.builder(request.getCity(), request.getCountry())
                        .stateOrProvince(request.getStateOrProvince())
                        .zipOrPostalCode(request.getZipOrPostalCode())
                        .streetAddress(request.getStreetAddress())
                        .build())
                .birthDetails(BirthDetails.builder()
                        .birthDate(request.getBirthDate())
                        .build())
                .studentStatus(request.getStudentStatus())
                .gender(request.getGender())
                .nationality(request.getNationality())
                .maritalStatus(request.getMaritalStatus())
                .religion(request.getReligion())
                .build();
    }


}
