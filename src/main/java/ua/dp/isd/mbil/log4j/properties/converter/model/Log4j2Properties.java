package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Log4j2Properties extends Config {
    private static final String CONFIG_FILENAME = "log4j2.properties";
    private RootLogger rootLogger;
    private List<Logger> loggers = new ArrayList<>();
    private List<Appender> appenders =  new ArrayList<>();

    @Override
    public String getConfigFileName() {
        return CONFIG_FILENAME;
    }

    @Override
    public void addLine(String line) {

    }

    @Override
    public ConfigLineTranslator getLineTranslator() {
        return new Log4j2PropertiesLineTranslator(this);
    }

    @Override
    public Collection<ConfigElement> getAllElements() {
        return null;
    }

    public void setRootLogger(RootLogger rootLogger) {
        this.rootLogger = rootLogger;
    }

    public void addLogger(Logger logger) {
        this.loggers.add(logger);
    }

    public void addAppender(Appender appender) {
        this.appenders.add(appender);
    }
}
