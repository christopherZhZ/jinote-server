package com.frankc137.jinote.dto;

import lombok.Data;
import org.springframework.data.annotation.Transient;

@Data
public class Share extends Base {

    private String noteid;
    private String fromid;
    private String toid;
//    private String accesslevel;
    @Transient private Note note;

}
