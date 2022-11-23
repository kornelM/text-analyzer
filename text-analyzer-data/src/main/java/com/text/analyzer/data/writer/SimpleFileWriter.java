package com.text.analyzer.data.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleFileWriter implements AnalyzerFileWriter {

    @Override
    public void writeToFile(String content, String path) {
        Path pathToFile = Paths.get(path);
        BufferedWriter writer = null;
        try {
            Files.deleteIfExists(pathToFile);
            Files.createDirectories(pathToFile.getParent());
            Files.createFile(pathToFile);
            writer = new BufferedWriter(new FileWriter(path, false));
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
