package ua.dp.isd.mbil.log4j.properties.converter.model;

public class Log4j2Properties extends Config {
    private static final String CONFIG_FILENAME = "log4j2.properties";
    @Override
    public String getConfigFileName() {
        return CONFIG_FILENAME;
    }

    @Override
    public ConfigTranslator getPropertiesTranslator() {
        return new Log4j2PropertiesTranslator(this);
    }

}
