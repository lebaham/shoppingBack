package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.CompteDao;
import com.shopping.shopping.model.Compte;
import com.shopping.shopping.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteServiceImp implements CompteService {
    @Autowired
    private CompteDao compteDao;

    @Override
    public Compte addCompte(Compte compte) {
        return compteDao.save(compte);
    }

    @Override
    public void deleteCompte(Long idCompte) {
        compteDao.deleteById(idCompte);
    }

    @Override
    public Compte updateCompte(Compte compte) {
        return compteDao.save(compte);
    }

    @Override
    public Optional<Compte> getComptes(Long idCompte) {
        return compteDao.findById(idCompte);
    }

    @Override
    public List<Compte> getComptes() {
        return compteDao.findAll();
    }
}
