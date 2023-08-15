package ua.dp.isd.mbil.log4j.properties.converter.model;


import com.scc.log4j.CompactMessagePatternLayout;
import org.apache.log4j.bridge.LayoutAdapter;
import org.apache.log4j.layout.Log4j1XmlLayout;
import org.apache.log4j.xml.XmlConfigurationFactory;
import org.apache.logging.log4j.core.appender.AsyncAppender;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.*;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.layout.XmlLayout;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Layout;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.RootLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Log4jXmlConfigTranslator extends ConfigTranslator<Configuration> {

    public Log4jXmlConfigTranslator(Log4jXmlConfig log4jXmlConfig) {
        super(log4jXmlConfig);
    }

    @Override
    protected Configuration createConfigHolder(InputStream inputStream) throws IOException {
        ConfigurationSource source = new ConfigurationSource(inputStream);
        Configuration configuration = new XmlConfigurationFactory().getConfiguration(null, source);
        configuration.start();
        return configuration;
    }

    @Override
    protected RootLogger getRootLogger(Configuration configHolder) {
        RootLogger rootLogger = new RootLogger();
        rootLogger.setLevel(configHolder.getRootLogger().getLevel().toString());
        Set<String> appenderRefs = configHolder.getRootLogger().getAppenders().keySet();
        rootLogger.setAppenderRefs(new ArrayList<>(appenderRefs));
        return rootLogger;
    }

    @Override
    protected List<Logger> getLoggers(Configuration configHolder) {
        List<Logger> loggers = new ArrayList<>();
        configHolder.getLoggers().values().stream()
                .map(this::translateLogger)
                .forEach(loggers::add);
        return loggers;
    }

    private Logger translateLogger(LoggerConfig loggerConfig) {
        Logger logger = new Logger();
        logger.setName(loggerConfig.getName());
        logger.setAppenderRefs(loggerConfig.getAppenders().keySet());
        logger.setLevel(loggerConfig.getLevel().toString());
        return logger;
    }

    @Override
    protected List<Appender> getAppenders(Configuration configuration) {
        return configuration.getAppenders().values().stream().map(this::translateAppender).collect(Collectors.toList());
    }

    private Appender translateAppender(org.apache.logging.log4j.core.Appender appender) {
        Appender translated = new Appender();
        String translatedType;
        String fileName = null;
        if (appender instanceof ConsoleAppender) {
            translatedType = ConsoleAppender.PLUGIN_NAME;
        } else if (appender instanceof RollingFileAppender) {
            translatedType = "DailySizeRollingFileAppender";
            RollingFileAppender other = (RollingFileAppender) appender;
            fileName = other.getFileName();
            translated.setMaxBackupIndex(getMaxBackupIndex(other));
            translated.setMaxFileSize(getMaxFileSize(other));
        } else if (appender instanceof FileAppender) {
            translatedType = FileAppender.PLUGIN_NAME;
            FileAppender other = (FileAppender) appender;
            fileName = other.getFileName();
        } else if (appender instanceof AsyncAppender) {
            translatedType = "Async";
            AsyncAppender other = (AsyncAppender) appender;
            translated.setAppenderRefs(Arrays.asList(other.getAppenderRefStrings()));
        } else {
            throw new IllegalArgumentException("unsupported appender: " + appender);
        }
        translated.setType(translatedType);
        translated.setFileName(fileName);
        translated.setName(appender.getName());
        org.apache.logging.log4j.core.Layout<? extends Serializable> layout = appender.getLayout();
        if (layout != null) {
            translated.setLayout(translateLayout(layout));
        }

        return translated;
    }

    private String getMaxFileSize(RollingFileAppender other) {
        TriggeringPolicy policy = other.getTriggeringPolicy();
        SizeBasedTriggeringPolicy policy1;
        if (policy instanceof SizeBasedTriggeringPolicy) {
            policy1 = (SizeBasedTriggeringPolicy) policy;
        } else if (policy instanceof CompositeTriggeringPolicy) {
            TriggeringPolicy triggeringPolicy = ((CompositeTriggeringPolicy) policy).getTriggeringPolicies()[0];
            if (triggeringPolicy instanceof SizeBasedTriggeringPolicy) {
                policy1 = (SizeBasedTriggeringPolicy) triggeringPolicy;
            } else {
                throw new RuntimeException("unknown policy among composite triggering policy: " + triggeringPolicy);
            }
        } else {
            throw new RuntimeException("unknown triggering policy");
        }
        long mbSize = policy1.getMaxFileSize() / 1000000;
        if (mbSize > 0) {
            return mbSize + "MB";
        } else {
            return policy1.getMaxFileSize() + "B";
        }
    }

    private Integer getMaxBackupIndex(RollingFileAppender other) {
        RolloverStrategy rolloverStrategy = other.getManager().getRolloverStrategy();
        return extractMaxBackupIndex(rolloverStrategy);
    }

    private Integer extractMaxBackupIndex(RolloverStrategy rolloverStrategy) {
        if (rolloverStrategy instanceof DefaultRolloverStrategy) {
            return ((DefaultRolloverStrategy) rolloverStrategy).getMaxIndex();
        }
        if (rolloverStrategy instanceof DirectWriteRolloverStrategy) {
            return ((DirectWriteRolloverStrategy) rolloverStrategy).getMaxFiles();
        }
        throw new IllegalArgumentException("unsupported rollover strategy: " + rolloverStrategy);
    }

    private Layout translateLayout(org.apache.logging.log4j.core.Layout<? extends Serializable> layout) {
        Layout translated = new Layout();
        String translatedType;
        if (layout instanceof PatternLayout) {
            translatedType = "PatternLayout";
            PatternLayout other = (PatternLayout) layout;
            translated.setPattern(other.getConversionPattern());
        } else if (layout instanceof XmlLayout) {
            translatedType = "XMLLayoutl4j2_1";
        } else if (layout instanceof Log4j1XmlLayout) {
            translatedType = "XMLLayoutl4j2_1";
        } else if (layout instanceof LayoutAdapter) {
            org.apache.log4j.Layout other = ((LayoutAdapter) layout).getLayout();
            if (other instanceof CompactMessagePatternLayout) {
                translatedType = "CompactMessagePatternLayout";
                CompactMessagePatternLayout other2 = (CompactMessagePatternLayout) other;
                translated.setPattern(other2.getConversionPattern());
                translated.setMsgSize(other2.getMsgSize());
            } else {
                throw new RuntimeException("unknown adapted layout: " + other);
            }
        } else {
            throw new IllegalArgumentException("unsupported layout: " + layout);
        }
        translated.setType(translatedType);
        return translated;
    }
}
