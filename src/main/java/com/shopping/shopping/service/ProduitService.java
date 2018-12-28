package com.shopping.shopping.service;

import com.shopping.shopping.exception.ProduitException;
import com.shopping.shopping.model.Produit;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    Produit addProduit(Produit produit) throws ProduitException;
}
