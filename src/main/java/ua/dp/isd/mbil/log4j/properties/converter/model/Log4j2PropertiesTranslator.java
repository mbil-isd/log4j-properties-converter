package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.util.Properties;

public class Log4j2PropertiesTranslator extends ConfigTranslator {
    private final Log4j2Properties log4j2Properties;

    public Log4j2PropertiesTranslator(Log4j2Properties log4j2Properties) {
        this.log4j2Properties = log4j2Properties;
    }

    @Override
    public void translate(Properties properties) {

    }

    private RootLogger getRootLogger(Properties properties) {
        return null;
    }

    private void processUnknownLine(String line) {
        
    }

    private void processAppenderLine(String line) {
    }

    private void processLoggerLine(String line) {
        
    }

    private void processRootLoggerLine(String line) {
        RootLogger rootLogger = new RootLogger();
        log4j2Properties.setRootLogger(rootLogger);
    }

    private boolean isAppender(String line) {
        return line.contains(".appender.");

    }

    private boolean isLogger(String line) {
        return line.contains(".logger.");
    }

    private boolean isRootLogger(String line) {
        return line.contains("rootLogger");
    }
}
