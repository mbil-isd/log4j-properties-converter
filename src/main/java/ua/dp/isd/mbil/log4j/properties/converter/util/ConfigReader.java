package ua.dp.isd.mbil.log4j.properties.converter.util;

import ua.dp.isd.mbil.log4j.properties.converter.model.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigReader {
    public <T extends Config> List<T> read(String path, Class<T> configType, boolean deleteAfterReading) {
        Stream<Path> pathStream = null;
        try {
            T instance = configType.newInstance();
            BiPredicate<Path, BasicFileAttributes> predicate = getFilenameFilter(instance.getConfigFileName());
            pathStream = Files.find(Paths.get(path), Integer.MAX_VALUE, predicate);
            List<Path> configFiles = pathStream.collect(Collectors.toList());
            return configFiles.stream()
                    .map(f -> parse(f, configType, deleteAfterReading))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
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

    private <T extends Config> T parse(Path path, Class<T> configType, boolean deleteAfterReading) {
        try {
            T parsed = configType.newInstance();
            parsed.getConfigTranslator().translate(Files.newInputStream(path));
            parsed.setPath(path.getParent());
            System.out.println("file: " + path + " successfully converted!");
            if (deleteAfterReading) {
                Files.delete(path);
                System.out.println("original file: " + path + " was deleted!");
            }
            return parsed;
        } catch (Exception e) {
            System.err.println("file: " + path + " was not translated because of error: " + e);
        }
        return null;
    }
}
