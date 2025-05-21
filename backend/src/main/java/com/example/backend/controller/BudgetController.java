package com.example.backend.controller;

import com.example.backend.model.Budget;
import com.example.backend.service.BudgetService;
import com.example.backend.service.UserService;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        Budget budget = budgetService.getBudgetById(id);
        return ResponseEntity.ok(budget);
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget saved = budgetService.createBudget(budget);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        Budget updated = budgetService.updateBudget(id, budget);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Budget>> searchBudgets(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String month) {

        if (year != null && month != null) {
            return ResponseEntity.ok(budgetService.findByYearAndMonth(year, month));
        } else if (year != null) {
            return ResponseEntity.ok(budgetService.findByYear(year));
        } else if (month != null) {
            return ResponseEntity.ok(budgetService.findByMonth(month));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}

