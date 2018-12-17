package com.shopping.shopping.dao;

import com.shopping.shopping.model.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueDao extends JpaRepository<Historique, Long> {
}
