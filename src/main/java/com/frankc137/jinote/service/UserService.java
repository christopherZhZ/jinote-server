package com.frankc137.jinote.service;

import com.frankc137.jinote.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserService extends BaseService {

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/add")
    public User addUser(@RequestBody User user) {
        return users.set(user);
    }

    @RequestMapping(value = "/get")
    public User getUser(@RequestParam String userid) {
        return users.get(userid);
    }

    @PostMapping(value = "/set")
    public User setUser(@RequestBody User user) {
        return users.set(user);
    }

    @RequestMapping(value = "/list")
    public List<User> listUser() {
        return users.listAll();
    }

    @RequestMapping(value = "/del")
    public Map delUser(@RequestParam String userid) {
        users.del(userid);
        return success();
    }

}
