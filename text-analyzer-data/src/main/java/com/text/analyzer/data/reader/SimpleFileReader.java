package com.text.analyzer.data.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class SimpleFileReader implements AnalyzerFileReader {

    @Override
    public List<String> readAsList(String path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8))) {
            String line;
            while (nonNull(line = in.readLine())) {
                list.add(line);
            }
        } catch (final IOException e) {
            throw new RuntimeException("Error while reading file under path: " + path + " REASON: " + e.getMessage());
        }
        return list;
    }

    @Override
    public String readToString(String path) {
        if (nonNull(path)) {
            try {
                return Files.readString(Path.of(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("Resource not found! " + path);
    }
}
