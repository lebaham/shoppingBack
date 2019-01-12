package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.HistoriqueDao;
import com.shopping.shopping.exception.HistoriqueException;
import com.shopping.shopping.model.Historique;
import com.shopping.shopping.service.HistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Service
public class HistoriqueServiceImp  implements HistoriqueService {
    @Autowired
    private HistoriqueDao historiqueDao;

    @Override
    public Optional<Historique> getHistorique(Long idHistorique) {

        if(historiqueDao.findById(idHistorique) == null){
            throw new HistoriqueException("le compte  recherché n'existe pas");
        }
        return historiqueDao.findById(idHistorique);
    }

    @Override
    public List<Historique> getHistoriques() {
        Stream<Historique> cs = historiqueDao.findAll().stream();
        return cs.collect(Collectors.toList());
    }

    @Override
    public Historique save(Historique historique) {
        return historiqueDao.save(historique);
    }

    @Override
    public void delete(Long idHistorique) {

    }
}
