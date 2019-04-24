package com.frankc137.jinote.dao.repo;

import com.frankc137.jinote.dao.CrudRepo;
import com.frankc137.jinote.dto.Note;

import java.util.List;

public interface NoteRepo extends CrudRepo<Note> {

    @Override
    default List<Note> list(String notebookid) {
        return findByNotebookid(notebookid);
    }

    List<Note> findByNotebookid(String notebookid);
}
