package edu.gmu.cs321;

import static edu.gmu.cs321.State.*;
import static edu.gmu.cs321.Status.*;
import static org.junit.Assert.*;

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
        assertEquals("1", data_entry.get_eid());
        assertEquals("2", reviewer.get_eid());
        assertEquals("3", approver.get_eid());
    }

    @Test
    public void check_name() {
        assertEquals("bob", data_entry.get_name());
        assertEquals("steve", reviewer.get_name());
        assertEquals("cuk", approver.get_name());
    }

    @Test
    public void check_imm_name() {
        assertEquals("gob", form1.get_name());
        assertEquals("berry", form2.get_name());
        assertEquals("sarah", form3.get_name());
    }

    @Test
    public void check_status_enum() {
        assertEquals(LAWFUL, form1.get_status());
        assertEquals(ASYLUM, form2.get_status());
        assertEquals(CITIZEN, form3.get_status());
    }

    @Test
    public void pass_through_form() {
        wf.add_form(form1);
        assertEquals(DATA_ENTRY_STATE, form1.get_state());
        data_entry.submit(form1);
        assertEquals(REVIEWER_STATE, form1.get_state());
        reviewer.submit(form1);
        assertEquals(APPROVER_STATE, form1.get_state());
        approver.submit(form1);
        assertEquals(COMPLETE_STATE, form1.get_state());
    }

    @Test
    public void check_add_form() {
        wf.add_form(form1);
        wf.add_form(form2);
        assertEquals(wf.get_form_list()[0].get_form_id(), form1.get_form_id());
        assertEquals(wf.get_form_list()[1].get_form_id(), form2.get_form_id());
    }

    @Test
    public void check_search_form() {
        wf.add_form(form1);
        wf.add_form(form2);
        assertEquals("5", wf.search_form("5").get_form_id());
        assertEquals("6", wf.search_form("6").get_form_id());
    }

    // Fix Validator Class
    @Test
    public void check_validate_form() {
        Validation v = new Validation();
        assertTrue(v.validate_form(form1));
        assertFalse(v.validate_form(form4));
    }
}
