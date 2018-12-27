package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.UtilisateurDao;
import com.shopping.shopping.exception.UtilisateurException;
import com.shopping.shopping.model.Utilisateur;
import com.shopping.shopping.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UtilisateurServiceImp implements UtilisateurService {
    @Autowired
    private UtilisateurDao utilisateurDao;
    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        if (utilisateur.getNom() == null){
            throw new UtilisateurException("le nom de l'utilisateur doit etre renseigné");
        }
        if(utilisateur.getPrenom() == null){
            throw new UtilisateurException("le prenom de l'utilisateur doit etre renseigné");
        }
        return utilisateurDao.save(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {

        return utilisateurDao.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> getUtilisateur(Long idUtilisateur) {
        if(utilisateurDao.findById(idUtilisateur) == null){
            throw new UtilisateurException("l'utilisateur  recherché n'existe pas");
        }
        return utilisateurDao.findById(idUtilisateur);
    }

    @Override
    public void deleteUtilisateur(Long idUser) {
        if(!utilisateurDao.existsById(idUser)){
            throw new UtilisateurException("impossible de supprimer l'utilisateur car l'utilisateur n'existe pas!!");
        }
        utilisateurDao.deleteById(idUser);
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        Stream<Utilisateur> su = utilisateurDao.findAll().stream();
        return su.collect(Collectors.toList());
    }
}
