package com.frankc137.jinote.service;

import com.frankc137.jinote.dto.Notebook;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/notebook")
public class NotebookService extends BaseService {

    @PostMapping(value = "/add")
    public Notebook addNotebook(@RequestHeader String userid,
                                @RequestBody Notebook notebook) {
        notebook.setUserid(userid);
        return notebooks.set(notebook);
    }

    @PostMapping(value = "/get")
    public Notebook getNotebook(@RequestHeader String userid,
                                @RequestParam String notebookid) {
        return notebooks.get(notebookid);
    }

    @PostMapping(value = "/set")
    public Notebook setNotebook(@RequestHeader String userid,
                                @RequestBody Notebook notebook) {
        return notebooks.set(notebook);
    }

//    @RequestMapping(value = "/del")

}
