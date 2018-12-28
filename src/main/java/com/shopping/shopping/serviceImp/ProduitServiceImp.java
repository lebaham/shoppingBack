package com.shopping.shopping.serviceImp;

import com.shopping.shopping.exception.ProduitException;
import com.shopping.shopping.model.Produit;
import com.shopping.shopping.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProduitServiceImp extends AbstractShoppingServiceImp<Produit,Long> implements ProduitService {
    @Autowired
    private JpaRepository<Produit,Long> produitDao;

    @Override
    public Produit addProduit(Produit produit) {
        if(produit.getNomProduit() == null){
           throw new ProduitException("le nom du produit doit etre renseigné!!");
        }
        return produitDao.save(produit);
    }

}
