package ua.dp.isd.mbil.log4j.properties.converter.util;

import ua.dp.isd.mbil.log4j.properties.converter.model.Config;

import java.util.ArrayList;
import java.util.List;

public class ConfigReader {
    public <T extends Config> List<T> read(String path, Class<T> configType) {
        return new ArrayList<>();
    }
}
