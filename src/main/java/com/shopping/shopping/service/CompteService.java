package com.shopping.shopping.service;

import com.shopping.shopping.model.Compte;

import java.util.List;
import java.util.Optional;

public interface CompteService {
    Compte addCompte(Compte compte);
    void   deleteCompte(Long idCompte);
    Compte updateCompte(Compte compte);
    Optional<Compte> getComptes(Long idCompte);
    List<Compte> getComptes();
}
