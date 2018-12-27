package com.shopping.shopping.web.controller;

import com.shopping.shopping.model.AbstractShoppingModel;
import com.shopping.shopping.serviceImp.AbstractShoppingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @param <T> le type d'entité que ce contrôleur manipulera
 * @param <ID> l'identifiant de l'identité
 */
public class AbstractController<T,ID> implements Controller<T,ID>{

    @Autowired
    protected AbstractShoppingService<T ,ID> abstractService;

    @Override
    public T get(ID id) {

        return abstractService;
        }

    @Override
    public T add(T t) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public void delete(ID id) {

    }
}