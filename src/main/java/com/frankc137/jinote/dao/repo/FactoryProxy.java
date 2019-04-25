package com.frankc137.jinote.dao.repo;

import com.frankc137.jinote.dao.Factory;
import com.frankc137.jinote.dao.Repo;
import com.frankc137.jinote.dao.RepoProxy;
import com.frankc137.jinote.dto.Note;
import com.frankc137.jinote.dto.Notebook;
import com.frankc137.jinote.dto.Share;
import com.frankc137.jinote.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryProxy implements Factory {

    @Autowired private UserRepo userRepo;
    @Autowired private NotebookRepo notebookRepo;
    @Autowired private NoteRepo noteRepo;
    @Autowired private ShareRepo shareRepo;

    @Override
    public Repo<User> user() {
        return new RepoProxy<>(userRepo);
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
