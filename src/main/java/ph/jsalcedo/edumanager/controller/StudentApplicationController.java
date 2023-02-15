package ph.jsalcedo.edumanager.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ph.jsalcedo.edumanager.entity.student.Student;
import ph.jsalcedo.edumanager.utils.models.entity.student.StudentService;

import java.util.List;

@Slf4j
@Controller
@RestController
@RequestMapping("api/v1/application/student")
@AllArgsConstructor
@CrossOrigin
public class StudentApplicationController {
    private final StudentService studentService;
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("Hello %s!", name);
    }

    @PostMapping(value = "/add")
    public String addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return String.format("Student has been added : %s", student);
    }

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudents(){
        return studentService.getAllStudents();
    }
}
