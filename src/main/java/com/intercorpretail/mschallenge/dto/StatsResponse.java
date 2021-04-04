package com.intercorpretail.mschallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StatsResponse<T> extends Response {
    @JsonProperty("promedioEdad")
    private Double average;
    @JsonProperty("desviacionEstandard")
    private Double standardDeviation;

    public StatsResponse(Double average, Double standardDeviation, String message, List<T> list) {
        super(message, list);
        this.average = average;
        this.standardDeviation = standardDeviation;
    }
}
