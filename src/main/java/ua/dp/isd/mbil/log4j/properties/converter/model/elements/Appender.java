package ua.dp.isd.mbil.log4j.properties.converter.model.elements;

import ua.dp.isd.mbil.log4j.properties.converter.model.Layout;

public class Appender {
    private String name;
    private String type;
    private Layout layout;
    private String fileName;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        String result = "appender." + name + ".name = " + name +
                System.lineSeparator() +
                "appender." + name + ".type = " + type +
                System.lineSeparator() +
                "appender." + name + ".layout = " + layout.getType();
        if (layout.hasPattern()) {
            result += System.lineSeparator() +
                    "appender." + name + ".layout.pattern = " + layout.getPattern();
        }
        if (fileName != null) {
            result += System.lineSeparator() +
                    "appender." + name + ".fileName = " + fileName;
        }
        return result;
    }
}
