package com.shopping.shopping;

import com.shopping.shopping.dao.CommandeDao;
import com.shopping.shopping.model.Commande;
import com.shopping.shopping.serviceImp.CommandeServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void getCommandeTestFalse(){
        commande = new Commande();
        commande.setIdCommande(ID_COMMANDE_1);
        when(commandeDao.findById(ID_COMMANDE_1)).thenReturn(java.util.Optional.ofNullable(commande));
        Optional<Commande> commande = commandeDao.findById(ID_COMMANDE_1);
        Optional<Commande> commandeTest = commandeServiceImp.getCommande(ID_COMMANDE_2);
        assertNotEquals(commandeTest, commande);

    }

    @Test
    public void getCommandeTestTrue(){
        commande = new Commande();
        commande.setIdCommande(ID_COMMANDE_1);
        when(commandeDao.findById(ID_COMMANDE_1)).thenReturn(java.util.Optional.ofNullable(commande));
        Optional<Commande> commande = commandeDao.findById(ID_COMMANDE_1);
        Optional<Commande> commandeTest = commandeServiceImp.getCommande(ID_COMMANDE_1);
        assertThat(commandeTest, is(equalTo(commande)));
    }

    @Test
    public void getAllCommandeTestFalse(){
      List<Commande> commandes = new ArrayList<Commande>();
        commande = new Commande();
        Commande commande1 = new Commande();

        commande.setIdCommande(ID_COMMANDE_1);
        commande1.setIdCommande(ID_COMMANDE_2);
        commandes.add(commande);
        commandes.add(commande1);

        when(commandeDao.findAll()).thenReturn(commandes);
        List<Commande> commandeTest = commandeServiceImp.getCommandes();

        assertNotEquals(1, commandeTest.size());

    }

    @Test
    public void getAllCommandeTestTrue(){
        List<Commande> commandes = new ArrayList<Commande>();
        commande = new Commande();
        Commande commande1 = new Commande();

        commande.setIdCommande(ID_COMMANDE_1);
        commande1.setIdCommande(ID_COMMANDE_2);
        commandes.add(commande);
        commandes.add(commande1);

        when(commandeDao.findAll()).thenReturn(commandes);
        List<Commande> commandes1 = commandeDao.findAll();
        List<Commande> commandeTest = commandeServiceImp.getCommandes();

        assertEquals(2, commandeTest.size());
        assertEquals(commandes1, commandeTest);
    }

    @Test
    public void saveCommandeTestFalse(){
        commande = new Commande();
        commande.setIdCommande(ID_COMMANDE_1);
        commande.setDatecreation(LocalDateTime.now());
        commande.setNumeroCommande(1);
        when(commandeDao.save(commande)).thenReturn(commande);
        Commande commandeTest = commandeServiceImp.addCommmande(commande);
        assertNotEquals(Optional.of(2), commandeTest.getIdCommande());
        assertNotEquals(LocalDateTime.now(), commandeTest.getDatecreation());
        assertNotEquals(2, commandeTest.getNumeroCommande());

    }

    @Test
    public void saveCommandeTestTrue(){
        commande = new Commande();
        commande.setIdCommande(ID_COMMANDE_1);
        LocalDateTime localDateTime = LocalDateTime.now();
        commande.setDatecreation(localDateTime);
        commande.setNumeroCommande(1);
        when(commandeDao.save(commande)).thenReturn(commande);
        Commande commandeTest = commandeServiceImp.addCommmande(commande);
        assertEquals(commande.getIdCommande(), commandeTest.getIdCommande());
        assertEquals(commande.getDatecreation(), commandeTest.getDatecreation());
        assertEquals(commande.getNumeroCommande(), commandeTest.getNumeroCommande());
        assertThat(commandeTest, is(equalTo(commande)));
    }

    @Test
    public void deleteCommandeTestTrue(){
        doNothing().when(commandeDao).deleteById(ID_COMMANDE_1);
        CommandeDao comDao = Mockito.mock(CommandeDao.class);
        commandeServiceImp.deleteCommande(ID_COMMANDE_1);
        verify(commandeDao,times(1)).deleteById(ID_COMMANDE_1);
    }
}
