package com.intercorpretail.mschallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
public class Response<T> {
    @JsonProperty("mensaje")
    private String message;
    @JsonProperty("datos")
    private List<T> data = new ArrayList<>();
}
