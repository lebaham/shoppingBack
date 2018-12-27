package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.CompteDao;
import com.shopping.shopping.exception.CompteException;
import com.shopping.shopping.model.Compte;
import com.shopping.shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class CompteServiceImp extends AbstractShoppingService<Compte,Long> implements ShoppingService<Compte,Long>{


    @Autowired
    CompteDao compteDao;

    public Compte addCompte(Compte compte){
        if(compteDao.existsByUsername(compte.getUsername())){
            throw new CompteException("le compte avec le username "+compte.getUsername()+" existe deja");
        }else if(compteDao.existsByEmail(compte.getEmail())){
            throw new CompteException("le compte avec l'email "+compte.getEmail()+" existe deja");
        }
        return super.add(compte);
    }
}
