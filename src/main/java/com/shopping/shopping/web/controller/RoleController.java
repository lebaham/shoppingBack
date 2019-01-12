package com.shopping.shopping.web.controller;
import com.shopping.shopping.model.Role;
import com.shopping.shopping.service.HistoriqueService;
import com.shopping.shopping.service.RoleService;
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

@CrossOrigin(origins="*")
@Api(description = "API pour les roles")
@RestController
public class RoleController {
    private final Logger log= LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private HistoriqueService historiqueService;

    @PostMapping("/roles")
    public ResponseEntity<Role> addRole(@Valid @RequestBody Role role)throws URISyntaxException {
        log.info("requete pour créer le role: {} ", role);
        Role result = roleService.addRole(role);
        return ResponseEntity.created(new URI("/api/role/add"+ result.getIdRole())).body(result);
    }

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @GetMapping("roles/{idRole}")
    public ResponseEntity<?> getRole(@PathVariable Long idRole){
        Optional<Role> role =  roleService.getRole(idRole);
        return role.map(response -> ResponseEntity.ok().body(response))
                .orElse((new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @PutMapping("/roles")
    public ResponseEntity<Optional<Role>> updateRole(@Valid @RequestBody Role role){
        log.info("Requete pour mettre à jour le role: {}");
        Optional<Role> result = roleService.getRole(role.getIdRole());
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("roles/{idRole}")
    public ResponseEntity<?> deleteRole(@PathVariable Long idRole){
        log.info("Requete pour supprimer un RoleEnum: {}", idRole);
        roleService.deleteRole(idRole);
        return ResponseEntity.ok().build();
    }
}
