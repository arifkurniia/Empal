package com.example.empal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompetencyBook {
    @SerializedName("CompetencyBookId")
    @Expose
    private String competencyBookId;
    @SerializedName("CompetencyBookName")
    @Expose
    private String competencyBookName;

    public String getCompetencyBookId() {
        return competencyBookId;
    }

    public void setCompetencyBookId(String competencyBookId) {
        this.competencyBookId = competencyBookId;
    }

    public String getCompetencyBookName() {
        return competencyBookName;
    }

    public void setCompetencyBookName(String competencyBookName) {
        this.competencyBookName = competencyBookName;
    }
}
