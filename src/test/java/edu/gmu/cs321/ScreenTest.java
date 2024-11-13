package edu.gmu.cs321;

import static edu.gmu.cs321.State.*;
import static edu.gmu.cs321.Status.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ScreenTest
{
    Employee dataEntry = new DataEntry("1", "bob");
    Employee reviewer = new Reviewer("2", "steve");
    Employee approver = new Approver("3", "cuk");

    Form form1 = new Form("5", "11", "gob", "10/28/1999", LAWFUL);
    Form form2 = new Form("6", "24", "berry", "03/26/2002", ASYLUM);
    Form form3 = new Form("7", "15", "sarah", "05/14/2006", CITIZEN);

    // bad form
    Form form4 = new Form("7", "15", "34324", "0d/14/2006", CITIZEN);

    Workflow wf = new Workflow("1", 5);

    @Test
    public void check_id() {
        assertEquals("1", dataEntry.getEid());
        assertEquals("2", reviewer.getEid());
        assertEquals("3", approver.getEid());
    }

    @Test
    public void check_name() {
<<<<<<< HEAD
        assertEquals("bob", data_entry.getName());
=======
        assertEquals("bob", dataEntry.getName());
>>>>>>> 2f6d47c151593ad3834c90efa823756de9a60cbd
        assertEquals("steve", reviewer.getName());
        assertEquals("cuk", approver.getName());
    }

    @Test
    public void check_imm_name() {
        assertEquals("gob", form1.getName());
        assertEquals("berry", form2.getName());
        assertEquals("sarah", form3.getName());
    }

    @Test
    public void check_status_enum() {
        assertEquals(LAWFUL, form1.getStatus());
        assertEquals(ASYLUM, form2.getStatus());
        assertEquals(CITIZEN, form3.getStatus());
    }

    @Test
    public void pass_through_form() {
        wf.addForm(form1);
        assertEquals(DATA_ENTRY_STATE, form1.getState());
        dataEntry.submitForm(form1);
        assertEquals(REVIEWER_STATE, form1.getState());
        reviewer.submitForm(form1);
        assertEquals(APPROVER_STATE, form1.getState());
        approver.submitForm(form1);
        assertEquals(COMPLETE_STATE, form1.getState());
    }

    @Test
    public void check_add_form() {
        wf.addForm(form1);
        wf.addForm(form2);
        assertEquals(wf.getFormList()[0].getFormId(), form1.getFormId());
        assertEquals(wf.getFormList()[1].getFormId(), form2.getFormId());
    }

    @Test
    public void check_search_form() {
        wf.addForm(form1);
        wf.addForm(form2);
        assertEquals("5", wf.searchForm("5").getFormId());
        assertEquals("6", wf.searchForm("6").getFormId());
    }

    // Fix Validator Class
    @Test
    public void check_validate_form() {
        Validation v = new Validation();
        assertTrue(v.validateForm(form1));
        assertFalse(v.validateForm(form4));
    }
}
