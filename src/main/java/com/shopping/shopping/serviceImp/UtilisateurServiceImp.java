package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.UtilisateurDao;
import com.shopping.shopping.model.Utilisateur;
import com.shopping.shopping.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImp implements UtilisateurService {
    @Autowired
    private UtilisateurDao utilisateurDao;
    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        return utilisateurDao.save(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return utilisateurDao.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> getUtilisateur(Long idUtilisateur) {
        return utilisateurDao.findById(idUtilisateur);
    }

    @Override
    public void deleteUtilisateur(Long idUser) {
        utilisateurDao.deleteById(idUser);
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurDao.findAll();
    }
}
