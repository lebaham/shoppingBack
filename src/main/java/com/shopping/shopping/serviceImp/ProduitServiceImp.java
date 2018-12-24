package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.ProduitDao;
import com.shopping.shopping.exception.ProduitException;
import com.shopping.shopping.model.Produit;
import com.shopping.shopping.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ProduitServiceImp implements ProduitService {
    @Autowired
    private ProduitDao produitDao;
    @Override
    public Produit addProduit(Produit produit) {
        if(produit.getNomProduit().isEmpty()){
           throw new ProduitException("le nom du produit doit etre renseigné!!");
        }
        return produitDao.save(produit);
    }

    @Override
    public Produit updateProduit(Produit produit) {
        return produitDao.save(produit);
    }

    @Override
    public Optional<Produit> getProduit(Long idProduit) {
        if(produitDao.findById(idProduit) == null){
            throw new ProduitException("le produit  recherché n'existe pas");
        }
        return produitDao.findById(idProduit);
    }

    @Override
    public void deleteProduit(Long idProduit) {
        if(produitDao.findById(idProduit) == null){
            throw new ProduitException("le produit  recherché n'existe pas");
        }
        produitDao.deleteById(idProduit);
    }

    @Override
    public List<Produit> getProduits() {
        Stream<Produit> ps = produitDao.findAll().stream();
        return ps.collect(Collectors.toList());
    }
}
