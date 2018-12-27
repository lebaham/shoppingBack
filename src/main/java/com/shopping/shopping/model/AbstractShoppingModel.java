package com.shopping.shopping.model;


import java.lang.reflect.ParameterizedType;

public abstract class AbstractShoppingModel<T>  implements ShoppingModel{

    public Class<T> g(){
        ParameterizedType superClass = (ParameterizedType)getClass().getGenericSuperclass();
        return (Class<T>) superClass.getActualTypeArguments()[0];
    }

    @Override
    public abstract String getName() ;
}
