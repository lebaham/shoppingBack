package com.shopping.shopping.dao;

import com.shopping.shopping.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface CommandeDao extends JpaRepository<Commande,Long> {
    Boolean existsByNumeroCommande(int  numeroCommande);

    @Query(value = "select c from Commande c inner join c.utilisateur u where u.idUtilisateur = :id order by c.datecreation desc")
    List<Commande> findCommandeByUtilisateur(@Param("id") Long idUtilisateur);

    @Query(value = "select c from Commande c inner join c.utilisateur u" +
            "where u.idUtilisateur = :id and (c.dateDeCreation between :dateMin and :dateMax) and c.statusCommande like :status order by c.datecreation desc")
    List<Commande> findCommandeByparameters(@Param("id") Long idUtilisateur,
                                                @Param("dateMin") Optional<LocalDateTime> dateMin, @Param("dateMax") Optional<LocalDateTime> dateMax,
                                                @Param("status") Optional<String> status);

    @Query(value = "select c from Commande c inner join c.utilisateur u where u.idUtilisateur = :id where c.dateDeCreation = :date order by c.datecreation desc")
    List<Commande> findCommandeByUsersAndDatecreation(@Param("id") Long idUtilisateur, @Param("date") LocalDateTime date);
}
