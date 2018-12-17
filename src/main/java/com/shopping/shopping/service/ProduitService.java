package com.shopping.shopping.service;

import com.shopping.shopping.model.Produit;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    Produit addProduit(Produit produit);
    Produit updateProduit(Produit produit);
    Optional<Produit> getProduit(Long idProduit);
    void    deleteProduit(Long idProduit);
    List<Produit> getProduits();
}
