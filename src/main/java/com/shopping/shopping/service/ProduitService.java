package com.shopping.shopping.service;

import com.shopping.shopping.exception.ProduitException;
import com.shopping.shopping.model.Produit;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    Produit addProduit(Produit produit) throws ProduitException;
    Produit updateProduit(Produit produit);
    Optional<Produit> getProduit(Long idProduit) throws ProduitException;
    void    deleteProduit(Long idProduit) throws ProduitException;
    List<Produit> getProduits();
}
