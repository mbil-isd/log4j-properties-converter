package ua.dp.isd.mbil.log4j.properties.converter.model.elements;

public class Logger {
    private String key;
    private String name;
    private String level;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "logger." + key + ".name = " + name +
                System.lineSeparator() +
                "logger." + key + ".level = " + level;
    }
}
