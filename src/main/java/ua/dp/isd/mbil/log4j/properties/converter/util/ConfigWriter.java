package ua.dp.isd.mbil.log4j.properties.converter.util;

import ua.dp.isd.mbil.log4j.properties.converter.model.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigWriter {
    public void write(Config config) {
        Path destinationPath = config.getPath();
        try {
            if (!Files.exists(destinationPath)) {
                Files.createDirectories(destinationPath);
            }
            Path destinationFile = Paths.get(destinationPath.toString(), config.getConfigFileName());
            Files.write(destinationFile, config.getStringRepresentation());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
