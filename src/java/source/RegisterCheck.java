/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Azathoth
 */
public class RegisterCheck extends HttpServlet {

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
        int plusposition=str.indexOf("+");                                      //pozice plusu z předvolby
        if (plusposition>0){
            return true;                                                        //pokud neprojde validace, nastaví se error na false
        }
        if (notNumeric(str.substring(1))) {
            return true;
        }
        if (str.length()>13||str.length()<11) {
            return true;
        }
        return false;
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");                              //nastavení na utf 8, jinak se znaky z formuláře špatně přečtou
            String[] labelRaw=Label.getLabelRaw();
            String[] notFilled = new String[labelRaw.length-2];
            for (int i = 0; i < notFilled.length; i++) {
                notFilled[i]="";
            }
            boolean error=false;
            String notFilledStyle=" class=\"notFilled\"";
            
            String[] input = new String[labelRaw.length-2];
            for (int i = 1; i < labelRaw.length-2; i++) {
                if (i==3||i==10||i==11||i==12) {                                //políčka s tímto číslem labelu nejsou ve formuláři
                    
                }else {
                    input[i]=request.getParameter(labelRaw[i]);
                }
            }
            
            if (notNumeric(input[13])&&notNumeric(input[15])) {                 //pokud je alespoň jedno z nich v pořádku, pak je druhé nepovinné
                notFilled[13]=notFilledStyle;                                             //pokud je notFilled true, pak je v daném políčku chyba
                notFilled[15]=notFilledStyle;                                             //pokud je notFilled true, pak je v daném políčku chyba
                error=true;
            }
            
            if (notValidBirthNumber(input[14])) {
                notFilled[14]=notFilledStyle;                                             //pokud je notFilled true, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[19])) {
                notFilled[19]=notFilledStyle;                                             //pokud je notFilled true, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[23])) {
                notFilled[23]=notFilledStyle;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if (notValidPhoneNumber(request.getParameter("predvolba"+labelRaw[25]),input[25])) {
                notFilled[25]=notFilledStyle;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if ((!input[28].equals(""))&&notNumeric(input[28])) {
                notFilled[28]=notFilledStyle;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if ((!input[32].equals(""))&&notNumeric(input[32])) {
                notFilled[32]=notFilledStyle;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if ((!input[34].equals(""))&&notValidPhoneNumber(request.getParameter("predvolba"+labelRaw[34]),input[34])) {
                notFilled[34]=notFilledStyle;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if (notNumeric(input[42])) {
                notFilled[42]=notFilledStyle;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            if (notValidPhoneNumber(request.getParameter("predvolba"+labelRaw[43]),input[43])) {
                notFilled[43]=notFilledStyle;                                             //pokud je notFilled 1, pak je v daném políčku chyba
                error=true;
            }
            
            if (!notValidBirthNumber(input[14])) {
                input[10]=getBirthDay(input[14]);                                   //z rodného čísla
                input[11]=getBirthMonth(input[14]);
                input[12]=getBirthYear(input[14]);
            } else {
                input[10]="";
                input[11]="";
                input[12]="";
            }
            
            for (int i = 1; i < notFilled.length; i++) {                        //testování prázdnosti vyplněných polí
                if (i!=3&&input[i].equals("")) {                                //3 je pro heslo, to se nezadává
                    if (i!=15&&i!=13&&(i<27||i>35)) {                             //políčka 27 až 35 jsou nepovinná, pokud jsou přízdná, nakopíruje se do nich hodnota z trvalého bydliště
                        notFilled[i]=notFilledStyle;                            //pokud je notFilled true, pak je v daném políčku chyba
                        error=true;                                             //políčko 3 je heslo a políčka 13 a 15 jsou OP a pas, výše je validace, azda je aspoň jeden z nich správně
                    }
                }
            }
            if (notValidEmail(input[9])){
                notFilled[9]=notFilledStyle;
                error=true;                                                     //pokud neprojde validace, nastaví se error na false
            }

            for (int i = 27; i <= 35; i++) {                                    //kontaktní údaje, nepovinné, pokud nevyplněny, předají se údaje z trvalého bydliště
                if (input[i].equals("")) {
                    input[i]=input[i-9];
                }
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("formContent", input);
            session.setAttribute("formCheck", notFilled);
                        
            if (error) {
                
                response.sendRedirect("proUchazece_Prihlaska.jsp");
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register");
                dispatcher.forward(request, response);
            }
        } catch (ServletException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
