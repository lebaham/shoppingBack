package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.Commande;
import com.shopping.shopping.service.CommandeService;
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
@RequestMapping("/api/commande")
public class CommandeController {
    private final Logger log= LoggerFactory.getLogger(CommandeController.class);
    @Autowired
    private CommandeService commandeService;

    @PostMapping("/add")
   public  ResponseEntity<Commande> addCommande(@Valid  @RequestBody Commande commande)throws URISyntaxException {
        log.info("requete pour créer la commande: {} ", commande);
        Commande result = commandeService.addCommmande(commande);
        return ResponseEntity.created(new URI("/api/commande/add"+ result.getIdCommande())).body(result);
    }

    @GetMapping("/all")
    public  List<Commande> getCommandes(){
        return commandeService.getCommandes();
    }

    @GetMapping("/{idCommande}")
    public ResponseEntity<?> getCommande(@PathVariable Long idCommande){
        Optional<Commande> commande =  commandeService.getCommande(idCommande);
        return commande.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @PutMapping("/update")
    public ResponseEntity<Commande> updateCommande(@Valid @RequestBody Commande commande){
        log.info("Requete pour mettre à jour la commande: {}");
        Commande result = commandeService.updateCommande(commande);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{idCommande}")
    public ResponseEntity<?> deleteCommande(@PathVariable Long idCommande){
        log.info("Requete pour supprimer une commande: {}", idCommande);
        commandeService.deleteCommande(idCommande);
        return ResponseEntity.ok().build();
    }
}
