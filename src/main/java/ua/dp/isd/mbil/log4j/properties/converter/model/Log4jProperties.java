package ua.dp.isd.mbil.log4j.properties.converter.model;

public class Log4jProperties extends Config
{
    private static final String CONFIG_FILENAME = "log4j.properties";
    @Override
    public String getConfigFileName() {
        return CONFIG_FILENAME;
    }
    @Override
    public ConfigTranslator getPropertiesTranslator() {
        return new Log4jPropertiesTranslator(this);
    }
}
