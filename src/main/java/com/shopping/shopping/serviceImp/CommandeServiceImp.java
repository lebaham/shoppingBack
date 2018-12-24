package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.CommandeDao;
import com.shopping.shopping.model.Commande;
import com.shopping.shopping.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImp implements CommandeService {
    @Autowired
    private CommandeDao commandeDao;
    @Override
    public  Commande addCommmande(Commande com) {
        if(commandeDao.existsByNumeroCommande(com.getNumeroCommande())){
            new Exception("la commande existe deja!");
        }
        return commandeDao.save(com);
    }

    @Override
    public void deleteCommande(Long comId) {
        commandeDao.deleteById(comId);
    }

    @Override
    public Commande updateCommande(Commande com) {
        return commandeDao.save(com);
    }

    @Override
    public Optional<Commande> getCommande(Long idCommande) {

        return commandeDao.findById(idCommande);
    }

    @Override
    public List<Commande> getCommandes() {
        return commandeDao.findAll();
    }
}
