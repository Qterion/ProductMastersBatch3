package org.example.controller;

import org.example.model.Group;
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
    public String showAttendance(
            @RequestParam(required = false) Long groupId,
            Model model,
            Authentication authentication) {
        List<Group> groups = attendanceService.getAllGroups();
        List<Student> students = attendanceService.getStudents(groupId);

        model.addAttribute("groups", groups);
        model.addAttribute("students", students);
        model.addAttribute("selectedGroupId", groupId);
        model.addAttribute("username", authentication != null ? authentication.getName() : "");
        model.addAttribute("isAdmin", authentication != null && 
                authentication.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));

        return "attendance";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addStudent(
            @RequestParam String name,
            @RequestParam String groupName,
            @RequestParam(required = false) Boolean isAttended) {
        attendanceService.addStudent(name, groupName, isAttended != null && isAttended);
        return "redirect:/attendance";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteStudent(@RequestParam Long studentId) {
        attendanceService.deleteStudent(studentId);
        return "redirect:/attendance";
    }
}

