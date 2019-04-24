package com.frankc137.jinote.dao;

import com.frankc137.jinote.dto.*;

public interface Factory {

    Repo<User> user();

    Repo<Notebook> notebook();

    Repo<Note> note();

    Repo<Share> share();

}
