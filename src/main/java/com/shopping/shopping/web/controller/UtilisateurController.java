package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.Historique;
import com.shopping.shopping.model.Utilisateur;
import com.shopping.shopping.service.HistoriqueService;
import com.shopping.shopping.service.UtilisateurService;
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
@Api(description = "API pour les utilisateurs")
@RestController
public class UtilisateurController {
    private final Logger log= LoggerFactory.getLogger(CommandeController.class);
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private HistoriqueService historiqueService;

    @ApiOperation(value = "Ajoute les utilisateurs")
    @PostMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> addUtilisateur(@Valid @RequestBody Utilisateur utilisateur)throws URISyntaxException {
        log.info("requete pour créer la commande: {} ", utilisateur);
        Utilisateur result = utilisateurService.addUtilisateur(utilisateur);
        Historique historique = new Historique();
        historique.setAction("l'utilisateur a été ajouté à "+ LocalDateTime.now());
        historique.setUtilisateur(result);
        historiqueService.addHistorique(historique);
        return ResponseEntity.created(new URI("/api/utilisateur/add"+ result.getIdUtilisateur())).body(result);
    }

    @ApiOperation(value = "Récupere tout les utilisateurs")
    @GetMapping("/utilisateurs")
    public List<Utilisateur> getUtilisateurs(){
        return utilisateurService.getUtilisateurs();}

    @ApiOperation(value = "Récupere tout utilisateur à partir de leur id")
    @GetMapping("utilisateurs/{idUtilisateur}")
    public ResponseEntity<?> getUtilisateur(@PathVariable Long idUtilisateur){
        Optional<Utilisateur> utilisateur =  utilisateurService.getUtilisateur(idUtilisateur);
        return utilisateur.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @ApiOperation(value = "met à jour un utilisateur")
    @PutMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> updateUtilisateur(@Valid @RequestBody Utilisateur utilisateur){
        log.info("Requete pour mettre à jour l'utilisateur: {}");
        Utilisateur result = utilisateurService.updateUtilisateur(utilisateur);
        Historique historique = new Historique();
        historique.setAction("l'utilisateur a été modifié à "+ LocalDateTime.now());
        historique.setUtilisateur(result);
        historiqueService.addHistorique(historique);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "Supprime un utilisateur à partir de son id")
    @DeleteMapping("utilisateurs/{idUtilisateur}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable Long idUtilisateur){
        log.info("Requete pour supprimer un utilisateur: {}", idUtilisateur);
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateur(idUtilisateur);
        utilisateurService.deleteUtilisateur(idUtilisateur);
        if(utilisateur.isPresent()) {
            Historique historique = new Historique();
            historique.setAction("l'utilisateur à été supprimé à "+ LocalDateTime.now());
            historique.setUtilisateur(utilisateur.get());
            historiqueService.addHistorique(historique);
        }
        return ResponseEntity.ok().build();
    }
}
