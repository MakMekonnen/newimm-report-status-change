package edu.gmu.cs321;

public enum State {
    DATA_ENTRY_STATE("DataEntry"),
    REVIEWER_STATE("Review"),
    APPROVER_STATE("Approve"),
    COMPLETE_STATE("Complete");

    private final String value;

    State(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
