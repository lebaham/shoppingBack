package com.shopping.shopping;

import com.shopping.shopping.dao.CommandeDao;
import com.shopping.shopping.model.Commande;
import com.shopping.shopping.serviceImp.CommandeServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CommandeServiceTest {
    private static final Long ID_COMMANDE = Integer.toUnsignedLong(1);
    @InjectMocks
    private CommandeServiceImp commandeServiceImp;
    @Mock
    private CommandeDao commandeDao;
    @Mock
    private Commande commande;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
         commandeServiceImp = new CommandeServiceImp();
    }

    @Test
    public void getCommandeTest(){
        when(commandeDao.findById(ID_COMMANDE)).thenReturn(java.util.Optional.ofNullable(commande));
        Optional<Commande> commande = commandeDao.findById(ID_COMMANDE);
        Optional<Commande> commandeTest = commandeServiceImp.getCommande(ID_COMMANDE);
        assertThat(commandeTest, is(equalTo(commande)));
    }


}
