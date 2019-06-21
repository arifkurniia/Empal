package com.mtsindonesia.empal;

import java.util.ArrayList;

public class ScheduleCollection {
    public String competencyRegisterName = "";
    public String scheduleType = "";
    public String facilitator = "";
    public String classId = "";
    public String scheduleDate = "";

    public static ArrayList<ScheduleCollection> scheduleCollectionArrayList;
    
    public ScheduleCollection(String competencyRegisterName, String scheduleType, String facilitator, String classId, String scheduleDate){
        this.competencyRegisterName = competencyRegisterName;
        this.scheduleType = scheduleType;
        this.facilitator = facilitator;
        this.classId = classId;
        this.scheduleDate = scheduleDate;
    }
}
