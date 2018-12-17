package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.Compte;
import com.shopping.shopping.service.CompteService;
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
@RequestMapping("/api/compte")
public class CompteController {
    private final Logger log= LoggerFactory.getLogger(CompteController.class);
    @Autowired
    private CompteService compteService;

    @PostMapping("/add")
    public ResponseEntity<Compte> addCompte(@Valid @RequestBody Compte compte)throws URISyntaxException {
        log.info("requete pour créer la commande: {} ", compte);
        Compte result = compteService.addCompte(compte);
        return ResponseEntity.created(new URI("/api/commande/add"+ result.getIdCompte())).body(result);
    }

    @GetMapping("/all")
    public List<Compte> getComptes(){
        return compteService.getComptes();
    }

    @GetMapping("/{idCommande}")
    public ResponseEntity<?> getComptes(@PathVariable Long idCompte){
        Optional<Compte> compte =  compteService.getComptes(idCompte);
        return compte.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @PutMapping("/update")
    public ResponseEntity<Compte> updateCompte(@Valid @RequestBody Compte compte){
        log.info("Requete pour mettre à jour le compte: {}");
        Compte result = compteService.updateCompte(compte);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{idCompte}")
    public ResponseEntity<?> deleteCompte(@PathVariable Long idCompte){
        log.info("Requete pour supprimer un compte: {}", idCompte);
        compteService.deleteCompte(idCompte);
        return ResponseEntity.ok().build();
    }
}
