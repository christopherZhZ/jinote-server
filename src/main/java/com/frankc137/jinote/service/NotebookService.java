package com.frankc137.jinote.service;

import com.frankc137.jinote.dto.Note;
import com.frankc137.jinote.dto.Notebook;
import com.frankc137.jinote.security.exception.AppException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/notebook")
public class NotebookService extends BaseService {

    @PostMapping(value = "/add")
    public Notebook addNotebook(@RequestHeader String userid,
                                @RequestBody Notebook notebook) {
        notebook.setUserid(userid);
        return notebooks.set(notebook);
    }

    @GetMapping(value = "/get")
    public Notebook getNotebook(@RequestHeader String userid,
                                @RequestParam String notebookid) {
        return notebooks.get(notebookid);
    }

    @RequestMapping(value = "/set")
    public Notebook setNotebook(@RequestHeader String userid,
                                @RequestBody Notebook notebook) {
        return notebooks.set(notebook);
    }

    @RequestMapping(value = "/rename")
    public Notebook renameNotebook(@RequestHeader String userid,
                                   @RequestParam String notebookid,
                                   @RequestParam String title) {
        Notebook nb = notebooks.get(notebookid);
        if (nb == null) {
            throw new AppException("notebookid does not exist!");
        }
        nb.setTitle(title);
        return notebooks.set(nb);
    }

    @RequestMapping(value = "/list")
    public List<Notebook> listNotebook(@RequestHeader String userid) {
        return notebooks.list(userid);
    }

    @RequestMapping(value = "/del")
    public Map delNotebook(@RequestHeader String userid,
                           @RequestParam String notebookid) {
        Notebook nb = notebooks.del(notebookid);
        if (nb == null) return fail();
        List<Note> noteList = notes.list(nb.getId());
        noteList.forEach(note -> {
            notes.del(note.getId());
            shares.listBy(note.getId()).forEach(s -> shares.del(s.getId()));
        });
        return success();
    }

}
