package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.ShoppingDao;
import com.shopping.shopping.exception.CompteException;
import com.shopping.shopping.exception.ProduitException;
import com.shopping.shopping.model.AbstractShoppingModel;
import com.shopping.shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AbstractShoppingService<T extends AbstractShoppingModel,ID> implements ShoppingService<T,ID> {
    @Autowired
    protected ShoppingDao<T,ID> shoppingDao;

    @Override
    public T get(ID id) throws Exception {
        new AbstractShoppingModel<T>().g().newInstance();
        if(shoppingDao.findById(id) == null){
            throw new Exception("la resource n'existe pas ");
        }
        return shoppingDao.findById(id).get();
    }

    @Override
    public T add(T t) {
        return shoppingDao.save(t);
    }

    @Override
    public List<T> getAll() {
        Stream<T> cs = shoppingDao.findAll().stream();
        return cs.collect(Collectors.toList());
    }

    @Override
    public void delete(ID id) {
        if(!shoppingDao.existsById(id)){
            throw new CompteException("la resource recherchée n'existe pas");
        }
        shoppingDao.deleteById(id);
    }

    @Override
    public T update(T t) {
        return shoppingDao.save(t);
    }
}
