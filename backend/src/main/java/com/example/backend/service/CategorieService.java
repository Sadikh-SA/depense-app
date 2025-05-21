package com.example.backend.service;

import com.example.backend.model.Categorie;
import com.example.backend.model.User;
import com.example.backend.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée avec l'ID : " + id));
    }

    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(Long id, Categorie categorie) {
        Categorie existing = getCategorieById(id);
        existing.setName(categorie.getName());
        return categorieRepository.save(existing);
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    public List<Categorie> getCategoriesByUser(User user) {
        return categorieRepository.findByUser(user);
    }

    public List<Categorie> searchCategories(String keyword) {
        return categorieRepository.findByNameContainingIgnoreCase(keyword);
    }

}
