package org.example.service;

import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendanceService {

    private final StudentRepository studentRepository;

    public AttendanceService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student addStudent(String name, String groupName, boolean isAttended) {
        Student student = new Student();
        student.setName(name);
        student.setGroupName(groupName);
        student.setIsAttended(isAttended);
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAllByOrderByName();
    }
}

