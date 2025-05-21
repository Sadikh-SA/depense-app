package com.example.backend.controller;

import com.example.backend.model.Depense;
import com.example.backend.model.User;
import com.example.backend.service.DepenseService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/depenses")
public class DepenseController {

    @Autowired
    private DepenseService depenseService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Depense>> getDepenses(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(depenseService.getDepensesByUser(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}/periode")
    public ResponseEntity<List<Depense>> getDepensesByPeriode(
            @PathVariable Long userId,
            @RequestParam String start,
            @RequestParam String end) {

        return userService.getUserById(userId)
                .map(user -> {
                    LocalDate startDate = LocalDate.parse(start);
                    LocalDate endDate = LocalDate.parse(end);
                    return ResponseEntity.ok(depenseService.getDepensesByUserAndPeriode(user, startDate, endDate));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Depense> createDepense(@RequestBody Depense depense) {
        User currentUser = userService.getCurrentUser();
        depense.setUser(currentUser); // on lie automatiquement le user
        return ResponseEntity.ok(depenseService.createDepense(depense));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Depense>> searchDepenses(@RequestParam String keyword) {
        return ResponseEntity.ok(depenseService.searchDepenses(keyword));
    }

}
