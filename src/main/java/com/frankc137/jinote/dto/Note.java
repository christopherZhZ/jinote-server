package com.frankc137.jinote.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Map;

@Data
public class Note extends Base {

    private String notebookid;
    private String userid;
    private String title;
    private Map<String, Object> contents;

    @JsonAnySetter
    public void setContent(String key, Object value) {
        contents.put(key, value);
    }

    public static Note readNoteObject(String noteStr) {
        Note n;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            n = objectMapper.readValue(noteStr, Note.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return n;
    }

}
