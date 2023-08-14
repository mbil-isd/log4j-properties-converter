package ua.dp.isd.mbil.log4j.properties.converter;

public class Main {

    public static void main(String[] args) {
        String path = "C:\\workspace\\esb-log4j2-patch";
        boolean deleteAfterReading = true;
        new ConfigConverter().run(path, deleteAfterReading);
    }
}