package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.ProduitDao;
import com.shopping.shopping.model.Produit;
import com.shopping.shopping.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImp implements ProduitService {
    @Autowired
    private ProduitDao produitDao;
    @Override
    public Produit addProduit(Produit produit) {
        return produitDao.save(produit);
    }

    @Override
    public Produit updateProduit(Produit produit) {
        return produitDao.save(produit);
    }

    @Override
    public Optional<Produit> getProduit(Long idProduit) {
        return produitDao.findById(idProduit);
    }

    @Override
    public void deleteProduit(Long idProduit) {
        produitDao.deleteById(idProduit);
    }

    @Override
    public List<Produit> getProduits() {
        return produitDao.findAll();
    }
}
