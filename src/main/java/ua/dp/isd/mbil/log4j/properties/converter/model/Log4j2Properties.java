package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Layout;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Log4j2Properties extends Log4j2Config {
    private static final String CONFIG_FILENAME = "log4j2.properties";

    @Override
    public String getConfigFileName() {
        return CONFIG_FILENAME;
    }

    @Override
    public ConfigTranslator<Properties> getConfigTranslator() {
        return new Log4j2PropertiesTranslator(this);
    }

    @Override
    public Iterable<? extends CharSequence> getStringRepresentation() {
        List<String> lines = getLoggers().stream()
                .map(this::stringifyLogger)
                .collect(Collectors.toList());
        Collection<String> appenderLines = getAppenders().stream()
                .map(this::stringifyAppender)
                .collect(Collectors.toList());
        lines.addAll(appenderLines);
        lines.add(stringifyRootLogger(getRootLogger()));
        lines = lines.stream().map(s -> s + System.lineSeparator()).collect(Collectors.toList());
        return lines;
    }

    private String stringifyRootLogger(RootLogger rootLogger) {
        return "rootLogger = " + String.join(", ", rootLogger.getLevel(), String.join(", ", rootLogger.getAppenderRefs()));
    }

    private String stringifyAppender(Appender appender) {
        String name = appender.getName();
        String result = "appender." + name + ".name = " + name +
                System.lineSeparator() +
                "appender." + name + ".type = " + appender.getType();
        Layout layout = appender.getLayout();
        if (layout != null) {
            result += System.lineSeparator() +
                    "appender." + name + ".layout = " + layout.getType();
            if (layout.hasPattern()) {
                result += System.lineSeparator() +
                        "appender." + name + ".layout.pattern = " + layout.getPattern();
            }
        }
        String fileName = appender.getFileName();
        if (fileName != null) {
            result += System.lineSeparator() +
                    "appender." + name + ".fileName = " + fileName;
        }
        return result;
    }

    private String stringifyLogger(Logger logger) {
        return "logger." + logger.getKey() + ".name = " + logger.getName() +
                System.lineSeparator() +
                "logger." + logger.getKey() + ".level = " + logger.getKey();
    }

    @Override
    public ConfigType getConfigType() {
        return ConfigType.PROPERTIES;
    }

}
