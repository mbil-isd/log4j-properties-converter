package ua.dp.isd.mbil.log4j.properties.converter.util;

import ua.dp.isd.mbil.log4j.properties.converter.model.Config;
import ua.dp.isd.mbil.log4j.properties.converter.model.ConfigElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigWriter {
    public void write(Config config, String destination) {
        Path destinationFile = Paths.get(destination, config.getConfigFileName());
        try {
            Files.write(destinationFile, getLines(config));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getLines(Config config) {
        return config.getAllElements().stream().map(ConfigElement::toString).collect(Collectors.toList());
    }
}
