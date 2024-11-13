package edu.gmu.cs321;


import static edu.gmu.cs321.State.*;

public class DataEntry extends Employee{
    public DataEntry(String eid, String name) {
        super(eid, name);
    }

    public void submit(Form form) {
        form.setState(REVIEWER_STATE);
        super.submitForm(form);
    }
}
