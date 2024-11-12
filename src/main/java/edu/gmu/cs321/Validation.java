package edu.gmu.cs321;

public class Validation {
    private boolean hasOnlyLetters(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasOnlyDigits(String str){
        for (int i=0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c < '0' || c > '9'){
                return false;
            }
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
        if (!hasOnlyLetters(name)){
            return false;
        }
        //Checking if DOB is in correct format
        if (dob.length() != 10){
            return false;
        }
        String cleanDOB = dob.replace("/", "");
        if (cleanDOB.length() != 8){
            return false;
        }

        if (!hasOnlyDigits(cleanDOB)) {
            return false;
        }

        if (!hasOnlyLetters(cleanDOB.substring(0, 2))) {
            return false;
        }

        if (!hasOnlyDigits(cleanDOB.substring(3, 5))) {
            return false;
        }
        if (!hasOnlyLetters(name)) {
            return false;
        }

        //checking fields of a date
        int month = Integer.parseInt(cleanDOB.substring(0, 2), 10);
        int day = Integer.parseInt(cleanDOB.substring(2, 4), 10);

        if (month < 1 || month > 12){
            return false;
        }
        return day >= 1 && day <= 31;   //probably need to check the month in order to get the max days
    }
}
