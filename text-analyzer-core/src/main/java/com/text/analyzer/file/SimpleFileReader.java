package com.text.analyzer.file;

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

    public List<String> readToList(String path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(path)), StandardCharsets.UTF_8))) {
            String line;
            while (nonNull(line = in.readLine())) {
                list.add(line);
            }
        } catch (final IOException e) {
            System.out.println("Error while reading file under path: " + path + " REASON: " + e.getMessage());
        }
        return list;
    }

    public String readToString(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
