package com.frankc137.jinote.service;

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
        note.setUserid(userid);
        return notes.set(note);
    }

    @GetMapping(value = "/get")
    public Note getNote(@RequestHeader String userid,
                        @RequestParam String noteid) {
        return notes.get(noteid);
    }

    @RequestMapping(value = "/set")
    public Note setNote(@RequestHeader String userid,
                        @RequestBody Note note) {
        return notes.set(note);
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
