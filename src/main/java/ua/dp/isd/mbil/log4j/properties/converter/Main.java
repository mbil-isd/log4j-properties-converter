package ua.dp.isd.mbil.log4j.properties.converter;

import ua.dp.isd.mbil.log4j.properties.converter.model.Log4jProperties;
import ua.dp.isd.mbil.log4j.properties.converter.model.Log4jXmlConfig;

public class Main {

    public static void main(String[] args) {
//        String path = "C:\\workspace\\esb-log4j2-patch";
//        boolean deleteAfterReading = true;
        String path = ".";
        boolean deleteAfterReading = false;
        System.setProperty("log4jDir", "target");
        new Log4jConfigToLog4j2Converter(Log4jProperties.class, Log4jXmlConfig.class).run(path, deleteAfterReading);
    }
}