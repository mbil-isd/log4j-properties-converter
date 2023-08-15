package ua.dp.isd.mbil.log4j.properties.converter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Log4j2XmlConfig extends Log4j2Config {
    @Override
    public String getConfigFileName() {
        return "log4j2.xml";
    }

    @Override
    public ConfigTranslator getConfigTranslator() {
        return null;
    }

    @Override
    public Iterable<? extends CharSequence> getStringRepresentation() {
        List<String> lines = new ArrayList<>();
        lines.add("<Configuration>");
        lines.addAll(stringifyAppenders());
        lines.add(System.lineSeparator());
        lines.addAll(stringifyLoggers());
        lines.add("</Configuration>");
        return lines;
    }

    private Collection<String> stringifyLoggers() {
        return null;
    }

    private List<String> stringifyAppenders() {
        return null;
    }

    @Override
    public ConfigType getConfigType() {
        return ConfigType.XML;
    }
}
