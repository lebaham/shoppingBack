package com.shopping.shopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingDao<T,ID> extends JpaRepository<T,ID> {

}
