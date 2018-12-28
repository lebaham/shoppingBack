package com.shopping.shopping;

import com.shopping.shopping.dao.CommandeDao;
import com.shopping.shopping.model.Commande;
import com.shopping.shopping.serviceImp.CommandeServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommandeServiceTest {
    private static final Long ID_COMMANDE_1 = Integer.toUnsignedLong(1);
    private static final Long ID_COMMANDE_2 = Integer.toUnsignedLong(2);
    @InjectMocks
    private CommandeServiceImp commandeServiceImp;
    @Mock
    private CommandeDao commandeDao;
    @Mock
    private Commande commande;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
    }

    // testons la sauvegarde d'une commande qui reussi
    @Test
    public void saveCommandeTestTrue(){
        commande = new Commande();
        commande.setIdCommande(ID_COMMANDE_1);
        LocalDateTime localDateTime = LocalDateTime.now();
        commande.setDatecreation(localDateTime);
        commande.setNumeroCommande(1);
        when(commandeDao.save(commande)).thenReturn(commande);
        Commande commandeTest = commandeServiceImp.addCommmande(commande);
        verify(commandeDao).save(commande);
        assertThat(commandeTest, is(equalTo(commande)));
    }

    //testons la sauvegarde qui echoue
    @Test
    public void saveCommandeFalse(){
        commande = new Commande();
        LocalDateTime localDateTime = LocalDateTime.now();
        commande.setDatecreation(localDateTime);
        commande.setNumeroCommande(1);
        Commande commandeTest = new Commande();
        commandeTest.setIdCommande(ID_COMMANDE_2);
        when(commandeDao.save(commande)).thenReturn(commande);
        Commande commande1 = commandeServiceImp.addCommmande(commande);
        assertNotEquals(commandeTest,commande1);
    }

}
