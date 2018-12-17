package com.shopping.shopping.service;

import com.shopping.shopping.model.Commande;

import java.util.List;
import java.util.Optional;

public interface CommandeService {
    Commande addCommmande(Commande com);
    void deleteCommande(Long comId);
    Commande updateCommande(Commande com);
    Optional<Commande> getCommande(Long idCommande);
    List<Commande> getCommandes();
}
