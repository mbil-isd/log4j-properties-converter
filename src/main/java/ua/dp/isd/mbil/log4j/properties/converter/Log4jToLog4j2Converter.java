package ua.dp.isd.mbil.log4j.properties.converter;

import ua.dp.isd.mbil.log4j.properties.converter.model.*;

public class Log4jToLog4j2Converter {
    public Log4j2Config convert(Log4jConfig log4jConfig) {
        Log4j2Config log4J2Config;
        Config.ConfigType configType = log4jConfig.getConfigType();
        if (configType.equals(Config.ConfigType.XML)) {
            log4J2Config = new Log4j2XmlConfig();
        } else if (configType.equals(Config.ConfigType.PROPERTIES)) {
            log4J2Config = new Log4j2Properties();
        } else {
            throw new IllegalArgumentException("config type is not supported: " + configType);
        }
        log4J2Config.setRootLogger(log4jConfig.getRootLogger());
        log4J2Config.setAppenders(log4jConfig.getAppenders());
        log4J2Config.setLoggers(log4jConfig.getLoggers());
        log4J2Config.setPath(log4jConfig.getPath());
        return log4J2Config;
    }
}
