package com.frankc137.jinote.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frankc137.jinote.dto.Note;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/note")
public class NoteService extends BaseService {

    @PostMapping(value = "/add")
    public Note addNote(@RequestHeader String userid,
                        @RequestBody Note note) {
        Note n = Note.readNoteObject(note.toJsonString());
        n.setUserid(userid);
        return notes.set(n);
    }

    @GetMapping(value = "/get")
    public Note getNote(@RequestHeader String userid,
                        @RequestParam String noteid) {
        return notes.get(noteid);
    }

    @RequestMapping(value = "/set")
    public Note setNote(@RequestHeader String userid,
                        @RequestBody Note note) {
        Note n = Note.readNoteObject(note.toJsonString());
        return notes.set(n);
    }

    @RequestMapping(value = "/list")
    public List<Note> listNote(@RequestHeader String userid,
                               @RequestParam String notebookid) {
        return notes.list(notebookid);
    }

    @RequestMapping(value = "/del")
    public Map delNote(@RequestHeader String userid,
                       @RequestParam String noteid) {
        Note n = notes.del(noteid);
        if (n == null) return fail();
        shares.listBy(n.getId()).forEach(s -> shares.del(s.getId()));
        return success();
    }

}
