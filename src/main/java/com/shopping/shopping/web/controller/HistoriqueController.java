package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.Historique;
import com.shopping.shopping.service.HistoriqueService;
import io.swagger.annotations.Api;
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

@Api(description = "API pour les operations CRUD des Historique")
@RestController
@RequestMapping("/api/historique")
public class HistoriqueController {
    private final Logger log= LoggerFactory.getLogger(CommandeController.class);
    @Autowired
    private HistoriqueService historiqueService;

    @PostMapping("/add")
    public ResponseEntity<Historique> addHistorique(@Valid @RequestBody Historique historique)throws URISyntaxException {
        log.info("requete pour créer l'historique: {} ", historique);
        Historique result = historiqueService.addHistorique(historique);
        return ResponseEntity.created(new URI("/api/commande/add"+ result.getIdHistorique())).body(result);
    }

    @GetMapping("/all")
    public List<Historique> getHistoriques(){
        return historiqueService.getHistoriques();
    }

    @GetMapping("/{idCommande}")
    public ResponseEntity<?> getHistorique(@PathVariable Long idHistorique){
        Optional<Historique> historique =  historiqueService.getHistorique(idHistorique);
        return historique.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }
}
