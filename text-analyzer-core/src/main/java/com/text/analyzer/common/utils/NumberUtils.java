package com.text.analyzer.common.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Supplier;

@UtilityClass
public class NumberUtils {

    public static final RoundingMode ROUNDING_MODE = RoundingMode.UP;
    public static final int DEFAULT_DIVIDE_SCALE = 6;

    public static BigDecimal calculateAverage(BigDecimal result, int totalNumberOfAllSearches, Supplier<BigDecimal> methodRef1, Supplier<Integer> methodRef2) {
        return result.add(
                divide(
                        methodRef1.get().multiply(BigDecimal.valueOf(methodRef2.get())),
                        totalNumberOfAllSearches
                )
        );
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return divide(dividend, divisor, DEFAULT_DIVIDE_SCALE, ROUNDING_MODE);
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale) {
        return divide(dividend, divisor, scale, ROUNDING_MODE);
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale, RoundingMode roundingMode) {
        return dividend.divide(divisor, scale, roundingMode);
    }

    public static BigDecimal divide(int dividend, int divisor) {
        BigDecimal dividendBigDecimal = BigDecimal.valueOf(dividend);
        BigDecimal divisorBigDecimal = BigDecimal.valueOf(divisor);

        return dividendBigDecimal.divide(divisorBigDecimal, DEFAULT_DIVIDE_SCALE, ROUNDING_MODE);
    }

    public static BigDecimal divide(BigDecimal dividend, int divisor) {
        BigDecimal divisorBigDecimal = BigDecimal.valueOf(divisor);

        return dividend.divide(divisorBigDecimal, DEFAULT_DIVIDE_SCALE, ROUNDING_MODE);
    }

    public static BigDecimal divide(int dividend, BigDecimal divisor) {
        BigDecimal dividendBigDecimal = BigDecimal.valueOf(dividend);

        return dividendBigDecimal.divide(divisor, DEFAULT_DIVIDE_SCALE, ROUNDING_MODE);
    }
}
