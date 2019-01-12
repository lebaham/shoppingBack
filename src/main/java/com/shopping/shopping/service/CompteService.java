package com.shopping.shopping.service;

import com.shopping.shopping.model.Compte;

import java.util.List;
import java.util.Optional;

public interface CompteService {

    Compte addCompte(Compte compte);

    void deleteCompte(Long idCompte);

    Optional<Compte> getCompte(Long idCompte);

    List<Compte> getComptes();

}
