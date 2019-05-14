package com.example.empal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompetencyRegister {
    @SerializedName("CompetencyGroupId")
    @Expose
    private String competencyGroupId;
    @SerializedName("CompetencyRegisterId")
    @Expose
    private String competencyRegisterId;
    @SerializedName("CompetencyRegisterName")
    @Expose
    private String competencyRegisterName;

    public String getCompetencyGroupId() {
        return competencyGroupId;
    }

    public void setCompetencyGroupId(String competencyGroupId) {
        this.competencyGroupId = competencyGroupId;
    }

    public String getCompetencyRegisterId() {
        return competencyRegisterId;
    }

    public void setCompetencyRegisterId(String competencyRegisterId) {
        this.competencyRegisterId = competencyRegisterId;
    }

    public String getCompetencyRegisterName() {
        return competencyRegisterName;
    }

    public void setCompetencyRegisterName(String competencyRegisterName) {
        this.competencyRegisterName = competencyRegisterName;
    }
}
