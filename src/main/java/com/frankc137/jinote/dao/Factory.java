package com.frankc137.jinote.dao;

import com.frankc137.jinote.dto.*;

public interface Factory {

    UserRepo user();

    Repo<Notebook> notebook();

    Repo<Note> note();

    Repo<Share> share();

    default Share fill(Share s) {
        if (s != null && s.getNote() == null) {
            s.setNote(note().get(s.getNoteid()));
        }
        return s;
    }

}
