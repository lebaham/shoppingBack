package com.shopping.shopping.service;
import com.shopping.shopping.model.Historique;

import java.util.List;
import java.util.Optional;

public interface HistoriqueService {
    Optional<Historique> getHistorique(Long idHistorique);
    List<Historique> getHistoriques();
    Historique save(Historique historique);
    void delete(Long idHistorique);

}
