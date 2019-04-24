package com.frankc137.jinote.dao.repo;

import com.frankc137.jinote.dao.CrudRepo;
import com.frankc137.jinote.dto.User;

import java.util.List;

public interface UserRepo extends CrudRepo<User> {

    List<User> findByUsername(String username);

    default User findUserByUsername(String username) {
        List<User> users = findByUsername(username);
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

}
