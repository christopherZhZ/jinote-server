package com.frankc137.jinote.service;

import com.frankc137.jinote.dto.User;
import com.frankc137.jinote.security.constant.C;
import com.frankc137.jinote.security.exception.SigninException;
import com.frankc137.jinote.security.exception.SignupException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
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
        User u = users.del(userid);
        if (u == null) return fail();
        return success();
    }

    @RequestMapping(value = "/signup")
    public User signup(@RequestBody User user) {
        User existedUser = users.findUserByName(user.getName());
        if (existedUser != null) {
            throw new SignupException("Username already used!");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return users.set(user);
    }

    @RequestMapping(value = "/login")
    public String login(String name, String password, HttpServletResponse response) {
        User user = users.findUserByName(name);
        if (user == null) {
            throw new SigninException("User does not exist!");
        }
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new SigninException("Incorrect password!");
        }

        List roleList = new ArrayList();
        String subject = user.getName() + "-" + roleList;
        String token = Jwts.builder().setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(SignatureAlgorithm.HS256, C.JWT_SIGNING_KEY).compact();
        response.addHeader("Authorization", "Bearer "+token);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        return user.getId();
    }

}
