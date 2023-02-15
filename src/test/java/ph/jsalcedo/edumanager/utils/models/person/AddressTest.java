package ph.jsalcedo.edumanager.utils.models.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ph.jsalcedo.edumanager.entity.student.Student;
import ph.jsalcedo.edumanager.entity.student.StudentRepository;
import ph.jsalcedo.edumanager.utils.models.enums.ErrorMessage;

@SpringBootTest

class AddressTest {
    private final Address testAddress;
    private final Student student;
    private final StudentRepository studentRepository;
    @Autowired
    AddressTest(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.testAddress = Address
                .builder("CDO", "Philippines")
                .build();
        this.student = Student.builder().address(testAddress).build();
    }



    @Test
    void setStreetAddress() {
        Exception minLengthException = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            student.getAddress().setStreetAddress("2");
            studentRepository.save(student);
        });
        String messageResult = minLengthException.getMessage();
        String expected = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE;
        Assertions.assertEquals(expected, messageResult);
    }

   @Test

    void setCity() {
        Exception minLengthException = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            testAddress.setCity("a");
        });
        String messageResult = minLengthException.getMessage();
        String expected = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE;
        Assertions.assertEquals(expected, messageResult);
    }

    @Test
    void setStateOrProvince() {
        Exception minLengthException = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            testAddress.setStateOrProvince("a");
        });
        String messageResult = minLengthException.getMessage();
        String expected = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE;
        Assertions.assertEquals(expected, messageResult);
    }

    @Test
    void setZipOrPostalCode() {
        Exception minLengthException = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            testAddress.setZipOrPostalCode("1");
        });
        String messageResult = minLengthException.getMessage();
        String expected = ErrorMessage.Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE;
        Assertions.assertEquals(expected, messageResult);
    }

    @Test
    void setCountry() {
        Exception minLengthException = Assertions.assertThrows(NullPointerException.class, ()->{
            testAddress.setCountry(" ");
        });
        String messageResult = minLengthException.getMessage();
        String expected = ErrorMessage.Constants.EMPTY_FIELD_REQUIRED_MESSAGE;
        Assertions.assertEquals(expected, messageResult);
    }
}