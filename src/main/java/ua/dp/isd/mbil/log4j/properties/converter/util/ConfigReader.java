package ua.dp.isd.mbil.log4j.properties.converter.util;

import ua.dp.isd.mbil.log4j.properties.converter.model.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigReader {
    public <T extends Config> List<T> read(String path, Class<T> configType) {
        Stream<Path> pathStream = null;
        try {
            T instance = configType.newInstance();
            BiPredicate<Path, BasicFileAttributes> predicate = getFilenameFilter(instance.getConfigFileName());
            pathStream = Files.find(Paths.get(path), 1, predicate);
            List<Path> configFiles = pathStream.collect(Collectors.toList());
            return configFiles.stream().map(f -> parse(f, configType)).collect(Collectors.toList());
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (pathStream != null) {
                pathStream.close();
            }
        }
    }

    private BiPredicate<Path, BasicFileAttributes> getFilenameFilter(String configFileName) {
        return (p, ats) -> p.getFileName().toString().equals(configFileName);
    }

    private <T extends Config> T parse(Path path, Class<T> configType) {
        try {
            T parsed = configType.newInstance();
            for (String line : Files.readAllLines(path)) {
                parseLine(line, parsed);
            }
            return parsed;
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T extends Config> void parseLine(String line, T parsed) {
        parsed.getLineTranslator().translate(line);
    }
}
