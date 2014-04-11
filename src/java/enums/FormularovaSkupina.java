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
public enum FormularovaSkupina {
    nacionale(      "nacionále",        new Label[]{Label.jmeno, Label.prijmeni, Label.studijniprogram, Label.studijniobor, Label.statniprislusnost, Label.rodinnystav}),
    kontakt(        "kontakt",          new Label[]{Label.email, Label.mobilnitelefon}),
    narozeni(       "narození",         new Label[]{Label.mistonarozeni, Label.okresnarozeni, Label.rodnecislo, Label.cisloOP, Label.cislopasu}),
    trvalebydliste( "trvalé bydliště",  new Label[]{Label.ulice, Label.cislodomu, Label.castobce, Label.obec, Label.okres, Label.psc, Label.stat, Label.telefon, Label.posta}),
    kontaktniudaje( "kontaktní údaje",  new Label[]{Label.kontaktniadresacastobce, Label.kontaktniadresacislodomu, Label.kontaktniadresaobec, Label.kontaktniadresaokres, Label.kontaktniadresaposta, Label.kontaktniadresapsc, Label.kontaktniadresastat, Label.kontaktniadresatelefon, Label.kontaktniadresaulice}),
    stredniskola(   "střední škola",    new Label[]{Label.nazevstredniskoly, Label.adresastredniskoly, Label.oborstredniskoly, Label.jkov, Label.kkov, Label.izo, Label.rokmaturity}),
    zmenaHesla(     "změna hesla",      new Label[]{Label.hashhesla, Label.noveheslo, Label.noveheslokonrola}),
    ;
    
    private String nazev;
    private Label[] obsah;

    private FormularovaSkupina(String nazev, Label[] obsah) {
        this.nazev = nazev;
        this.obsah = obsah;
    }

    public String getNazev() {
        return nazev;
    }

    public Label[] getObsah() {
        return obsah;
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
        for (int i = 0; i < this.obsah.length; i++) {
            if (label.equals(this.obsah[i])) {
                output=true;
            }
        }
        return output;
    }
}
