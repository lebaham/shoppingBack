package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.Compte;
import com.shopping.shopping.model.Historique;
import com.shopping.shopping.service.CompteService;
import com.shopping.shopping.service.HistoriqueService;
import com.shopping.shopping.service.ShoppingService;
import com.shopping.shopping.serviceImp.AbstractShoppingServiceImp;
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

@CrossOrigin(origins="*")
@Api(description = "API pour les comptes")
@RestController
public class CompteController {

    private final Logger log= LoggerFactory.getLogger(CompteController.class);
    @Autowired
    private HistoriqueService historiqueService;
    @Autowired
    private AbstractShoppingServiceImp<Compte,Long> shoppingCompteService;
    @Autowired
    private AbstractShoppingServiceImp<Historique,Long> shoppingHistoriqueService;



    @ApiOperation(value = "Ajoute un compte")
    @PostMapping("/comptes")
    public ResponseEntity<Compte> addCompte(@Valid @RequestBody Compte compte)throws URISyntaxException {
        log.info("requete pour créer la commande: {} ", compte);
        Compte result = shoppingCompteService.add(compte);
        Historique historique = new Historique();
        historique.setAction("compte ajouté à "+ LocalDateTime.now());
        historique.setCompte(result);
        shoppingHistoriqueService.add(historique);
        return ResponseEntity.created(new URI("/api/commande/add"+ result.getIdCompte())).body(result);
    }

    @ApiOperation(value = "Récupere tout les comptes")
    @GetMapping("/comptes")
    public List<Compte> getComptes(){
        return shoppingCompteService.getAll();
    }

    @ApiOperation(value = "Récupere un compte à partir de son id")
    @GetMapping("/comptes/{idCommande}")
    public ResponseEntity<?> getCompte(@PathVariable Long idCompte){
        Optional<Compte> compte =  shoppingCompteService.get(idCompte);
        return compte.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @ApiOperation(value = "Met à jour un compte")
    @PutMapping("/comptes")
    public ResponseEntity<Compte> updateCompte(@Valid @RequestBody Compte compte){
        log.info("Requete pour mettre à jour le compte: {}");
        Compte result = shoppingCompteService.update(compte);
        Historique historique = new Historique();
        historique.setAction("compte mis à jour à "+ LocalDateTime.now());
        historique.setCompte(result);
        shoppingHistoriqueService.add(historique);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Supprime un compte grace à partir de son id")
    @DeleteMapping("comptes/{idCompte}")
    public ResponseEntity<?> deleteCompte(@PathVariable Long idCompte){
        log.info("Requete pour supprimer un compte: {}", idCompte);
        Optional<Compte> compte = shoppingCompteService.get(idCompte);
        if(compte.isPresent()) {
            shoppingHistoriqueService.delete(idCompte);
            Historique historique = new Historique();
            historique.setAction("commande à été supprimé à "+ LocalDateTime.now());
            historique.setCompte(compte.get());
            shoppingHistoriqueService.add(historique);
        }

        return ResponseEntity.ok().build();
    }
}
