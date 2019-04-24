package com.frankc137.jinote.dao;

import java.util.List;

public interface Repo<T> {

    T get(String id);

    T set(T t);

    List<T> listAll();

    default List<T> list(String pid) {
        return listAll();
    }

    default List<T> listBy(String gid) {
        return listAll();
    }

    T del(String id);

}
