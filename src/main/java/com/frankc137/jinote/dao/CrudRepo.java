package com.frankc137.jinote.dao;

import com.frankc137.jinote.dto.Base;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudRepo<T extends Base> extends CrudRepository<T, String> {

    default List<T> listAll() {
        return (List<T>) findAll();
    }

    default List<T> list(String parentid) {
        return listAll();
    }

    default List<T> listBy(String groupid) {
        return list(groupid);
    }

}
