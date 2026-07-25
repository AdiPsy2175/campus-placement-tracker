package com.aditya.campusplacementtracker.service;

import com.aditya.campusplacementtracker.entity.Student;
import com.aditya.campusplacementtracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.aditya.campusplacementtracker.entity.User;
import com.aditya.campusplacementtracker.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {

        User user = new User();

        String username = student.getEmail().split("@")[0];

        user.setUsername(username);

        user.setPassword(passwordEncoder.encode("Temp@123"));

        user.setRole(Role.STUDENT);

        student.setUser(user);

        return studentRepository.save(student);

    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);

    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);

    }

    public long getStudentCount() {
        return studentRepository.count();
    }

    public long getPlacedStudentCount() {
        return studentRepository.countByPlacedTrue();
    }

    public List<Student> searchStudents(String keyword) {
        return studentRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Page<Student> getStudentsPage(int page, String sortBy) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        return studentRepository.findAll(pageable);
    }

    public List<Student> getPlacedStudents() {
        return studentRepository.findByPlacedTrue();
    }

    public List<Student> getUnplacedStudents() {
        return studentRepository.findByPlacedFalse();
    }
}