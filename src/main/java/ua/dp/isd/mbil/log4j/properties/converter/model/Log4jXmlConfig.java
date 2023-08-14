package ua.dp.isd.mbil.log4j.properties.converter.model;

public class Log4jXmlConfig extends Log4jConfig {
    @Override
    public String getConfigFileName() {
        return "log4j.xml";
    }

    @Override
    public ConfigTranslator getPropertiesTranslator() {
        return null;
    }

    @Override
    public ConfigType getConfigType() {
        return ConfigType.XML;
    }
}
