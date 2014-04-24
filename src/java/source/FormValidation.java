/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import enums.Label;
import enums.LabelCategory;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Azathoth
 */
public class FormValidation {
    private final Map<Label, String> notFilled;
    private final boolean error;
    
    /**
     * Determines whether provided string is numeric and contains only whole number or not.
     * @param str String you want to test
     * @return true only when provided String contains whole number, otherwise return false
     */
    public static boolean notNumeric(String str)  {  
        try {  
          Long.parseLong(str);  
        } catch(NumberFormatException nfe){  
          return true;  
        }  
        return false;  
    }
    
    /**
     * Determines whether provided string contain correct syntax for email address or not.
     * @param str String you want to test
     * @return true only when provided String has correct email syntax, otherwise return false
     */
    public static boolean notValidEmail(String str){
        int atposition=str.indexOf("@");                                        //pozice zavináče
        int dotposition=str.lastIndexOf(".");                                   //pozice tečky
        return atposition<1 || dotposition<atposition+2 || dotposition+2>=str.length();
    }
    
    /**
     * Determines whether provided string contain correct syntax for birthnumber or not.
     * @param str String you want to test
     * @return true only when provided String has correct birthnumber syntax, otherwise return false
     */
    public static boolean notValidBirthNumber(String str){
        if (str.contains("/")) {
            str=str.replace("/", "");
        }
        if (notNumeric(str)) {
            return true;
        }
        if (!(str.length()==10)) {
            return true;
        }
        int month=Integer.parseInt(str.substring(2, 4));
        if (month==0||(month>12&&month<21)||(month>32&&month<51)||(month>62&&month<71)||month>82) {
            return true;
        }
        int day=Integer.parseInt(str.substring(4, 6));
        if (day==0||day>31) {
            return true;
        }
        long number=Long.parseLong(str);
        return number%11!=0;
    }
    
    /**
     * Extracts birth day from provided String, expecting provided String is valid birth number.
     * @param str valid birth number which contains birth day
     * @return birth day from provided birth number
     */
    public static String getBirthDay(String str){
        String temp=str.substring(4,6);
        int output=Integer.parseInt(temp);
        return Integer.toString(output);
    }
    
    /**
     * Extracts birth month from provided String, expecting provided String is valid birth number.
     * @param str valid birth number which contains birth month
     * @return birth month from provided birth number
     */
    public static String getBirthMonth(String str){
        String temp=str.substring(2,4);
        int output=Integer.parseInt(temp);
        if (output>20&&output<33) {
            return Integer.toString(output-20);
        }
        if (output>50&&output<63) {
            return Integer.toString(output-50);
        }
        if (output>70&&output<83) {
            return Integer.toString(output-70);
        }
        return Integer.toString(output);
    }
    
    /**
     * Extracts birth year from provided String, expecting provided String is valid birth number.
     * @param str valid birth number which contains birth year
     * @return birth year from provided birth number, only in rage of 1930 to 2029
     */
    public static String getBirthYear(String str){
        String temp=str.substring(0,2);
        int output=Integer.parseInt(temp);
        if (output<30) {
            output+=2000;
        } else {
            output+=1900;
        }
        return Integer.toString(output);
    }
    
    /**
     * Determines whether provided strings are calling code and phone number or not.
     * @param callingCode calling code before the phone number
     * @param str phone number you want to test
     * @return true only when provided String is phone number, otherwise return false
     */
    public static boolean notValidPhoneNumber(String callingCode, String str){
        str=callingCode+str;
        return notValidPhoneNumber(str);
    }
    
    /**
     * Strips calling code of provided string, expecting telephone number as parameter
     * @param str valid phone number, when it has calling code before it, calling code shall be stripped
     * @return provided string, but without calling code in the beginning
     */
    public static String stripCallingCode(String str){
        if (str.startsWith("+")) {
            str=str.substring(4, str.length());
            return str;
        }
        if (str.length()>9) {
            str=str.substring(str.length()-9, str.length());
            return str;
        }
        return str;
    }
    
    /**
     * Iterates through provided Map and when Map value contains any valid phone number, its calling code will be stripped.
     * @param input Map whose value should be containing valid phone number (nothing happens, when no valid phone number is found).
     * @return same Map, but with calling codes of all valid phone numbers stripped
     */
    public static Map<Label, String> stripCallingCode(Map<Label, String> input){
        for (Label label : input.keySet()) {
            if (label.isPhonenumber()) {
                input.put(label, stripCallingCode(input.get(label)));
            }
        }
        return input;
    }
    
