package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.Historique;
import com.shopping.shopping.model.Produit;
import com.shopping.shopping.service.HistoriqueService;
import com.shopping.shopping.service.ProduitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins="*")
@Api(description = "API pour les  produits")
@RestController
public class ProduitController {
    private final Logger log= LoggerFactory.getLogger(ProduitController.class);
    @Autowired
    private ProduitService produitService;
    @Autowired
    private HistoriqueService historiqueService;

    @ApiOperation(value = "Ajoute un produit")
    @PostMapping("/produit")
    public ResponseEntity<Produit> addProduit(@Valid @RequestBody Produit produit)throws URISyntaxException {
        log.info("requete pour créer le produit: {} ", produit);
        Produit result = produitService.addProduit(produit);
        Historique historique = new Historique();
        historique.setAction("produit a été ajouter à "+ LocalDateTime.now());
        historique.setProduit(result);
        historiqueService.save(historique);
        return ResponseEntity.created(new URI("/api/produit/add"+ result.getIdProduit())).body(result);
    }

    @ApiOperation(value = "Recupere tout les produits en stock")
    @GetMapping("/produits")
    public List<Produit> getProduits(){
        return produitService.getProduits();
    }

    @ApiOperation(value = "Recupere un produit grace à son ID")
    @GetMapping("produits/{idProduit}")
    public ResponseEntity<?> getProduit(@PathVariable Long idProduit){
        Optional<Produit> produit =  produitService.getProduit(idProduit);
        return produit.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @ApiOperation(value = "met à jour un produit")
    @PutMapping("/produits")
    public ResponseEntity<Produit> updateProduits(@Valid @RequestBody Produit produit){
        log.info("Requete pour mettre à jour le produit: {}");
        Produit result = produitService.addProduit(produit);
        Historique historique = new Historique();
        historique.setAction("produit a été modifié à "+ LocalDateTime.now());
        historique.setProduit(result);
        historiqueService.save(historique);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
    @DeleteMapping("produits/{idProduit}")
    public ResponseEntity<?> deleteProduit(@PathVariable Long idProduit){
        log.info("Requete pour supprimer un produit: {}", idProduit);
        Optional<Produit> produit = produitService.getProduit(idProduit);
        produitService.deleteProduit(idProduit);
        if(produit.isPresent()) {
            Historique historique = new Historique();
            historique.setAction("le produit à été supprimé à "+ LocalDateTime.now());
            historique.setProduit(produit.get());
            historiqueService.save(historique);
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Recherche d'un produit par nomProduit")
    @GetMapping("produits/{nomProduit}")
    public List<Produit> getProduitsByName(@PathVariable String nomProduit){
        log.info("Requete pour rechercher un produit par nom: {}", nomProduit);
        return produitService.findProduitByName(nomProduit);
    }

    @ApiOperation(value = "Recherche d'un produit par nomProduit")
    @GetMapping("produits/{nomProduit}/{prixMin}/{prixMax}/{pays}")
    public List<Produit> getProduitsByCriteria(@PathVariable Optional<String> nomProduit, @PathVariable Optional<Double> prixMin,
                                               @PathVariable Optional<Double> prixMax, @PathVariable Optional<String> pays){
        log.info("Requete pour rechercher un produit par criteres: {}", nomProduit, prixMin, prixMax, pays );
        return produitService.findProduits(nomProduit, prixMin, prixMax, pays);
    }


    public List<Produit> findProduits(Optional<String> nomProduit, Optional<Double> prixMin,
                                      Optional<Double> prixMax, Optional<String> pays) {
        Stream<Produit> produitStream = produitDao.findProduits(nomProduit, prixMin, prixMax, pays).stream();
        return produitStream.collect(Collectors.toList());
    }

}
