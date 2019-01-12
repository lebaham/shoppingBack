package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.ProduitDao;
import com.shopping.shopping.exception.ProduitException;
import com.shopping.shopping.model.Produit;
import com.shopping.shopping.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Service
public class ProduitServiceImp implements ProduitService {
    @Autowired
    private ProduitDao produitDao;

    @Override
    public Produit addProduit(Produit produit) {
        if(produit.getNomProduit() == null){
           throw new ProduitException("le nom du produit doit etre renseigné!!");
        }
        Optional<Produit> produitSearch = produitDao.findByNomProduit(produit.getNomProduit());
        if(produitSearch.isPresent()) {
            produitSearch.get().setQuantite(produitSearch.get().getQuantite() + 1);
            produitSearch.get().setDateCreation(LocalDateTime.now());
            return produitDao.save(produitSearch.get());
        }
        produit.setQuantite(1);
        produit.setDateCreation(LocalDateTime.now());
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

    @Override
    public List<Produit> findProduits(Optional<String> nomProduit, Optional<Double> prixMin,
                                      Optional<Double> prixMax, Optional<String> pays) {
        Stream<Produit> produitStream = produitDao.findProduits(nomProduit, prixMin, prixMax, pays).stream();
        return produitStream.collect(Collectors.toList());
    }

    @Override
    public List<Produit> findProduitByName(String nomProduit) {
        Stream<Produit> produitStream = produitDao.findProduitByName(nomProduit).stream();
        return produitStream.collect(Collectors.toList());
    }

}
