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
public class UtilisateurServiceImp extends AbstractShoppingServiceImp<Utilisateur, Long> implements UtilisateurService {
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
}
