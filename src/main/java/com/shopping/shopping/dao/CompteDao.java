package com.shopping.shopping.dao;

import com.shopping.shopping.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteDao extends JpaRepository<Compte, Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
