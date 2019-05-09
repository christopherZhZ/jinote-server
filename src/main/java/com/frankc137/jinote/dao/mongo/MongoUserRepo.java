package com.frankc137.jinote.dao.mongo;

import com.frankc137.jinote.dao.CrudRepo;
import com.frankc137.jinote.dto.User;

import java.util.List;

public interface MongoUserRepo extends CrudRepo<User> {

    List<User> findByName(String name);

    default User findUserByName(String name) {
        List<User> users = findByName(name);
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

}
