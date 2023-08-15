package ua.dp.isd.mbil.log4j.properties.converter.model.elements;

import java.util.ArrayList;
import java.util.List;

public class Appender {
    private String name;
    private String type;
    private Layout layout;
    private String fileName;
    private String maxFileSize;
    private Integer maxBackupIndex;
    private Integer maxFilesNumber;
    private List<String> appenderRefs = new ArrayList<>();

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

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public Integer getMaxBackupIndex() {
        return maxBackupIndex;
    }

    public void setMaxBackupIndex(Integer maxBackupIndex) {
        this.maxBackupIndex = maxBackupIndex;
    }

    public Integer getMaxFilesNumber() {
        return maxFilesNumber;
    }

    public void setMaxFilesNumber(Integer maxFilesNumber) {
        this.maxFilesNumber = maxFilesNumber;
    }

    public List<String> getAppenderRefs() {
        return appenderRefs;
    }

    public void setAppenderRefs(List<String> appenderRefs) {
        this.appenderRefs = appenderRefs;
    }
}
