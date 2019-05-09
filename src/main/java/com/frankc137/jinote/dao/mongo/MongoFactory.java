package com.frankc137.jinote.dao.mongo;

import com.frankc137.jinote.dao.Factory;
import com.frankc137.jinote.dao.Repo;
import com.frankc137.jinote.dao.RepoProxy;
import com.frankc137.jinote.dao.UserRepo;
import com.frankc137.jinote.dto.Note;
import com.frankc137.jinote.dto.Notebook;
import com.frankc137.jinote.dto.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoFactory implements Factory {

    @Autowired private MongoUserRepo userRepo;
    @Autowired private MongoNotebookRepo notebookRepo;
    @Autowired private MongoNoteRepo noteRepo;
    @Autowired private MongoShareRepo shareRepo;

    @Override
    public UserRepo user() {
        return new RepoProxy<>(userRepo).proxying(UserRepo.class);
    }

    @Override
    public Repo<Notebook> notebook() {
        return new RepoProxy<>(notebookRepo);
    }

    @Override
    public Repo<Note> note() {
        return new RepoProxy<>(noteRepo);
    }

    @Override
    public Repo<Share> share() {
        return new RepoProxy<>(shareRepo);
    }
}
