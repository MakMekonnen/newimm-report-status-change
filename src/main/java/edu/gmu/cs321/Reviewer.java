package edu.gmu.cs321;

import static edu.gmu.cs321.State.*;

public class Reviewer extends Employee{
    public Reviewer(String eid, String name) {
        super(eid, name);
    }

    public void submit(Form form) {
        form.setState(APPROVER_STATE);
        super.submitForm(form);
    }
}
