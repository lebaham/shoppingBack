package com.shopping.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.shopping.model.Produit;
import com.shopping.shopping.service.ProduitService;
import static org.mockito.BDDMockito.*;
import com.shopping.shopping.web.controller.ProduitController;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ProduitController.class)
public class ProduitControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProduitService produitService;
    private ObjectMapper mapper = new ObjectMapper();
}
