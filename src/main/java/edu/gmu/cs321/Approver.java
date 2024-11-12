package edu.gmu.cs321;

import static edu.gmu.cs321.State.*;

public class Approver extends Employee{
    public Approver(String eid, String name) {
        super(eid, name);
    }

    public void submit(Form form) {
        form.setState(COMPLETE_STATE);
    }
}
