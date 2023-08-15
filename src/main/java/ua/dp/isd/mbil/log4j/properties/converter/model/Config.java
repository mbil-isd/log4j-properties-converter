package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.nio.file.Path;
import java.util.List;

public abstract class Config {
    private RootLogger rootLogger;
    private List<Logger> loggers;
    private List<Appender> appenders;
    private Path path;

    public enum ConfigType {
        XML, PROPERTIES;
    }
    public abstract String getConfigFileName();

    public abstract ConfigTranslator<?> getConfigTranslator();

    public abstract Iterable<? extends CharSequence> getStringRepresentation();

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

    public void setPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public abstract ConfigType getConfigType();
}
