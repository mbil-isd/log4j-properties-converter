package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

public class Log4j2PropertiesLineTranslator extends ConfigLineTranslator {
    private final Log4j2Properties properties;

    public Log4j2PropertiesLineTranslator(Log4j2Properties log4j2Properties) {
        this.properties = log4j2Properties;
    }

    @Override
    public void translate(String line) {
        if (isComment(line)) {
            return;
        }

        if (isRootLogger(line)) {
            properties.setRootLogger(translateRootLogger(line));
        }

        if (isLogger(line)) {
            properties.addLogger(translateLogger(line));
        }

        if (isAppender(line)) {
            properties.addAppender(translateAppender(line));
        }
    }

    private boolean isAppender(String line) {
        return line.contains(".appender.");

    }

    private Appender translateAppender(String line) {
        Appender appender = new Appender();
        appender.setName(line.split("\\.")[2]);
        return appender;
    }

    private Logger translateLogger(String line) {
        return null;
    }

    private boolean isLogger(String line) {
        return line.contains(".logger.");
    }

    private boolean isRootLogger(String line) {
        return line.contains("rootLogger");
    }

    private RootLogger translateRootLogger(String line) {
        return null;
    }

    private boolean isComment(String line) {
        return line.startsWith("#");
    }
}
