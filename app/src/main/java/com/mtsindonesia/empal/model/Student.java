package com.mtsindonesia.empal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("StudentId")
    @Expose
    private String studentId;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("ContactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Company")
    @Expose
    private String company;
    @SerializedName("NIP")
    @Expose
    private String nIP;
    @SerializedName("FunctionProfile")
    @Expose
    private String functionProfile;
    @SerializedName("FunctionProfiles")
    @Expose
    private Object functionProfiles;
    @SerializedName("Evaluation")
    @Expose
    private Object evaluation;
    @SerializedName("ApprovalStatus")
    @Expose
    private Boolean approvalStatus;
    @SerializedName("SelectedGrid")
    @Expose
    private Object selectedGrid;
    @SerializedName("StringDateOfBirth")
    @Expose
    private String stringDateOfBirth;
    @SerializedName("ModifiedDate")
    @Expose
    private Integer modifiedDate;
    @SerializedName("ModifiedBy")
    @Expose
    private String modifiedBy;
    @SerializedName("CreatedDate")
    @Expose
    private Integer createdDate;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNIP() {
        return nIP;
    }

    public void setNIP(String nIP) {
        this.nIP = nIP;
    }

    public String getFunctionProfile() {
        return functionProfile;
    }

    public void setFunctionProfile(String functionProfile) {
        this.functionProfile = functionProfile;
    }

    public Object getFunctionProfiles() {
        return functionProfiles;
    }

    public void setFunctionProfiles(Object functionProfiles) {
        this.functionProfiles = functionProfiles;
    }

    public Object getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Object evaluation) {
        this.evaluation = evaluation;
    }

    public Boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Object getSelectedGrid() {
        return selectedGrid;
    }

    public void setSelectedGrid(Object selectedGrid) {
        this.selectedGrid = selectedGrid;
    }

    public String getStringDateOfBirth() {
        return stringDateOfBirth;
    }

    public void setStringDateOfBirth(String stringDateOfBirth) {
        this.stringDateOfBirth = stringDateOfBirth;
    }

    public Integer getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Integer modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
