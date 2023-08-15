package ua.dp.isd.mbil.log4j.properties.converter.model.elements;

import java.util.HashSet;
import java.util.Set;

public class Logger {
    private String key;
    private String name;
    private String level;
    private Set<String> appenderRefs = new HashSet<>();

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

    public void setAppenderRefs(Set<String> appenderRefs) {
        this.appenderRefs = appenderRefs;
    }

    public Set<String> getAppenderRefs() {
        return appenderRefs;
    }
}
