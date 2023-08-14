package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Config {
    private RootLogger rootLogger;
    private List<Logger> loggers;
    private List<Appender> appenders;
    private Path path;

    public enum ConfigType {
        XML, PROPERTIES
    }

    public abstract String getConfigFileName();

    public abstract ConfigTranslator getPropertiesTranslator();

    public Collection<Object> getAllElements() {
        return Stream.of(loggers, appenders, Collections.singleton(rootLogger))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

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
