package com.bfhl.solution.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BfhlRequest {

    @JsonProperty("data")
    private List<String> data;

    public List<String> getData() { return data; }
    public void setData(List<String> data) { this.data = data; }
}
