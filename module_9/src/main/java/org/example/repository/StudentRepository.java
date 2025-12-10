package org.example.repository;

import org.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGroupIdOrderByName(Long groupId);
    List<Student> findAllByOrderByName();
}

