package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.Utilisateur;
import com.shopping.shopping.service.UtilisateurService;
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
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    private final Logger log= LoggerFactory.getLogger(CommandeController.class);
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/add")
    public ResponseEntity<Utilisateur> addUtilisateur(@Valid @RequestBody Utilisateur utilisateur)throws URISyntaxException {
        log.info("requete pour créer la commande: {} ", utilisateur);
        Utilisateur result = utilisateurService.addUtilisateur(utilisateur);
        return ResponseEntity.created(new URI("/api/utilisateur/add"+ result.getIdUtilisateur())).body(result);
    }

    @GetMapping("/all")
    public List<Utilisateur> getUtilisateurs(){
        return utilisateurService.getUtilisateurs();}

    @GetMapping("/{idUtilisateur}")
    public ResponseEntity<?> getUtilisateur(@PathVariable Long idUtilisateur){
        Optional<Utilisateur> utilisateur =  utilisateurService.getUtilisateur(idUtilisateur);
        return utilisateur.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @PutMapping("/update")
    public ResponseEntity<Utilisateur> updateUtilisateur(@Valid @RequestBody Utilisateur utilisateur){
        log.info("Requete pour mettre à jour l'utilisateur: {}");
        Utilisateur result = utilisateurService.updateUtilisateur(utilisateur);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{idUtilisateur}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable Long idUtilisateur){
        log.info("Requete pour supprimer un utilisateur: {}", idUtilisateur);
        utilisateurService.deleteUtilisateur(idUtilisateur);
        return ResponseEntity.ok().build();
    }
}
