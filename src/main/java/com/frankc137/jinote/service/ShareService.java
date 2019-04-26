package com.frankc137.jinote.service;

import com.frankc137.jinote.dto.Note;
import com.frankc137.jinote.dto.Share;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public class ShareService extends BaseService {

    @PostMapping(value = "/add")
    public Share addShare(@RequestHeader String userid,
                    @RequestParam String toid,
                    @RequestParam String noteid) {
        Share s = new Share();
        s.setFromid(userid);
        s.setToid(toid);
        s.setNoteid(noteid);
        return shares.set(factory.fill(s));
    }

    @GetMapping(value = "/get")
    public Share getShare(@RequestHeader String userid,
                          @RequestParam String shareid) {
        return shares.get(shareid);
    }

    @RequestMapping(value = "/listmine")
    public List<Share> listShareToMe(@RequestHeader String userid) {
        return shares.list(userid);
    }

    @RequestMapping(value = "/listbynote")
    public List<Share> listByNote(@RequestHeader String userid,
                                  @RequestParam String noteid) {
        return shares.listBy(noteid);
    }

    @RequestMapping(value = "/del")
    public Map delShare(@RequestHeader String userid,
                        @RequestParam String shareid) {
        Share s = shares.del(shareid);
        if (s == null) return fail();
        return success();
    }

}
