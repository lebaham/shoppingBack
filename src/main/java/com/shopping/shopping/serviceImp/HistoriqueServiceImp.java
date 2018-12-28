package com.shopping.shopping.serviceImp;

import com.shopping.shopping.model.Historique;
import com.shopping.shopping.service.HistoriqueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoriqueServiceImp extends AbstractShoppingServiceImp<Historique,Long> implements HistoriqueService {

}
