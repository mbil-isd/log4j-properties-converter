package ua.dp.isd.mbil.log4j.properties.converter;

import org.apache.log4j.helpers.LogLog;
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

    public void run() {
        LogLog.setInternalDebugging(true);
        List<Log4jProperties> configs = reader.read(".", Log4jProperties.class);
        List<Log4j2Properties> converted = configs.stream().map(this::convertToLog4j2).collect(Collectors.toList());
        converted.forEach(c -> writer.write(c, "./out"));
    }

    private Log4j2Properties convertToLog4j2(Log4jProperties log4JProperties) {
        return log4jToLog4j2Converter.convert(log4JProperties);
    }
}
