package com.shopping.shopping;

import com.shopping.shopping.dao.CommandeDao;
import com.shopping.shopping.exception.CommandeException;
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
import static org.junit.Assert.fail;
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

    //testons la suppression de la commande

    @Test
    public void deleteCommandeTestTrue(){
        when(commandeDao.existsById(ID_COMMANDE_1)).thenReturn(true);
        doNothing().when(commandeDao).deleteById(ID_COMMANDE_1);
        CommandeDao comDao = Mockito.mock(CommandeDao.class);
        commandeServiceImp.deleteCommande(ID_COMMANDE_1);
        verify(commandeDao,times(1)).deleteById(ID_COMMANDE_1);
    }

    //testons la suppression de la commande qui echoue
    @Test
    public void deleteCommandeTestFalse(){
        String message = "";
        doNothing().when(commandeDao).deleteById(ID_COMMANDE_1);
        try {
            commandeServiceImp.deleteCommande(ID_COMMANDE_1);
        } catch (CommandeException e){
            assertEquals("la commande n'existe pas!!", e.getMessage());
        }
    }

    //testons la recherche d'une commande
    @Test
    public void getCommandeTestTrue(){
        commande = new Commande();
        commande.setIdCommande(ID_COMMANDE_1);
        when(commandeDao.findById(ID_COMMANDE_1)).thenReturn(java.util.Optional.ofNullable(commande));
        Optional<Commande> commande = commandeDao.findById(ID_COMMANDE_1);
        Optional<Commande> commandeTest = commandeServiceImp.getCommande(ID_COMMANDE_1);
        assertThat(commande, is(equalTo(commande)));
    }

    //testons la recherche d'une commande qui echoue
    @Test
    public void getCommandeTestFalse(){
        try {
               commandeServiceImp.getCommande(ID_COMMANDE_1);
        }catch (CommandeException e){
            assertEquals("la commande recherchée n'existe pas!!",e.getMessage());
        }
    }

    //testons la recherche de tout les produit
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

     //recherche de tout les produits qui echoue
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

}
