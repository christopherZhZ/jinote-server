package com.frankc137.jinote.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
public class Base {

    private String id;
    @CreatedDate private Date createdDate;

    public String toJsonString() {
        String jsonStr = null;
        jsonStr = JSON.toJSONString(Base.this);
        return jsonStr;
    }
}
