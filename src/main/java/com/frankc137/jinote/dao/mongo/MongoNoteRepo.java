package com.frankc137.jinote.dao.mongo;

import com.frankc137.jinote.dao.CrudRepo;
import com.frankc137.jinote.dto.Note;

import java.util.List;

public interface MongoNoteRepo extends CrudRepo<Note> {

    @Override
    default List<Note> list(String userid) {
        return findByUserid(userid);
    }

    @Override
    default List<Note> listBy(String notebookid) {
        return findByNotebookid(notebookid);
    }

    List<Note> findByNotebookid(String notebookid);

    List<Note> findByUserid(String userid);
}
