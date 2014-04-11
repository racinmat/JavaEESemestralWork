/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enums;

/**
 *
 * @author Azathoth
 */
public enum LabelCategory {
    cisloId(new Label[]{Label.cisloOP, Label.cislopasu}),
    ;
    
    private Label[] seznam;                                                     //seznam musí být synchronizovaný se sloupcem v enumu Label

    private LabelCategory(Label[] seznam)  {
        this.seznam = seznam;
    }

    public Label[] getSeznam() {
        return seznam;
    }
    
    public static boolean contains(Label label){
        for (LabelCategory labelCategory : LabelCategory.values()) {
            for (Label label1 : labelCategory.seznam) {
                if (label1.equals(label)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static LabelCategory containing(Label label){
        for (LabelCategory labelCategory : LabelCategory.values()) {
            for (Label label1 : labelCategory.seznam) {
                if (label1.equals(label)) {
                    return labelCategory;
                }
            }
        }
        throw new IllegalArgumentException("No LabelCategory found for "+label.getNazevRaw());
    }
}
