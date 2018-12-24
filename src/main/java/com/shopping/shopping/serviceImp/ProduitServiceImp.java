package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.ProduitDao;
import com.shopping.shopping.exception.ProduitException;
import com.shopping.shopping.model.Produit;
import com.shopping.shopping.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProduitServiceImp implements ProduitService {
    @Autowired
    private ProduitDao produitDao;
    @Override
    public Produit addProduit(Produit produit) throws ProduitException {
        if(produit.getNomProduit().isEmpty()){
            throw new ProduitException("le nom du produit doit etre renseigner");
        }
        return produitDao.save(produit);
    }

    @Override
    public Produit updateProduit(Produit produit) {
        return produitDao.save(produit);
    }

    @Override
    public Optional<Produit> getProduit(Long idProduit) throws ProduitException {
        Optional<Produit> produit = produitDao.findById(idProduit);
        if(!produit.isPresent()){
            throw new ProduitException("le produit rechercher n'existe pas");
        }
        return produit;
    }

    @Override
    public void deleteProduit(Long idProduit) throws ProduitException {
        if(idProduit == null){
            throw new ProduitException("id doit etre renseigné");
        }else if(!produitDao.existsById(idProduit)){
            throw new ProduitException("l'utilisateur ne peut pas etre supprimé car il n'existe pas");
        }
        produitDao.deleteById(idProduit);
    }

    @Override
    public List<Produit> getProduits() {
        Stream<Produit> listp = produitDao.findAll().stream();
        List<Produit> produits = listp.collect(Collectors.toList());
        return produits;
    }
}
