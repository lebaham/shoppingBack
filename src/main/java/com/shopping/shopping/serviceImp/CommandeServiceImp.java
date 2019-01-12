package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.CommandeDao;
import com.shopping.shopping.exception.CommandeException;
import com.shopping.shopping.model.Commande;
import com.shopping.shopping.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Service
public class CommandeServiceImp implements CommandeService {
    @Autowired
    private CommandeDao commandeDao;

    @Override
    public  Commande addCommmande(Commande com) {
        if(commandeDao.existsByNumeroCommande(com.getNumeroCommande())){
            new Exception("la commande existe deja!");
        }
        com.setDatecreation(LocalDateTime.now());
        return commandeDao.save(com);
    }

    @Override
    public void deleteCommande(Long comId) {
        if(!commandeDao.existsById(comId)){
            throw new CommandeException("la commande n'existe pas!!");
        }
        commandeDao.deleteById(comId);
    }


    @Override
    public Optional<Commande> getCommande(Long idCommande) {
        Commande  commande = commandeDao.findById(idCommande).orElseThrow(() -> new CommandeException("la commande recherchée n'existe pas!!"));
        return Optional.ofNullable(commande);
    }

    @Override
    public List<Commande> getCommandes() {
        Stream<Commande> cs = commandeDao.findAll().stream();
        return cs.collect(Collectors.toList());
    }

    @Override
    public Commande updateCommande(Commande commande) {
     return commandeDao.save(commande);
    }

    @Override
    public Boolean existsByNumeroCommande(int numeroCommande) {
      Boolean exist = commandeDao.existsByNumeroCommande(numeroCommande);
        return exist;
    }

    @Override
    public List<Commande> findCommandeByUtilisateur(Long idUtilisateur) {
        return commandeDao.findCommandeByUtilisateur(idUtilisateur);
    }

}
