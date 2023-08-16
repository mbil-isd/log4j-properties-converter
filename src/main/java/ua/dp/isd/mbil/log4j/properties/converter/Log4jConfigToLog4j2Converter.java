package ua.dp.isd.mbil.log4j.properties.converter;

import ua.dp.isd.mbil.log4j.properties.converter.model.Log4jConfig;
import ua.dp.isd.mbil.log4j.properties.converter.util.ConfigReader;
import ua.dp.isd.mbil.log4j.properties.converter.util.ConfigWriter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Log4jConfigToLog4j2Converter {
    private final ConfigReader reader;
    private final ConfigWriter writer;
    private final Log4jToLog4j2Converter log4jToLog4j2Converter;

    private final List<Class<? extends Log4jConfig>> toBeConverted;

    public Log4jConfigToLog4j2Converter(Class<? extends Log4jConfig>... toBeConverted) {
        this.reader = new ConfigReader();
        this.writer = new ConfigWriter();
        this.log4jToLog4j2Converter = new Log4jToLog4j2Converter();
        this.toBeConverted = Arrays.asList(toBeConverted);
    }

    public void run(String path, boolean deleteAfterReading) {
        toBeConverted.stream()
                .map(t -> reader.read(path, t, deleteAfterReading))
                .filter(l -> !l.isEmpty())
                .peek(l -> System.out.println("found: " + l.size() + " files of type: " + l.stream().findFirst().get().getClass().getSimpleName()))
                .flatMap(Collection::stream)
                .map(log4jToLog4j2Converter::convert)
                .forEach(writer::write);
    }
}
