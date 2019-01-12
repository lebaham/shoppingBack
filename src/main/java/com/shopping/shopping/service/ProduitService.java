package com.shopping.shopping.service;

import com.shopping.shopping.exception.ProduitException;
import com.shopping.shopping.model.Produit;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ProduitService {
    Produit addProduit(Produit produit);

  Optional<Produit> getProduit(Long idProduit);

   void deleteProduit(Long idProduit);

   List<Produit> getProduits();

    List<Produit> findProduits(  Optional<String> nomProduit, Optional<Double> prixMin,  Optional<Double> prixMax, Optional<String> pays);

    List<Produit> findProduitByName(String nomProduit);

}
