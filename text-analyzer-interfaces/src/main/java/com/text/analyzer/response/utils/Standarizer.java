package com.text.analyzer.response.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.util.Objects.isNull;

@UtilityClass
public class Standarizer {

    private static final BigDecimal PERCENT_MULTIPLIER_ONE_HUNDRED = BigDecimal.valueOf(100);

    public BigDecimal standarizeAvgValue(BigDecimal value) {
        if (isNull(value)) {
            return null;
        }
        return value
                .stripTrailingZeros()
                .setScale(2, RoundingMode.UP);
    }

    public BigDecimal standarizePercentValue(BigDecimal value) {
        if (isNull(value)) {
            return null;
        }
        return value
                .stripTrailingZeros()
                .multiply(PERCENT_MULTIPLIER_ONE_HUNDRED)
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
