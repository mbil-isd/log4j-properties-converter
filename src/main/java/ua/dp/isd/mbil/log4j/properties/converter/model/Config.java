package ua.dp.isd.mbil.log4j.properties.converter.model;

import java.util.Collection;

public abstract class Config {

    public static final String TEST =  "test";

    public abstract String getConfigFileName();

    public abstract void addLine(String line);

    public abstract ConfigLineTranslator getLineTranslator();

    public abstract Collection<ConfigElement> getAllElements();
}
