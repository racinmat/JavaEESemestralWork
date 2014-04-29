package enums;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * This enum is used for keeping in one group all Labels which shall be in one fieldset in forms. Only visual stuff.
 * @author Azathoth
 */
public enum FormGroup {
    
    /**
     * Personal data, contains Label.NAME, Label.LASTNAME, Label.STUDY_PROGRAM, Label.BRANCH_OF_STUDY, Label.CITIZENSHIP, Label.MARITAL_STATUS.
     */
    PERSONAL_DATA(      "nacionále",        new Label[]{Label.NAME, Label.LASTNAME, Label.STUDY_PROGRAM, Label.BRANCH_OF_STUDY, Label.CITIZENSHIP, Label.MARITAL_STATUS}),
    /**
     * Contact, contains Label.EMAIL, Label.CELLPHONE.
     */
    CONTACT(            "kontakt",          new Label[]{Label.EMAIL, Label.CELLPHONE}),
    /**
     * Birth, contains Label.BIRTHPLACE, Label.BIRTHCOUNTY, Label.BIRTHNUMBER, Label.ID, Label.PASSPORT.
     */
    BIRTH(              "narození",         new Label[]{Label.BIRTHPLACE, Label.BIRTHCOUNTY, Label.BIRTHNUMBER, Label.ID, Label.PASSPORT}),
    /**
     * Permantne address, contains Label.STREET, Label.HOUSE_NUMBER, Label.MUNICIPALITY_PART, Label.MUNICIPALITY, Label.COUNTY, Label.ZIP, Label.COUNTRY, Label.PHONE, Label.POST.
     */
    PERMANENT_ADDRESS(  "trvalé bydliště",  new Label[]{Label.STREET, Label.HOUSE_NUMBER, Label.MUNICIPALITY_PART, Label.MUNICIPALITY, Label.COUNTY, Label.ZIP, Label.COUNTRY, Label.PHONE, Label.POST}),
    /**
     * Contact address, contains Label.CONTACT_MUNICIPALITY_PART, Label.CONTACT_HOUSE_NUMBER, Label.CONTACT_MUNICIPALITY, Label.CONTACT_COUTY, Label.CONTACT_POST, Label.CONTACT_ZIP, Label.CONTACT_COUNTRY, Label.CONTACT_PHONE, Label.CONTACT_STREET.
     */
    CONTACT_ADDRESS(    "kontaktní údaje",  new Label[]{Label.CONTACT_MUNICIPALITY_PART, Label.CONTACT_HOUSE_NUMBER, Label.CONTACT_MUNICIPALITY, Label.CONTACT_COUTY, Label.CONTACT_POST, Label.CONTACT_ZIP, Label.CONTACT_COUNTRY, Label.CONTACT_PHONE, Label.CONTACT_STREET}),
    /**
     * HIgh School, contains Label.NAME_OF_HIGH_SCHOOL, Label.ADDRESS_OF_HIGH_SCHOOL, Label.BRANCH_OF_HIGH_SCHOOL, Label.JKOV, Label.KKOV, Label.IZO, Label.YEAR_MATURITY.
     */
    HIGH_SCHOOL(        "střední škola",    new Label[]{Label.NAME_OF_HIGH_SCHOOL, Label.ADDRESS_OF_HIGH_SCHOOL, Label.BRANCH_OF_HIGH_SCHOOL, Label.JKOV, Label.KKOV, Label.IZO, Label.YEAR_MATURITY}),
    /**
     * Password change, contains Label.PASSWORD, Label.NEW_PASSWORD, Label.NEW_PASSWORD_CHECK.
     */
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
     * @return name for users in String, is used as legend for fieldset.
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return array of Labels containing all Labels from this group.
     */
    public Label[] getContent() {
        return content;
    }
    
    /**
     * Returns first label from provided Map contained in this enum.
     * @param input Map where is searched first occurence of any Label from this FormGroup
     * @return first Label from the beginning of Map contained in this FormGroup
     */
    public Label getFirst(Map<Label, String> input){
        Label output = getFirst(input.keySet().toArray(new Label[input.keySet().size()]));
        return output;
    }
    
    /**
     * Returns last label from provided Map contained in this enum.
     * @param input Map where is searched last occurence of any Label from this FormGroup
     * @return last Label from the beginning of Map contained in this FormGroup
     */
    public Label getLast(Map<Label, String> input){
        Label output=getLast(input.keySet().toArray(new Label[input.keySet().size()]));
        return output;
    }
    
    /**
     * Returns first label from provided List contained in this enum.
     * @param input List where is searched first occurence of any Label from this FormGroup
     * @return first Label from the beginning of List contained in this FormGroup
     */
    public Label getFirst(List<Label> input){
        Label output = getFirst(input.toArray(new Label[input.size()]));
        return output;
    }
    
    /**
     * Returns last label from provided List contained in this enum.
     * @param input List where is searched last occurence of any Label from this FormGroup
     * @return last Label from the beginning of List contained in this FormGroup
     */
    public Label getLast(List<Label> input){
        Label output=getLast(input.toArray(new Label[input.size()]));
        return output;
    }
    
    /**
     * Returns last label from provided array contained in this enum.
     * @param input array where is searched last occurence of any Label from this FormGroup
     * @return last Label from the beginning of array contained in this FormGroup
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
     * Returns first label from provided array contained in this enum.
     * @param input array where is searched first occurence of any Label from this FormGroup
     * @return first Label from the beginning of array contained in this FormGroup, return null is provided label is not in this enum
     */
    public Label getFirst(Label[] input) throws IllegalArgumentException{
        for (Label label : input) {
            if (this.contains(label)) {
                return label;
            }
        }
        throw new IllegalArgumentException(this.getName()+" does not contain any of provided labels");
    }
    
    /**
     * Determines whether contains label or not.
     * @param label tested if is contained in this object or not.
     * @return true when provided label is contained, otherwise return false
     */
    public boolean contains(Label label) throws IllegalArgumentException{
        if (label==null) {
            throw new IllegalArgumentException("You cannot use null as argument.");
        }
        for (Label content : this.content) {
            if (label.equals(content)) {
                return true;
            }
        }
        return false;
    }
}
