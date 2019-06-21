package com.mtsindonesia.empal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("CompetencyRegisterName")
    @Expose
    private String competencyRegisterName;
    @SerializedName("ScheduleType")
    @Expose
    private String scheduleType;
    @SerializedName("Facilitator")
    @Expose
    private String facilitator;
    @SerializedName("ClassId")
    @Expose
    private String classId;
    @SerializedName("Start")
    @Expose
    private String start;
    @SerializedName("End")
    @Expose
    private String end;

    public String getCompetencyRegisterName() {
        return competencyRegisterName;
    }

    public void setCompetencyRegisterName(String competencyRegisterName) {
        this.competencyRegisterName = competencyRegisterName;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}