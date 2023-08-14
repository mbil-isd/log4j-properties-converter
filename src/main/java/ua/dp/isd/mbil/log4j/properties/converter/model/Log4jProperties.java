package ua.dp.isd.mbil.log4j.properties.converter.model;

import java.util.Collection;

public class Log4jProperties extends Config
{
    private static final String CONFIG_FILENAME = "log4j.properties";

    @Override
    public String getConfigFileName() {
        return CONFIG_FILENAME;
    }

    @Override
    public void addLine(String line) {

    }

    @Override
    public ConfigLineTranslator getLineTranslator() {
        return null;
    }

    @Override
    public Collection<ConfigElement> getAllElements() {
        return null;
    }
}
