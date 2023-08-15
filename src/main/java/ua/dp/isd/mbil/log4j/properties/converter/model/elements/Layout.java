package ua.dp.isd.mbil.log4j.properties.converter.model.elements;

public class Layout {
    private String type;
    private boolean hasPattern;
    private String pattern;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setHasPattern(boolean hasPattern) {
        this.hasPattern = hasPattern;
    }

    public boolean hasPattern() {
        return hasPattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
        setHasPattern(true);
    }

    public String getPattern() {
        return pattern;
    }
}
