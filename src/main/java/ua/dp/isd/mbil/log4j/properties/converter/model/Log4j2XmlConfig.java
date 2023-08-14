package ua.dp.isd.mbil.log4j.properties.converter.model;

public class Log4j2XmlConfig extends Log4j2Config {
    @Override
    public String getConfigFileName() {
        return "log4j2.xml";
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
