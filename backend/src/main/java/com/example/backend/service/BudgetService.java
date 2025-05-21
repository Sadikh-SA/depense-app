package com.example.backend.service;

import com.example.backend.model.Budget;
import com.example.backend.model.User;
import com.example.backend.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget non trouvé avec ID : " + id));
    }

    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Budget updateBudget(Long id, Budget budget) {
        Budget existing = getBudgetById(id);
        // existing.setNom(budget.getNom());
        existing.setAmount(budget.getAmount());
        existing.setMonth(budget.getMonth());
        return budgetRepository.save(existing);
    }


    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }

    public List<Budget> findByYear(String year) {
        return budgetRepository.findByYear(year);
    }

    public List<Budget> findByMonth(String month) {
        return budgetRepository.findByMonth(month);
    }

    public List<Budget> findByYearAndMonth(String year, String month) {
        return budgetRepository.findByYearAndMonth(year, month);
    }


}

