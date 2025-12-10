package org.example.service;

import org.example.model.Group;
import org.example.model.Student;
import org.example.repository.GroupRepository;
import org.example.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendanceService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public AttendanceService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional
    public Student addStudent(String name, String groupName, boolean isAttended) {
        Group group = groupRepository.findByGroupName(groupName)
                .orElseGet(() -> {
                    Group newGroup = new Group();
                    newGroup.setGroupName(groupName);
                    return groupRepository.save(newGroup);
                });

        Student student = new Student();
        student.setName(name);
        student.setGroup(group);
        student.setIsAttended(isAttended);

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public List<Student> getStudents(Long groupId) {
        if (groupId != null) {
            return studentRepository.findByGroupIdOrderByName(groupId);
        }
        return studentRepository.findAllByOrderByName();
    }
}

