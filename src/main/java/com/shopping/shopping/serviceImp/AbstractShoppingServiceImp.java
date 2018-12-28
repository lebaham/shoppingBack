package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.ShoppingDao;
import com.shopping.shopping.exception.CommandeException;
import com.shopping.shopping.exception.CompteException;
import com.shopping.shopping.exception.ShoppingException;
import com.shopping.shopping.model.Commande;
import com.shopping.shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AbstractShoppingServiceImp<T,ID> implements ShoppingService<T,ID> {
    @Autowired
    private JpaRepository<T,ID> shoppingDao;

    @Override
    public Optional<T> get(ID id) {
        T t = shoppingDao.findById(id).orElseThrow(()-> new ShoppingException("la ressource recherchée n'existe pas"));
        return (Optional<T>) t;
    }

    @Override
    public T add(T t) {
        if(t == null){
            throw  new ShoppingException("les champs de l'objet doivent etre renseignés");
        }
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
            throw new ShoppingException("la resource recherchée n'existe pas");
        }
        shoppingDao.deleteById(id);
    }

    @Override
    public T update(T t) {
        return shoppingDao.save(t);
    }
}
