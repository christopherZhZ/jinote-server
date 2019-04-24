package com.frankc137.jinote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User extends Base {

    private String username;
    private String usertype;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
