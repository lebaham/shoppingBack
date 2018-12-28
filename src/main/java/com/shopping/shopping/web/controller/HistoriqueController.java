package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.Commande;
import com.shopping.shopping.model.Historique;
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
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@Api(description = "API pour les  Historiques")
@RestController
public class HistoriqueController {
    private final Logger log= LoggerFactory.getLogger(CommandeController.class);
    @Autowired
    private AbstractShoppingServiceImp<Historique,Long> shoppingHistoriqueService;

    @ApiOperation(value = "ajoute un historique")
    @PostMapping("/historiques")
    public ResponseEntity<Historique> addHistorique(@Valid @RequestBody Historique historique)throws URISyntaxException {
        log.info("requete pour créer l'historique: {} ", historique);
        Historique result = shoppingHistoriqueService.add(historique);
        return ResponseEntity.created(new URI("/api/commande/add"+ result.getIdHistorique())).body(result);
    }

    @ApiOperation(value = "Recupere tout les historiques")
    @GetMapping("/historiques")
    public List<Historique> getHistoriques(){
        return shoppingHistoriqueService.getAll();
    }

    @ApiOperation(value = "Récupere un historique à partir de son id")
    @GetMapping("historiques/{idCommande}")
    public ResponseEntity<?> getHistorique(@PathVariable Long idHistorique){
        Optional<Historique> historique =  shoppingHistoriqueService.get(idHistorique);
        return historique.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }
}
