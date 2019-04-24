package com.frankc137.jinote.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class Note extends Base {

    private String notebookid;
    private String userid;
    private JsonNode content;

}
