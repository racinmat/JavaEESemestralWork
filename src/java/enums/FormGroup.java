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
    personalData(       "nacionále",        new Label[]{Label.name, Label.lastname, Label.studyProgram, Label.branchOfStudy, Label.citizenship, Label.maritalStatus}),
    contact(            "kontakt",          new Label[]{Label.email, Label.cellphone}),
    birth(              "narození",         new Label[]{Label.birthplace, Label.birthcouty, Label.birthnumber, Label.ID, Label.passport}),
    permanentAddress(   "trvalé bydliště",  new Label[]{Label.street, Label.houseNumber, Label.municipalityPart, Label.municipality, Label.couty, Label.zip, Label.country, Label.phone, Label.post}),
    contactAddress(     "kontaktní údaje",  new Label[]{Label.contactmunicipalityPart, Label.contacthouseNumber, Label.contactmunicipality, Label.contactcouty, Label.contactpost, Label.contactzip, Label.contactcountry, Label.contactphone, Label.contactstreet}),
    highSchool(         "střední škola",    new Label[]{Label.nameofHighSchool, Label.adresaofHighSchool, Label.oborofHighSchool, Label.jkov, Label.kkov, Label.izo, Label.yearmaturity}),
    passwordChange(     "změna hesla",      new Label[]{Label.password, Label.newpassword, Label.newpasswordcheck}),
    ;
    
    private String name;
    private Label[] content;

    private FormGroup(String name, Label[] content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public Label[] getContent() {
        return content;
    }
    
    public Label getFirst(HashMap<Label, String> input){
        Label output = getFirst(input.keySet().toArray(new Label[input.keySet().size()]));
        return output;
    }
    
    public Label getLast(HashMap<Label, String> input){
        Label output=getLast(input.keySet().toArray(new Label[input.keySet().size()]));
        return output;
    }
    
    public Label getFirst(ArrayList<Label> input){
        Label output = getFirst(input.toArray(new Label[input.size()]));
        return output;
    }
    
    public Label getLast(ArrayList<Label> input){
        Label output=getLast(input.toArray(new Label[input.size()]));
        return output;
    }
    
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
