package com.shopping.shopping;

import com.shopping.shopping.dao.ProduitDao;
import com.shopping.shopping.model.Produit;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import com.shopping.shopping.serviceImp.ProduitServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProduitServiceTest {
    private static final Long ID_PRODUIT_1 = Integer.toUnsignedLong(1);
    private static final Long ID_PRODUIT_2 = Integer.toUnsignedLong(2);
    @InjectMocks
    private ProduitServiceImp produitServiceImp;
    @Mock
    private ProduitDao produitDao;
    @Mock
    private Produit produit;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addProduitTest(){
        produit.setIdProduit(ID_PRODUIT_1);
        LocalDateTime localDate = LocalDateTime.now();
        produit.setDateCreation(localDate);
        produit.setDescription("produit certifié");
        produit.setPays("cameroun");
        when(produitDao.save(produit)).thenReturn(produit);
        Produit produitTest = produitServiceImp.addProduit(produit);
        assertEquals(produit.getIdProduit(), produitTest.getIdProduit());
        assertEquals(produit.getDescription(), produitTest.getDescription());
        assertEquals(produit.getPays(), produitTest.getPays());
        assertEquals(produit.getDateCreation(), produitTest.getDateCreation());
        verify(produitDao,times(1)).save(produit);
        assertThat(produitTest, is(equalTo(produit)));
    }

    public void getProduitTest(){
        produit.setIdProduit(ID_PRODUIT_1);
        when(produitDao.findById(ID_PRODUIT_1)).thenReturn(Optional.ofNullable(produit));
        Optional<Produit> produitTest = produitServiceImp.getProduit(ID_PRODUIT_1);
        verify(produitDao,times(1)).findById(ID_PRODUIT_1);
        verify(produitDao,never()).deleteById(ID_PRODUIT_1);
        assertThat(produitTest, is(equalTo(produit)));
    }
}
