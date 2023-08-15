package ua.dp.isd.mbil.log4j.properties.converter.model;

public class Log4jProperties extends Log4jConfig
{
    private static final String CONFIG_FILENAME = "log4j.properties";
    @Override
    public String getConfigFileName() {
        return CONFIG_FILENAME;
    }
    @Override
    public ConfigTranslator getConfigTranslator() {
        return new Log4jPropertiesTranslator(this);
    }

    @Override
    public Iterable<? extends CharSequence> getStringRepresentation() {
        return null;
    }

    @Override
    public ConfigType getConfigType() {
        return ConfigType.PROPERTIES;
    }
}
