package server.controller;

import server.model.Student;
import server.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/student")
    public List<Student> index() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/{name}")
    public Student getStudentInfo(@PathVariable String name) {
        return studentRepository.findByName(name);
    }

    @PostMapping("/student")
    public Student create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String major = body.get("major");
        return studentRepository.save(new Student(name, major));
    }

    @PutMapping("/student/{id}")
    public Student update(@PathVariable String id, @RequestBody Map<String, String> body) {
        int studentId = Integer.parseInt(id);

        Student student = studentRepository.findById(studentId);
        student.setName(body.get("name"));
        student.setName(body.get("major"));
        return studentRepository.save(student);
    }

//    @DeleteMapping("student/{id}")
//    public boolean delete(@PathVariable String id) {
//        int studentId = Integer.parseInt(id);
//
//        studentRepository.delete(studentId);
//        return true;
//    }



}
