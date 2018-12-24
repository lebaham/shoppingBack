package com.shopping.shopping;

import com.shopping.shopping.dao.CompteDao;
import com.shopping.shopping.exception.CompteException;
import com.shopping.shopping.model.Compte;
import com.shopping.shopping.serviceImp.CompteServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CompteServiceTest {
    private static final Long ID_COMPTE_1 = Integer.toUnsignedLong(1);
    private static final Long ID_COMPTE_2 = Integer.toUnsignedLong(2);
    @InjectMocks
    private CompteServiceImp compteServiceImp;
    @Mock
    private CompteDao compteDao;
    @Mock
    private Compte compte;
    @Mock
    private CompteException compteIntrouvableException;
    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addCompteTest(){
        compte.setIdCompte(ID_COMPTE_1);
        compte.setUsername("steve");
        when(compteDao.save(compte)).thenReturn(compte);
        when(compteDao.existsByUsername(compte.getUsername())).thenReturn(true);
        verify(compteDao, times(1)).existsByUsername("steve");
        Compte compteTest = compteServiceImp.addCompte(compte);
        assertEquals("le compte avec le username "+compte.getUsername()+" existe deja",compteTest );

    }
}
