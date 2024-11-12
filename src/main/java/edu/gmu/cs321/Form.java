package edu.gmu.cs321;

import static edu.gmu.cs321.State.*;

public class Form {

    private String formId;
    private String aid;
    private State state;
    private String name;
    private String dob;
    private Status status;

    public Form(String formId, String aid, String name, String dob, Status status) {
        this.formId = formId;
        this.aid = aid;
        this.name = name;
        this.dob = dob;
        this.status = status;
        this.state = DATA_ENTRY_STATE;
    }

    public void submitForm() {
        // Function to submit form
    }

    public void validateForm() {
        // Function to validate the form
    }

    public void showForm() {
        // Function to show the form
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}