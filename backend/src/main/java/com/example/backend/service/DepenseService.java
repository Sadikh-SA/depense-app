package com.example.backend.service;

import com.example.backend.model.Depense;
import com.example.backend.model.User;
import com.example.backend.repository.DepenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class DepenseService {

    @Autowired
    private DepenseRepository depenseRepository;

    public List<Depense> getAllDepenses() {
        return depenseRepository.findAll();
    }

    public Depense getDepenseById(Long id) {
        return depenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense non trouvée avec l'ID : " + id));
    }

    public Depense createDepense(Depense depense) {
        return depenseRepository.save(depense);
    }

    public Depense updateDepense(Long id, Depense depense) {
        Depense existing = getDepenseById(id);
        existing.setAmount(depense.getAmount());
        existing.setDescription(depense.getDescription());
        existing.setDate(depense.getDate());
        existing.setCategorie(depense.getCategorie());
        //existing.setBudget(depense.getBudget());
        return depenseRepository.save(existing);
    }

    public void deleteDepense(Long id) {
        depenseRepository.deleteById(id);
    }
    public List<Depense> getDepensesByUser(User user) {
        return depenseRepository.findByUser(user);
    }

    public List<Depense> getDepensesByUserAndPeriode(User user, LocalDate start, LocalDate end) {
        return depenseRepository.findByUserAndDateBetween(user, start, end);
    }

    public List<Depense> searchDepenses(String keyword) {
        return depenseRepository.findByDescriptionContainingIgnoreCase(keyword);
    }

}

