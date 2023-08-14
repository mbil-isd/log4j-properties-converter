package ua.dp.isd.mbil.log4j.properties.converter.model.elements;

import ua.dp.isd.mbil.log4j.properties.converter.model.Layout;

public class Appender {
    private String name;
    private String type;
    private Layout layout;

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
}
