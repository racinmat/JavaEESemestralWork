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
    ID_NUMBER(new Label[]{Label.ID, Label.PASSPORT}),
    ;
    
    private final Label[] list;                                                     //list musí být synchronizovaný se sloupcem v enumu Label

    private LabelCategory(Label[] list)  {
        this.list = list;
    }

    public Label[] getList() {
        return list;
    }
    
    public static boolean contains(Label label){
        for (LabelCategory labelCategory : LabelCategory.values()) {
            for (Label label1 : labelCategory.list) {
                if (label1.equals(label)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static LabelCategory containing(Label label){
        for (LabelCategory labelCategory : LabelCategory.values()) {
            for (Label label1 : labelCategory.list) {
                if (label1.equals(label)) {
                    return labelCategory;
                }
            }
        }
        throw new IllegalArgumentException("No LabelCategory found for "+label.getNameRaw());
    }
}
