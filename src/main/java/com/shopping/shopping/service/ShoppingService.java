package com.shopping.shopping.service;

import java.util.List;
import java.util.Optional;

public interface ShoppingService<T,ID> {
    Optional<T> get(ID id);
    T add(T t);
    List<T> getAll();
    void delete(ID id);
    T update(T t);
}
