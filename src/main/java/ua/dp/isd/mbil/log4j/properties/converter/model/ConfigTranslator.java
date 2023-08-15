package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class ConfigTranslator<T> {

    private final Config config;
    private T configHolder;

    protected ConfigTranslator(Config config) {
        this.config = config;
    }

    public final void translate(InputStream inputStream) throws IOException {
        initConfigHolder(inputStream);
        config.setAppenders(getAppenders(configHolder));
        config.setLoggers(getLoggers(configHolder));
        config.setRootLogger(getRootLogger(configHolder));
    }

    private void initConfigHolder(InputStream inputStream) throws IOException {
        configHolder = createConfigHolder(inputStream);
    }

    protected abstract T createConfigHolder(InputStream inputStream) throws IOException;

    protected abstract RootLogger getRootLogger(T configHolder);

    protected abstract List<Logger> getLoggers(T configHolder);

    protected abstract List<Appender> getAppenders(T configHolder);
}

