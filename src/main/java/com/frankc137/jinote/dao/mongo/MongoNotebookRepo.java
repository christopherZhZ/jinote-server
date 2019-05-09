package com.frankc137.jinote.dao.mongo;

import com.frankc137.jinote.dao.CrudRepo;
import com.frankc137.jinote.dto.Notebook;

import java.util.List;

public interface MongoNotebookRepo extends CrudRepo<Notebook> {

    @Override
    default List<Notebook> list(String userid) {
        return findByUserid(userid);
    }

    List<Notebook> findByUserid(String userid);

}
