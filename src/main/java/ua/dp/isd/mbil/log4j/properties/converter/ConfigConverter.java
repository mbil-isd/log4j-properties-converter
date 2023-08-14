package ua.dp.isd.mbil.log4j.properties.converter;

import ua.dp.isd.mbil.log4j.properties.converter.model.Log4j2Properties;
import ua.dp.isd.mbil.log4j.properties.converter.model.Log4jProperties;
import ua.dp.isd.mbil.log4j.properties.converter.util.ConfigReader;
import ua.dp.isd.mbil.log4j.properties.converter.util.ConfigWriter;

import java.util.List;
import java.util.stream.Collectors;

public class ConfigConverter {
    private final ConfigReader reader;
    private final ConfigWriter writer;
    private Log4jToLog4j2Converter log4jToLog4j2Converter;

    public ConfigConverter() {
        this.reader = new ConfigReader();
        this.writer = new ConfigWriter();
        this.log4jToLog4j2Converter = new Log4jToLog4j2Converter();

    }

    public void run(String path, boolean deleteAfterReading) {
        List<Log4jProperties> configs = reader.read(path, Log4jProperties.class, deleteAfterReading);
        System.out.println("found: " + configs.size() + " config files to convert");
        List<Log4j2Properties> converted = configs.stream().map(this::convertToLog4j2).collect(Collectors.toList());
        System.out.println("converted: " + converted.size() + " config files");
        converted.forEach(writer::write);
        System.out.println("files were written!");
    }

    private Log4j2Properties convertToLog4j2(Log4jProperties log4JProperties) {
        return log4jToLog4j2Converter.convert(log4JProperties);
    }
}
