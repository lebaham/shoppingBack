package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.CompteDao;
import com.shopping.shopping.exception.CompteException;
import com.shopping.shopping.model.Compte;
import com.shopping.shopping.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class CompteServiceImp implements CompteService {
    @Autowired
    private CompteDao compteDao;

    @Override
    public Compte addCompte(Compte compte){
        if(compteDao.existsByUsername(compte.getUsername())){
            throw new CompteException("le compte avec le username "+compte.getUsername()+" existe deja");
        }else if(compteDao.existsByEmail(compte.getEmail())){
            throw new CompteException("le compte avec l'email "+compte.getEmail()+" existe deja");
        }
        return compteDao.save(compte);
    }

    @Override
    public void deleteCompte(Long idCompte) {
        if(!compteDao.existsById(idCompte)){
            throw new CompteException("le compte  recherché n'existe pas");
        }
        compteDao.deleteById(idCompte);
    }

    @Override
    public Compte updateCompte(Compte compte) {
        return compteDao.save(compte);
    }

    @Override
    public Optional<Compte> getComptes(Long idCompte) {
        Compte  compte = compteDao.findById(idCompte).orElseThrow(() -> new CompteException("le compte recherché n'existe pas!!"));
        return Optional.ofNullable(compte);
    }

    @Override
    public List<Compte> getComptes() {
        Stream<Compte> cs = compteDao.findAll().stream();
        return cs.collect(Collectors.toList());
    }
}
