package com.shopping.shopping.web.controller;

import com.shopping.shopping.enumeration.CommandeStatus;
import com.shopping.shopping.model.Commande;
import com.shopping.shopping.model.Historique;
import com.shopping.shopping.service.CommandeService;
import com.shopping.shopping.service.HistoriqueService;
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
import java.util.Random;

@CrossOrigin(origins="*")
@Api(description = "API pour les commandes")
@RestController
public class CommandeController{
    private final Logger log= LoggerFactory.getLogger(CommandeController.class);
    @Autowired
    private CommandeService commandeService;
    @Autowired
    private HistoriqueService historiqueService;

    @ApiOperation(value = "Ajoute une commande")
    @PostMapping("/commande")
   public  ResponseEntity<Commande> addCommande(@Valid  @RequestBody Commande commande)throws URISyntaxException {
        log.info("requete pour créer la commande: {} ", commande);
        Random r = new Random();
        commande.setNumeroCommande(r.nextInt());
        commande.setStatusCommande(CommandeStatus.EN_PREPARATION.getName());
        Commande result = commandeService.addCommmande(commande);
        Historique historique = new Historique();
        historique.setAction("commande ajouté à "+ LocalDateTime.now());
        historique.setCommande(commande);
        historiqueService.save(historique);
        return ResponseEntity.created(new URI("/commandes"+ result.getIdCommande())).body(result);
    }

    @ApiOperation(value = "Récupere les commandes en stock")
    @GetMapping("/commandes")
    public  List<Commande> getCommandes(){
        return commandeService.getCommandes();
    }

    @ApiOperation(value = "Récupere une commande grace à son id")
    @GetMapping("/commande/{idCommande}")
    public ResponseEntity<?> getCommande(@PathVariable Long idCommande){
        Optional<Commande> commande =  commandeService.getCommande(idCommande);
        return commande.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @ApiOperation(value = "met à jour une commande grace à son id")
    @PutMapping("/commande")
    public ResponseEntity<Commande> updateCommande(@Valid @RequestBody Commande commande){
        log.info("Requete pour mettre à jour la commande: {}");
        Commande result = commandeService.addCommmande(commande);
        Historique historique = new Historique();
        historique.setAction("commande modifiée à "+ LocalDateTime.now());
        historique.setCommande(commande);
        historiqueService.save(historique);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Supprime une commande grace à son id")
    @DeleteMapping("/commande/{idCommande}")
    public ResponseEntity<?> deleteCommande(@PathVariable Long idCommande){
        log.info("Requete pour supprimer une commande: {}", idCommande);
         Optional<Commande> commande = commandeService.getCommande(idCommande);
        if(commande.isPresent()) {
            commandeService.deleteCommande(idCommande);
            Historique historique = new Historique();
            historique.setAction("commande à été supprimé à " + LocalDateTime.now());
            historique.setCommande(commande.get());
            historiqueService.save(historique);
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "renvoie la liste des commandes du client")
    @GetMapping("/commandes/{idUtilisateur}")
    public List<Commande> getCommandesByUtilisateur(@PathVariable Long idUtilisateur){
        log.info("Requete pour recuperer la liste des commandes du client: {}", idUtilisateur);
        return commandeService.findCommandeByUtilisateur(idUtilisateur);
    }
}
