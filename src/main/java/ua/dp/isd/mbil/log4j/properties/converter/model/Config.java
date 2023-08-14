package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.util.Collection;
import java.util.List;

public abstract class Config {

    public static final String TEST =  "test";
    private RootLogger rootLogger;
    private List<Logger> loggers;
    private List<Appender> appenders;

    public abstract String getConfigFileName();

    public abstract void addLine(String line);

    public abstract ConfigTranslator getLineTranslator();

    public abstract Collection<ConfigElement> getAllElements();

    public void setRootLogger(RootLogger rootLogger) {
        this.rootLogger = rootLogger;
    }

    public RootLogger getRootLogger() {
        return rootLogger;
    }

    public void setLoggers(List<Logger> loggers) {
        this.loggers = loggers;
    }

    public List<Logger> getLoggers() {
        return loggers;
    }

    public void setAppenders(List<Appender> appenders) {
        this.appenders = appenders;
    }

    public List<Appender> getAppenders() {
        return appenders;
    }
}
