/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Azathoth
 */
public enum FormGroup {
    PERSONAL_DATA(      "nacionále",        new Label[]{Label.NAME, Label.LASTNAME, Label.STUDY_PROGRAM, Label.BRANCH_OF_STUDY, Label.CITIZENSHIP, Label.MARITAL_STATUS}),
    CONTACT(            "kontakt",          new Label[]{Label.EMAIL, Label.CELLPHONE}),
    BIRTH(              "narození",         new Label[]{Label.BIRTHPLACE, Label.BIRTHCOUNTY, Label.BIRTHNUMBER, Label.ID, Label.PASSPORT}),
    PERMANENT_ADDRESS(  "trvalé bydliště",  new Label[]{Label.STREET, Label.HOUSE_NUMBER, Label.MUNICIPALITY_PART, Label.MUNICIPALITY, Label.COUNTY, Label.ZIP, Label.COUNTRY, Label.PHONE, Label.POST}),
    CONTACT_ADDRESS(    "kontaktní údaje",  new Label[]{Label.CONTACT_MUNICIPALITY_PART, Label.CONTACT_HOUSE_NUMBER, Label.CONTACT_MUNICIPALITY, Label.CONTACT_COUTY, Label.CONTACT_POST, Label.CONTACT_ZIP, Label.CONTACT_COUNTRY, Label.CONTACT_PHONE, Label.CONTACT_STREET}),
    HIGH_SCHOOL(        "střední škola",    new Label[]{Label.NAME_OF_HIGH_SCHOOL, Label.ADDRESS_OF_HIGH_SCHOOL, Label.OBOR_OF_HIGH_SCHOOL, Label.JKOV, Label.KKOV, Label.IZO, Label.YEAR_MATURITY}),
    PASSWORD_CHANGE(    "změna hesla",      new Label[]{Label.PASSWORD, Label.NEW_PASSWORD, Label.NEW_PASSWORD_CHECK}),
    ;
    
    private final String name;
    private final Label[] content;

    private FormGroup(String name, Label[] content) {
        this.name = name;
        this.content = content;
    }

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return 
     */
    public Label[] getContent() {
        return content;
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public Label getFirst(HashMap<Label, String> input){
        Label output = getFirst(input.keySet().toArray(new Label[input.keySet().size()]));
        return output;
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public Label getLast(HashMap<Label, String> input){
        Label output=getLast(input.keySet().toArray(new Label[input.keySet().size()]));
        return output;
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public Label getFirst(ArrayList<Label> input){
        Label output = getFirst(input.toArray(new Label[input.size()]));
        return output;
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public Label getLast(ArrayList<Label> input){
        Label output=getLast(input.toArray(new Label[input.size()]));
        return output;
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public Label getLast(Label[] input){
        Stack <Label> stack = new Stack<>();
        Label[] list=new Label[input.length];
        for (Label label : input) {                                    //otočí pořadí a poté se najde první
            stack.push(label);
        }
        for (int i = 0; i < list.length; i++) {
            list[i]=stack.pop();
        }
        Label output=getFirst(list);
        return output;
    }
    
    /**
     * 
     * @param input
     * @return 
     */
    public Label getFirst(Label[] input){
        Label output = null;
        int count=0;
        boolean found=false;
        for (Label label : input) {
            if (this.contains(label)&&!found) {
                output=label;
                found=true;
            }
        }
        return output;
    }
    
    /**
     * Determines whether contains label or not.
     * @param label tested if is contained in this object or not.
     * @return true when provided label is contained
     */
    public boolean contains(Label label){
        if (label==null) {
            throw new IllegalArgumentException("You cannot use null as argument.");
        }
        boolean output=false;
        for (int i = 0; i < this.content.length; i++) {
            if (label.equals(this.content[i])) {
                output=true;
            }
        }
        return output;
    }
}
