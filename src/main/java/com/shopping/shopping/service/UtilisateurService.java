package com.shopping.shopping.service;

import com.shopping.shopping.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    Utilisateur addUtilisateur(Utilisateur utilisateur);

 Utilisateur updateUtilisateur(Utilisateur utilisateur);

 Optional<Utilisateur> getUtilisateur(Long idUtilisateur);

   void deleteUtilisateur(Long idUser);

   List<Utilisateur> getUtilisateurs();
}
