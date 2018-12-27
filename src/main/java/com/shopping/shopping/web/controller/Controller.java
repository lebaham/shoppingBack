package com.shopping.shopping.web.controller;

import java.util.List;

public interface Controller<T,ID>
{
    T get(ID id);
    T add(T t);
    List<T> getAll();
    void delete(ID id);
}
