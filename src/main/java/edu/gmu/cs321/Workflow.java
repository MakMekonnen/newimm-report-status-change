package edu.gmu.cs321;

public class Workflow {
    private String workflowId;
    private Form[] formList;
    private String eid;
    int tail = 0;

    public Workflow(String workflowId, int formLength) {
        this.workflowId = workflowId;
        formList = new Form[formLength];
    }

    public void addForm(Form form) {
        if (tail < formList.length) {
            formList[tail++] = form;
        }
    }

    public Form searchForm(int formId) {
        for (Form form : formList) {
            if (form.getFormId() == formId) {
                return form;
            }
        }
        return null;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public Form[] getFormList() {
        return formList;
    }


}
