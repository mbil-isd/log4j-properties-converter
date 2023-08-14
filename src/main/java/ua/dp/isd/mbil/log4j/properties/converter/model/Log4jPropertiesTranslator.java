package ua.dp.isd.mbil.log4j.properties.converter.model;

import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.util.*;
import java.util.stream.Collectors;

public class Log4jPropertiesTranslator extends ConfigTranslator {

    private static final String ROOT_LOGGER_KEY = "log4j.rootLogger";
    private static final String APPENDER_PREFIX = "log4j.appender";
    private static final String CONSOLE_APPENDER_TYPE = "ConsoleAppender";
    private static final String FILE_APPENDER_TYPE = "FileAppender";
    private static final String APPENDER_LAYOUT_KEY = "layout";
    private static final String PATTERN_LAYOUT = "PatternLayout";
    private static final String XML_LAYOUT = "SmxXMLLayout";
    private static final String APPENDER_PATTERN_KEY = "ConversionPattern";
    private static final String LOGGER_PREFIX = "log4j.logger";
    private final Log4jProperties log4jProperties;

    public Log4jPropertiesTranslator(Log4jProperties log4jProperties) {
        this.log4jProperties = log4jProperties;
    }

    @Override
    public void translate(Properties properties) {
        log4jProperties.setRootLogger(getRootLogger(properties));
        log4jProperties.setLoggers(getLoggers(properties));
        log4jProperties.setAppenders(getAppenders(properties));
    }

    private List<Appender> getAppenders(Properties properties) {
        Map<String, Appender> appenderMap = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith(APPENDER_PREFIX)) {
                String appenderName = key.split("\\.")[2];
                if (!appenderMap.containsKey(appenderName)) {
                    Appender appender = new Appender();
                    appender.setName(appenderName);
                    appender.setType(getAppenderType(properties.getProperty(APPENDER_PREFIX + "." + appenderName)));
                    appender.setLayout(getAppenderLayout(properties, appenderName));
                    appenderMap.put(appenderName, appender);
                }
            }
        }
        return new ArrayList<>(appenderMap.values());
    }

    private String buildPropertyName(String... parts) {
        return String.join(".", parts);
    }

    private Layout getAppenderLayout(Properties properties, String appenderName) {
        Layout layout = new Layout();
        String layoutTypeValue = properties.getProperty(buildPropertyName(APPENDER_PREFIX, appenderName, APPENDER_LAYOUT_KEY));
        String layoutType = null;
        if (layoutTypeValue.contains(PATTERN_LAYOUT)) {
            layoutType = "PatternLayout";
            layout.setHasPattern(true);
            String patternKey = buildPropertyName(APPENDER_PREFIX, appenderName, APPENDER_LAYOUT_KEY, APPENDER_PATTERN_KEY);
            layout.setPattern(properties.getProperty(patternKey));
        } else if (layoutTypeValue.contains(XML_LAYOUT)) {
            layoutType = "XMLLayoutl4j2_1";
        }
        layout.setType(layoutType);
        return layout;
    }

    private String getAppenderType(String property) {
        if (property.contains(CONSOLE_APPENDER_TYPE)) {
            return "Console";
        }
        if (property.contains(FILE_APPENDER_TYPE)) {
            return "File";
        }
        return null;
    }

    private List<Logger> getLoggers(Properties properties) {
        List<Logger> loggers = new ArrayList<>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith(LOGGER_PREFIX)) {
                String loggerName = key.replace(LOGGER_PREFIX, "").substring(1);
                String loggerKey = loggerName.replaceAll("\\.", "");
                Logger logger = new Logger();
                logger.setKey(loggerKey);
                logger.setName(loggerName);
                logger.setLevel(properties.getProperty(key).trim());
                loggers.add(logger);
            }
        }
        return loggers;
    }

    private RootLogger getRootLogger(Properties properties) {
        String value = properties.getProperty(ROOT_LOGGER_KEY);
        if (value == null) {
            System.out.println("no root logger found!");
            return null;
        }

        RootLogger rootLogger = new RootLogger();
        String[] pieces = value.split(",");
        String level = pieces[0].trim();
        rootLogger.setLevel(level);
        String[] appenderRefsArr = Arrays.copyOfRange(pieces, 1, pieces.length);
        List<String> appenderRefs = Arrays.stream(appenderRefsArr)
                .map(String::trim).collect(Collectors.toList());
        rootLogger.setAppenderRefs(appenderRefs);
        return rootLogger;
    }
}