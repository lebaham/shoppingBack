package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.Produit;
import com.shopping.shopping.service.ProduitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produit")
public class ProduitController {
    private final Logger log= LoggerFactory.getLogger(ProduitController.class);
    @Autowired
    private ProduitService produitService;

    @PostMapping("/add")
    public ResponseEntity<Produit> addProduit(@Valid @RequestBody Produit produit)throws URISyntaxException {
        log.info("requete pour créer le produit: {} ", produit);
        Produit result = produitService.addProduit(produit);
        return ResponseEntity.created(new URI("/api/produit/add"+ result.getIdProduit())).body(result);
    }

    @GetMapping("/all")
    public List<Produit> getProduits(){
        return produitService.getProduits();
    }

    @GetMapping("/{idProduit}")
    public ResponseEntity<?> getProduits(@PathVariable Long idProduit){
        Optional<Produit> produit =  produitService.getProduit(idProduit);
        return produit.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @PutMapping("/update")
    public ResponseEntity<Produit> updateProduits(@Valid @RequestBody Produit produit){
        log.info("Requete pour mettre à jour le produit: {}");
        Produit result = produitService.updateProduit(produit);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{idProduit}")
    public ResponseEntity<?> deleteProduit(@PathVariable Long idProduit){
        log.info("Requete pour supprimer un produit: {}", idProduit);
        produitService.deleteProduit(idProduit);
        return ResponseEntity.ok().build();
    }
}
