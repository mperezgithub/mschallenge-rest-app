package com.intercorpretail.mschallenge.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatsUtil {

    public Double calculateArithmeticMean(List<Integer> data) {
        return data.stream().mapToDouble(i -> i).average().orElse(0.0);
    }

    public Double calculateStandardDeviation(List<Integer> data) {
        Double average = this.calculateArithmeticMean(data);

        // Variance
        Double variance = data.stream()
                .map(i -> i - average)
                .map(i -> i * i)
                .mapToDouble(i -> i).average().getAsDouble();

        //Standard Deviation
        return Math.sqrt(variance);
    }
}
