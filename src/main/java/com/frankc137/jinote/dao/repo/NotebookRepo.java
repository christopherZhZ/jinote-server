package com.frankc137.jinote.dao.repo;

import com.frankc137.jinote.dao.CrudRepo;
import com.frankc137.jinote.dto.Notebook;

import java.util.List;

public interface NotebookRepo extends CrudRepo<Notebook> {

    @Override
    default List<Notebook> list(String userid) {
        return findByUserid(userid);
    }

    List<Notebook> findByUserid(String userid);

}
