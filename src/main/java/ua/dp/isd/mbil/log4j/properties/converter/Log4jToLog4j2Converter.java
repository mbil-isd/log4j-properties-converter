package ua.dp.isd.mbil.log4j.properties.converter;

import ua.dp.isd.mbil.log4j.properties.converter.model.Config;
import ua.dp.isd.mbil.log4j.properties.converter.model.Log4j2Config;
import ua.dp.isd.mbil.log4j.properties.converter.model.Log4j2Properties;
import ua.dp.isd.mbil.log4j.properties.converter.model.Log4j2XmlConfig;

public class Log4jToLog4j2Converter {
    public Log4j2Config convert(Config config) {
        Log4j2Config log4J2Config;
        Config.ConfigType configType = config.getConfigType();
        if (configType.equals(Config.ConfigType.XML)) {
            log4J2Config = new Log4j2XmlConfig();
        } else if (configType.equals(Config.ConfigType.PROPERTIES)) {
            log4J2Config = new Log4j2Properties();
        } else {
            throw new IllegalArgumentException("config type is not supported: " + configType);
        }
        log4J2Config.setRootLogger(config.getRootLogger());
        log4J2Config.setAppenders(config.getAppenders());
        log4J2Config.setLoggers(config.getLoggers());
        log4J2Config.setPath(config.getPath());
        return log4J2Config;
    }
}
