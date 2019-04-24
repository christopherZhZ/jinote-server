package com.frankc137.jinote.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
public class Base {

    private String id;
    @CreatedDate private Date createdDate;
    @LastModifiedDate private Date lastModifiedDate;

    public String toJsonString() {
        String jsonStr = null;
        jsonStr = JSON.toJSONString(Base.this);
        return jsonStr;
    }
}
