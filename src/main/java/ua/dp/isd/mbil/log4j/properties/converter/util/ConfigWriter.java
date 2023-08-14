package ua.dp.isd.mbil.log4j.properties.converter.util;

import ua.dp.isd.mbil.log4j.properties.converter.model.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigWriter {
    public void write(Config config, String destination) {
        Path destinationPath = Paths.get(destination);
        try {
            if (!Files.exists(destinationPath)) {
                Files.createDirectories(destinationPath);
            }
            Path destinationFile = Paths.get(destination, config.getConfigFileName());
            Files.write(destinationFile, getLines(config));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getLines(Config config) {
        return config.getAllElements().stream()
                .map(Object::toString)
                .map(s -> s + System.lineSeparator())
                .collect(Collectors.toList());
    }
}
