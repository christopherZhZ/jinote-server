package com.frankc137.jinote.service;

import com.frankc137.jinote.dao.Factory;
import com.frankc137.jinote.dao.Repo;
import com.frankc137.jinote.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;

public abstract class BaseService {

    @Autowired protected Factory factory;
    protected Repo<User> users;
    protected Repo<Notebook> notebooks;
    protected Repo<Note> notes;
    protected Repo<Share> shares;

    @PostConstruct
    private void init() {
        users = factory.user();
        notebooks = factory.notebook();
        notes = factory.note();
        shares = factory.share();
    }

    protected static class ResMap extends HashMap {
        private ResMap() {}

        public static ResMap m(Object key, Object value) {
            ResMap resMap = new ResMap();
            resMap.put(key, value);
            return resMap;
        }
    }

    protected ResMap success() {
        return ResMap.m("status", "success");
    }

    protected ResMap fail() {
        return ResMap.m("status", "fail");
    }

}
