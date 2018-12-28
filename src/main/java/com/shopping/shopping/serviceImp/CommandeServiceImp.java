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
public class CommandeServiceImp extends AbstractShoppingServiceImp<Commande,Long> implements CommandeService {
    @Autowired
    private CommandeDao commandeDao;

    @Override
    public  Commande addCommmande(Commande com) {
        if(commandeDao.existsByNumeroCommande(com.getNumeroCommande())){
            new Exception("la commande existe deja!");
        }
        return commandeDao.save(com);
    }
}
