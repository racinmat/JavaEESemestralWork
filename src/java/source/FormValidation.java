/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import enums.Label;
import enums.LabelCategory;
import enums.SQLTables;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azathoth
 */
public class FormValidation {
    private HashMap<Label, String> notFilled;
    private boolean error;
    
    
    public static boolean notNumeric(String str)  {  
        try {  
          long temp = Long.parseLong(str);  
        } catch(NumberFormatException nfe){  
          return true;  
        }  
        return false;  
    }
    
    public static boolean notValidEmail(String str){
        int atposition=str.indexOf("@");                                        //pozice zavináče
        int dotposition=str.lastIndexOf(".");                                   //pozice tečky
        if (atposition<1 || dotposition<atposition+2 || dotposition+2>=str.length()){
            return true;                                                        //pokud neprojde validace, nastaví se error na false
        }
        return false;
    }
    
    public static boolean notValidBirthNumber(String str){
        if (str.contains("/")) {
            str=str.replace("/", "");
        }
        if (notNumeric(str)) {
            return notNumeric(str);
        }
        if (!(str.length()==10)) {
            return true;
        }
        int mesic=Integer.parseInt(str.substring(2, 4));
        if (mesic==0||(mesic>12&&mesic<21)||(mesic>32&&mesic<51)||(mesic>62&&mesic<71)||mesic>82) {
            return true;
        }
        int den=Integer.parseInt(str.substring(4, 6));
        if (den==0||den>31) {
            return true;
        }
        long cislo=Long.parseLong(str);
        if (cislo%11!=0) {
            return true;
        }
        return false;
    }
    
    public static String getBirthDay(String str){
        String temp=str.substring(4,6);
        int output=Integer.parseInt(temp);
        return Integer.toString(output);
    }
    
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
    
    public static boolean notValidPhoneNumber(String predvolba, String str){
        str=predvolba+str;
        return notValidPhoneNumber(str);
    }
    
    public static String stripPredvolba(String str){
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
    
    public static HashMap<Label, String> stripPredvolba(HashMap<Label, String> input){
        for (Label label : input.keySet()) {
            if (label.isTelefonniCislo()) {
                input.put(label, stripPredvolba(input.get(label)));
            }
        }
        return input;
    }
    
    public static boolean notValidPhoneNumber(String str){
        int plusposition=str.indexOf("+");                                      //pozice plusu z předvolby
        if (plusposition>0){
            return true;                                                        //pokud neprojde validace, nastaví se error na false
        }
        if (notNumeric(str.substring(1))) {     //vynechá plus
            return true;
        }
        if (str.length()>13||str.length()<13) {
            return true;
        }
        return false;
    }
    
    public static String getSexFromBirthNumber(String str){
        String temp=str.substring(2,4);
        int output=Integer.parseInt(temp);
        if (output>50) {
            return "žena";
        }
        return "muž";
    }
    
    public static FormValidation validateForm(HashMap<Label, String> input, SQLTables table, String notFilledStyle){
        HashMap<Label, String> notFilled = new HashMap<>();
        for (Label label : Label.values()) {
            if (label.isInTable(table)&&!label.isAutomatickeVyplneni()) {
                notFilled.put(label, "");
            }
        }
        boolean error = false;
        for (Label label : input.keySet()) {                                    //foreach cyklus pouze pro políčka, která jsou ve formuláři
            if (LabelCategory.contains(label)) {
                boolean empty=false;
                LabelCategory temp=LabelCategory.containing(label);
                Label[] tempArray=temp.getSeznam();
                for (Label label2 : tempArray) {                                //projde všechna políčka, z nichž je jedno povinné
                    if (!input.get(label2).equals("")) {
                        empty=true;
                        label=label2;                                           //tím se label přesměruje na label, kde je input vyplněný
                    }
                }
            }
            if (LabelCategory.contains(label)||label.isPovinne()||!input.get(label).equals("")) {          //kontrola povinných a nepovinných, vyplněných položek
                if (label.isCiselne()) {
                    if (notNumeric(input.get(label))) {                         //kontrola číslených položek
                        notFilled.put(label, notFilledStyle);                   
                        error=true;
                    }
                }
                if(label.isEmailovaadresa()){
                    if (notValidEmail(input.get(label))) {
                        notFilled.put(label, notFilledStyle);
                        error=true;
                    }
                }
                if (label.isTelefonniCislo()) {
                    if (!(!label.isPovinne()&&stripPredvolba(input.get(label)).equals(""))) {   //ošetření bugu s přidáváním předvolby u nepovinných políček
                        if (notValidPhoneNumber(input.get(label))) {
                            notFilled.put(label, notFilledStyle);
                            error=true;
                        }
                    }
                }
                if (label.isRodneCislo()) {
                    if (notValidBirthNumber(input.get(label))) {
                        notFilled.put(label, notFilledStyle);
                        error=true;
                    }
                }
                if (label.isPovinne()&&input.get(label).equals("")) {           //test povinných políček
                    notFilled.put(label, notFilledStyle);
                    error=true;
                }
            }
            if (label.getKopirovanoZ()!=null) {                                 //pro kontaktní údaje, které se kopírují z údajů o trvalém bydlišti
                if (input.get(label).equals("")) {
                    input.put(label, input.get(label.getKopirovanoZ()));
                }
            }
        }
        FormValidation output=new FormValidation(notFilled, error);
        return output;
    }

    public FormValidation(HashMap<Label, String> notFilled, boolean error) {
        this.notFilled = notFilled;
        this.error = error;
    }

    public HashMap<Label, String> getNotFilled() {
        return notFilled;
    }

    public boolean isError() {
        return error;
    }

}
