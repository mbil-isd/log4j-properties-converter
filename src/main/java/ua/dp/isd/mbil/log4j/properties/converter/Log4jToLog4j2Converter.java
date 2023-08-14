package ua.dp.isd.mbil.log4j.properties.converter;

import ua.dp.isd.mbil.log4j.properties.converter.model.Log4j2Properties;
import ua.dp.isd.mbil.log4j.properties.converter.model.Log4jProperties;

public class Log4jToLog4j2Converter {
    public Log4j2Properties convert(Log4jProperties log4JProperties) {
        Log4j2Properties log4J2Properties = new Log4j2Properties();
        log4J2Properties.setRootLogger(log4JProperties.getRootLogger());
        log4J2Properties.setAppenders(log4JProperties.getAppenders());
        log4J2Properties.setLoggers(log4JProperties.getLoggers());
        log4J2Properties.setPath(log4JProperties.getPath());
        return log4J2Properties;
    }
}
