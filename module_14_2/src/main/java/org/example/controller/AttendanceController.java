package org.example.controller;

import org.example.model.Student;
import org.example.service.AttendanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public String showAttendance(Model model, Authentication authentication) {
        List<Student> students = attendanceService.getAllStudents();

        model.addAttribute("students", students);
        model.addAttribute("username", authentication != null ? authentication.getName() : "");
        model.addAttribute("isAdmin", authentication != null && 
                authentication.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || 
                                     a.getAuthority().equals("ROLE_TEACHER")));
        model.addAttribute("isStudent", authentication != null && 
                authentication.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT")));

        return "attendance";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public String addStudent(
            @RequestParam String name,
            @RequestParam String groupName,
            @RequestParam(required = false) Boolean isAttended) {
        attendanceService.addStudent(name, groupName, isAttended != null && isAttended);
        return "redirect:/attendance";
    }
}

