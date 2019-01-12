package com.shopping.shopping.dao;

import com.shopping.shopping.model.Commande;
import com.shopping.shopping.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitDao extends JpaRepository<Produit, Long> {

    Optional<Produit> findByNomProduit(String nomProduit);

    @Query(value = "select p from Produit p" +
            "where p.nomProduit like :nomProduit and (p.prix between :prixMin and :prixMax) and p.pays like :pays")
    List<Produit> findProduits(@Param("nomProduit") Optional<String> nomProduit,
                                            @Param("prixMin") Optional<Double> prixMin, @Param("prixMax") Optional<Double> prixMax,
                                            @Param("pays") Optional<String> pays);

    @Query(value = "select p from produit p where p.nomProduit like :name order by nomProduit asc")
    List<Produit> findProduitByName(@Param("name") String nomProduit);
}
