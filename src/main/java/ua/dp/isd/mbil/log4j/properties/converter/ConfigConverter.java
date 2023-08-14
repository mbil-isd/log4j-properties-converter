package ua.dp.isd.mbil.log4j.properties.converter;

import ua.dp.isd.mbil.log4j.properties.converter.model.Log4j2Config;
import ua.dp.isd.mbil.log4j.properties.converter.model.Log4jConfig;
import ua.dp.isd.mbil.log4j.properties.converter.util.ConfigReader;
import ua.dp.isd.mbil.log4j.properties.converter.util.ConfigWriter;

import java.util.List;
import java.util.stream.Collectors;

public class ConfigConverter {
    private final ConfigReader reader;
    private final ConfigWriter writer;

    public ConfigConverter() {
        this.reader = new ConfigReader();
        this.writer = new ConfigWriter();
    }

    public void run() {
        List<Log4jConfig> configs = reader.read(".", Log4jConfig.class);
        List<Log4j2Config> converted = configs.stream().map(this::convertToLog4j2).collect(Collectors.toList());
        converted.forEach(writer::write);
    }

    private Log4j2Config convertToLog4j2(Log4jConfig log4jConfig) {
        return null;
    }
}
