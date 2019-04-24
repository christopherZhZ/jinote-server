package com.frankc137.jinote.dao.repo;

import com.frankc137.jinote.dao.CrudRepo;
import com.frankc137.jinote.dto.Share;

import java.util.List;

public interface ShareRepo extends CrudRepo<Share> {

    @Override
    default List<Share> list(String toid) {
        return findByToid(toid);
    }

    List<Share> findByToid(String toid);

    @Override
    default List<Share> listBy(String noteid) {
        return findByNoteid(noteid);
    }

    List<Share> findByNoteid(String noteid);
}
