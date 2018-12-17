package com.shopping.shopping.service;

import com.shopping.shopping.model.Historique;

import java.util.List;
import java.util.Optional;

public interface HistoriqueService {
    Historique addHistorique(Historique historique);
    Optional<Historique> getHistorique(Long idHistorique);
    List<Historique> getHistoriques();
}
