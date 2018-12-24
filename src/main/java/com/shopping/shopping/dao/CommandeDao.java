package com.shopping.shopping.dao;

import com.shopping.shopping.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeDao extends JpaRepository<Commande,Long> {
    Boolean existsByNumeroCommande(int  numeroCommande);
}
