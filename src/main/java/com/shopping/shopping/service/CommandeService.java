package com.shopping.shopping.service;
import com.shopping.shopping.model.Commande;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommandeService {
    Commande addCommmande(Commande com);

    void deleteCommande(Long comId);

    Optional<Commande> getCommande(Long idCommande);

    List<Commande> getCommandes();

    Commande updateCommande(Commande commande);

    Boolean existsByNumeroCommande(int  numeroCommande);

    List<Commande> findCommandeByUtilisateur(Long idUtilisateur);

    List<Commande> findCommandeByparameters(Long idUtilisateur, Optional<LocalDateTime> dateMin,  Optional<LocalDateTime> dateMax,
                                           Optional<String> status);


    List<Commande> findCommandeByUsersAndDatecreation(Long idUtilisateur, LocalDateTime date);
}
