package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.CommandeDao;
import com.shopping.shopping.exception.CommandeException;
import com.shopping.shopping.model.Commande;
import com.shopping.shopping.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class CommandeServiceImp implements CommandeService {
    @Autowired
    private CommandeDao commandeDao;
    @Override
    public  Commande addCommmande(Commande com) {

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
    public Commande updateCommande(Commande com) {
        return commandeDao.save(com);
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


}
