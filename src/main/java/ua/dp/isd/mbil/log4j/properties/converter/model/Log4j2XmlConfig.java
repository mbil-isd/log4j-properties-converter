package ua.dp.isd.mbil.log4j.properties.converter.model;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Appender;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Layout;
import ua.dp.isd.mbil.log4j.properties.converter.model.elements.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        lines.addAll(stringifyLoggers());
        lines.add("</Configuration>");
        return prettify(lines);
    }

    private List<String> prettify(List<String> lines) {
        String xmlString = lines.stream().reduce(String::concat).orElseThrow(() -> new RuntimeException("no lines found"));
        try {
            InputSource src = new InputSource(new StringReader(xmlString));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            Writer out = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(out));
            return Collections.singletonList(out.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error occurs when pretty-printing xml:\n" + xmlString, e);
        }
    }

    private Collection<String> stringifyLoggers() {
        List<String> lines = new ArrayList<>();
        lines.add("<Loggers>");
        lines.addAll(getLoggers().stream().map(this::stringifyLogger).collect(Collectors.toList()));
        lines.add(stringifyRootLogger());
        lines.add("</Loggers>");
        return lines;
    }

    private String stringifyRootLogger() {
        String result = "<Root level=\"" + getRootLogger().getLevel() + "\">";
        result += stringifyAppenderRefs(getRootLogger().getAppenderRefs());
        result += "</Root>";
        return result;
    }

    private String stringifyLogger(Logger logger) {
        String result = "<Logger name=\"" + logger.getName() + "\" level=\"" + logger.getLevel() + "\">";
        result += stringifyAppenderRefs(logger.getAppenderRefs());
        result += "</Logger>";
        return result;
    }

    private String stringifyAppenderRefs(Collection<String> appenderRefs) {
        return appenderRefs.stream()
                .map(this::stringifyAppenderRef)
                .reduce(String::concat).orElse("");
    }

    private List<String> stringifyAppenders() {
        List<String> lines = new ArrayList<>();
        lines.add("<Appenders>");
        lines.addAll(getAppenders().stream().map(this::stringifyAppender).collect(Collectors.toList()));
        lines.add("</Appenders>");
        return lines;
    }

    private String stringifyAppender(Appender appender) {
        String result = "<" + appender.getType() + " name=\"" + appender.getName() + "\"";
        String fileName = appender.getFileName();
        if (fileName != null) {
            result += " fileName=\"" + fileName + "\"";
        }
        String maxFileSize = appender.getMaxFileSize();
        if (maxFileSize != null) {
            result += " maxFileSize=\"" + maxFileSize + "\"";
        }
        Integer maxBackupIndex = appender.getMaxBackupIndex();
        if (maxBackupIndex != null) {
            result += " maxBackupIndex=\"" + maxBackupIndex + "\"";
        }
        Integer maxFilesNumber = appender.getMaxFilesNumber();
        if (maxFilesNumber != null) {
            result += " maxFilesNumber=\"" + maxFilesNumber + "\"";
        }
        result += ">";
        Layout layout = appender.getLayout();
        if (layout != null) {
            result += "<" + layout.getType();
            if (layout.hasPattern()) {
                result += " pattern=\"" + layout.getPattern() + "\"";
            } else {
                result += " properties=\"true\" checkBadXmlSymbols=\"false\"";
            }
            if (layout.getMsgSize() != null) {
                result += " msgSize=\"" + layout.getMsgSize() + "\"";
            }
            result += "/>";
        }
        result += appender.getAppenderRefs().stream().map(this::stringifyAppenderRef).reduce(String::concat).orElse("");
        result += "</" + appender.getType() + ">";
        return result;
    }

    private String stringifyAppenderRef(String appenderRef) {
        return "<AppenderRef ref=\"" + appenderRef + "\"/>";
    }

    @Override
    public ConfigType getConfigType() {
        return ConfigType.XML;
    }
}
