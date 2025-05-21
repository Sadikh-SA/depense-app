package com.example.backend.repository;

import com.example.backend.model.Budget;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUserAndMonth(User user, String month);
    // Recherche par année
    @Query("SELECT b FROM Budget b WHERE SUBSTRING(b.month, 1, 4) = :year")
    List<Budget> findByYear(@Param("year") String year);

    // Recherche par mois uniquement (ex: "05")
    @Query("SELECT b FROM Budget b WHERE SUBSTRING(b.month, 6, 2) = :month")
    List<Budget> findByMonth(@Param("month") String month);

    // Recherche par année et mois (ex: "2024" + "05")
    @Query("SELECT b FROM Budget b WHERE b.month = CONCAT(:year, '-', :month)")
    List<Budget> findByYearAndMonth(@Param("year") String year, @Param("month") String month);

}
