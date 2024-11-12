package edu.gmu.cs321;

public class Employee {
    private String eid;
    private String name;

    public Employee(String eid, String name) {
        this.eid = eid;
        this.name = name;
    }

    public String getEid() {
        return eid;
    }
    public String getName() {
        return name;
    }
    public void setEid(String eid) {
        this.eid = eid;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void submitForm(Form form) {
        // Submits the form
    }
}
