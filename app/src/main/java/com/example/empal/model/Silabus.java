package com.example.empal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Silabus {

    @SerializedName("SilabusName")
    @Expose
    private String silabusName;
    @SerializedName("FileUrl")
    @Expose
    private String fileUrl;

    public String getSilabusName() {
        return silabusName;
    }

    public void setSilabusName(String silabusName) {
        this.silabusName = silabusName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}