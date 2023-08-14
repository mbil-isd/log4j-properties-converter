package ua.dp.isd.mbil.log4j.properties.converter.model.elements;

import java.util.List;

public class RootLogger {
    private String level;
    private List<String> appenderRefs;

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setAppenderRefs(List<String> appenderRefs) {
        this.appenderRefs = appenderRefs;
    }

    public List<String> getAppenderRefs() {
        return appenderRefs;
    }
}
