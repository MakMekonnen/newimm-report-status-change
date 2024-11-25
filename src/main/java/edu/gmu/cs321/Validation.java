package edu.gmu.cs321;

public class Validation {

    public static boolean hasOnlyLetters(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasOnlyDigits(String str){
        for (int i=0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c < '0' || c > '9'){
                return false;
            }
        }
        return true;
    }
    /*
     * Data Entry Validation Functions
     */
    public static boolean checkNameFormat(String input){
        if(input == null){
            return false;
        }
        else if(input.trim().isEmpty()){
            return false;
        }
        else if (input.split(" ").length != 1){
            return false;
        }
        else if (!hasOnlyLetters(input.trim())){
            return false;
        }
        return true;
    }

    public static boolean checkDateFormat(String input){
        if(input == null){
            return false;
        }
        else if (input.trim().isEmpty()){
            return false;
        }
        if (input.length() != 10){
            return false;
        }
        String dob = input.replace("/", "");
        if (dob.length() != 8){
            return false;
        }
        if (!hasOnlyDigits(dob)) {
            return false;
        }
        //checking fields of a date
        int month = Integer.parseInt(dob.substring(0, 2), 10);
        int day = Integer.parseInt(dob.substring(2, 4), 10);
        if (month < 1 || month > 12){
            return false;
        }
        return day >= 1 && day <= 31;   //probably need to check the month in order to get the max days
    }

    public static boolean checkAIDFormat(String input){
        if(input == null){
            return false;
        }
        else if(input.trim().isEmpty()){
            return false;
        }
        else if(!input.substring(0, 2).equals("A-")){
            return false;
        }
        String nums = input.substring(2);
        if(nums.length() < 6 || nums.length() > 9){
            return false;
        }
        else if (!hasOnlyDigits(nums)){
            return false;
        }
        return true;
    }

    public boolean validateForm(Form form) {
        //Checking if any fields are null
        if (form == null) {
            return false;
        }

        String name = form.getName();
        String dob = form.getDob(); //used for further testing

        if (form.getFormId() == null){
            return false;
        }
        if (form.getAid() == null){
            return false;
        }
        if (name == null){
            return false;
        }
        if (dob == null){
            return false;
        }
        if (form.getStatus() == null){
            return false;
        }
        if (form.getState() == null){
            return false;
        }
        if (!checkNameFormat(name)){
            return false;
        }
        return checkDateFormat(dob);
    }
}
