package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.HistoriqueDao;
import com.shopping.shopping.model.Historique;
import com.shopping.shopping.service.HistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueServiceImp implements HistoriqueService {
    @Autowired
    private HistoriqueDao historiqueDao;

    @Override
    public Historique addHistorique(Historique historique) {
        return historiqueDao.save(historique);
    }

    @Override
    public Optional<Historique> getHistorique(Long idHistorique) {
        return historiqueDao.findById(idHistorique);
    }

    @Override
    public List<Historique> getHistoriques() {
        return historiqueDao.findAll();
    }
}
