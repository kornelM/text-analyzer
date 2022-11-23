package com.text.analyzer.html.color;

import com.text.analyzer.html.comparator.RandomComparator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Color {

    RED("#e30b0b"),
    YELLOW("#f5d907"),
    GREEN("#269615"),
    BLUE("#0217f7"),
    PINK("#f57c73"),
    ORANGE("#ed7002"),
    GREY("#5e5c5c"),
    BROWN("#592d1d"),
    PURPLE("#8305f2"),
    DARK_BLUE("#071070"),
    LIGHT_BLUE("#0593f2"),
    DARK_GREEN("#0c4004"),
    LIGHT_GREEN("#02f779");

    private static final List<Color> colors = List.of(Color.values());
    private final String hex;

    public static List<String> getNumberOfColors(int number) {
        return colors.stream()
                .sorted(new RandomComparator<>())
                .limit(number)
                .map(Color::getHex)
                .collect(Collectors.toList());
    }
}
