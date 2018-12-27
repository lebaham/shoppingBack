package com.shopping.shopping.service;

import java.util.List;

public interface ShoppingService<T,ID> {
    T get(ID id) throws Exception;
    T add(T t);
    List<T> getAll();
    void delete(ID id);
    T update(T t);
}
