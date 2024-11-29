package edu.gmu.cs321;
import java.util.HashMap;

import com.cs321.Workflow;

public class Test {

    public static Workflow workflow = new Workflow();
    public static HashMap<Integer, Form> formIDToForm = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Test 1: Add valid item
        System.out.println("Test 1: Add valid item (FormID: 1, NextStep: 'Review')");
        int result1 = workflow.AddWFItem(1, "Review");
        System.out.println("Result: " + result1); // Expected: 0

        // Test 2: Add item with invalid next step
        System.out.println("Test 2: Add item with invalid next step (FormID: 2, NextStep: 'InvalidStep')");
        int result2 = workflow.AddWFItem(2, "InvalidStep");
        System.out.println("Result: " + result2); // Expected: -1

        // Test 3: Add item with existing FormID
        System.out.println("Test 3: Add item with existing FormID (FormID: 1, NextStep: 'Approve')");
        int result3 = workflow.AddWFItem(1, "Approve");
        System.out.println("Result: " + result3); // Expected: -2

        // Test 4: Add item with negative FormID
        System.out.println("Test 4: Add item with negative FormID (FormID: -1, NextStep: 'Review')");
        int result4 = workflow.AddWFItem(-1, "Review");
        System.out.println("Result: " + result4); // Expected: -2

        // Test 5: Get next item with valid next step
        System.out.println("Test 5: Get next item (NextStep: 'Review')");
        int result5 = workflow.GetNextWFItem("Review");
        System.out.println("Result: " + result5); // Expected: 1

        // Test 6: Get next item from empty queue
        System.out.println("Test 6: Get next item from empty queue (NextStep: 'Review')");
        workflow.GetNextWFItem("Review"); // Remove the only item
        int result6 = workflow.GetNextWFItem("Review");
        System.out.println("Result: " + result6); // Expected: -3

        // Test 7: Get next item with invalid next step
        System.out.println("Test 7: Get next item with invalid next step (NextStep: 'InvalidStep')");
        int result7 = workflow.GetNextWFItem("InvalidStep");
        System.out.println("Result: " + result7); // Expected: -1

        // Test 8: Add another valid item and check FIFO
        System.out.println("Test 8: Add valid item (FormID: 2, NextStep: 'Approve')");
        workflow.AddWFItem(2, "Approve");
        System.out.println("Get next item (NextStep: 'Review')");
        int result8 = workflow.GetNextWFItem("Review");
        System.out.println("Result: " + result8); // Expected: -3 since queue is empty
        System.out.println("Get next item (NextStep: 'Approve')");
        int result9 = workflow.GetNextWFItem("Approve");
        System.out.println("Result: " + result9); // Expected: 2
    }
}