    /**
     * Determines whether provided string is phone number or not.
     * @param str String you want to test
     * @return true only when provided String is phone number, otherwise return false
     */
    public static boolean notValidPhoneNumber(String str){
        int plusposition=str.indexOf("+");                                      //pozice plusu z předvolby
        if (plusposition>0){
            return true;                                                        //pokud neprojde validace, nastaví se error na false
        }
        if (notNumeric(str.substring(1))) {     //vynechá plus
            return true;
        }
        return str.length()>13||str.length()<13;
    }
    
    /**
     * Extracts sex from provided String, expecting provided String is valid birth number.
     * @param str valid birth number which contains sex
     * @return sex from provided birth number, only in rage of 1930 to 2029
     */
    public static String getSexFromBirthNumber(String str){
        String temp=str.substring(2,4);
        int output=Integer.parseInt(temp);
        if (output>50) {
            return "žena";
        }
        return "muž";
    }
    
    /**
     * Validates provided form according to parameters of Labels used as keys.
     * @param input Map containing Labels as keys and String as values, which will be tested
     * @param notFilledStyle String containing css code which will be appended to style of input labels of HTML form whose data are being validated
     * @return Object containing two fields: first is Map notFilled, with same Labels as keys as provided Map and as value "" or notFilledStyle, depending on result of validation of each value in provided Map, second is boolean determining whether validation was successful or there were any errors
     */
    public static FormValidation validateForm(Map<Label, String> input, String notFilledStyle){
        Map<Label, String> notFilled = new LinkedHashMap<>();
        boolean error = false;
        for (Label label : input.keySet()) {                                    //foreach cyklus pouze pro políčka, která jsou ve formuláři
            notFilled.put(label, "");                                           //notFilled is filled on the begin of each iteration, might be overwrited later in iteration by notFilledStyle
            if (LabelCategory.contains(label)) {
                LabelCategory temp=LabelCategory.containing(label);
                Label[] tempArray=temp.getList();
                for (Label label2 : tempArray) {                                //projde všechna políčka, z nichž je jedno povinné
                    if (!input.get(label2).equals("")) {
                        label=label2;                                           //tím se label přesměruje na label, kde je input vyplněný
                    }
                }
            }
            if (LabelCategory.contains(label)||label.isObligatory()||!input.get(label).equals("")) {          //kontrola povinných a nepovinných, vyplněných položek
                if (label.isNumber()) {
                    if (notNumeric(input.get(label))) {                         //kontrola číslených položek
                        notFilled.put(label, notFilledStyle);                   
                        error=true;
                    }
                }
                if(label.isEmailAddress()){
                    if (notValidEmail(input.get(label))) {
                        notFilled.put(label, notFilledStyle);
                        error=true;
                    }
                }
                if (label.isPhonenumber()) {
                    if (!(!label.isObligatory()&&stripCallingCode(input.get(label)).equals(""))) {   //ošetření bugu s přidáváním předvolby u nepovinných políček
                        if (notValidPhoneNumber(input.get(label))) {
                            notFilled.put(label, notFilledStyle);
                            error=true;
                        }
                    }
                }
                if (label.isbirthNumber()) {
                    if (notValidBirthNumber(input.get(label))) {
                        notFilled.put(label, notFilledStyle);
                        error=true;
                    }
                }
                if (label.isObligatory()&&input.get(label).equals("")) {           //test povinných políček
                    notFilled.put(label, notFilledStyle);
                    error=true;
                }
            }
            if (label.getCopiedFrom()!=null) {                                 //pro kontaktní údaje, které se kopírují z údajů o trvalém bydlišti
                if (input.get(label).equals("")) {
                    input.put(label, input.get(label.getCopiedFrom()));
                }
            }
        }
        return new FormValidation(notFilled, error);
    }

    /**
     * Constuctor used for saving two variables from return of formValidation method.
     * @param notFilled contains Map, with same Labels as keys as provided Map and as value "" or notFilledStyle, depending on result of validation of each value in provided Map
     * @param error boolean determining whether validation was successful or there were any errors
     */
    public FormValidation(Map<Label, String> notFilled, boolean error) {
        this.notFilled = notFilled;
        this.error = error;
    }

    /**
     * 
     * @return Map showing which input fields were filled wrong
     */
    public Map<Label, String> getNotFilled() {
        return notFilled;
    }

    /**
     * 
     * @return true if the validation was successful, otherwise return false
     */
    public boolean isError() {
        return error;
    }

}
