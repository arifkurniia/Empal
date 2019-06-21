package com.mtsindonesia.empal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompetencyGroup {
    @SerializedName("CompetencyBookId")
    @Expose
    private String competencyBookId;
    @SerializedName("CompetencyGroupId")
    @Expose
    private String competencyGroupId;
    @SerializedName("CompetencyGroupName")
    @Expose
    private String competencyGroupName;

    public String getCompetencyBookId() {
        return competencyBookId;
    }

    public void setCompetencyBookId(String competencyBookId) {
        this.competencyBookId = competencyBookId;
    }

    public String getCompetencyGroupId() {
        return competencyGroupId;
    }

    public void setCompetencyGroupId(String competencyGroupId) {
        this.competencyGroupId = competencyGroupId;
    }

    public String getCompetencyGroupName() {
        return competencyGroupName;
    }

    public void setCompetencyGroupName(String competencyGroupName) {
        this.competencyGroupName = competencyGroupName;
    }
}
