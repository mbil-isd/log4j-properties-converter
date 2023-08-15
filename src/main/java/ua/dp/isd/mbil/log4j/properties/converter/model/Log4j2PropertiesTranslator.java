package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Log4j2PropertiesTranslator extends ConfigTranslator<Properties> {

    public Log4j2PropertiesTranslator(Log4j2Properties log4j2Properties) {
        super(log4j2Properties);
    }

    @Override
    protected Properties createConfigHolder(InputStream inputStream) throws IOException {
        return null;
    }

    protected RootLogger getRootLogger(Properties properties) {
        return null;
    }

    @Override
    protected List<Logger> getLoggers(Properties configHolder) {
        return null;
    }

    @Override
    protected List<Appender> getAppenders(Properties configHolder) {
        return null;
    }

    private void processUnknownLine(String line) {

    }

    private void processAppenderLine(String line) {
    }

    private void processLoggerLine(String line) {

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
