<%-- 
    Document   : proUchazece
    Created on : 21.2.2014, 14:09:42
    Author     : Azathoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
<%
    session = request.getSession(true);                                         //zpřístupní se session
    String registered="";
    if(session.getAttribute("registered")!=null){
        registered=(String) session.getAttribute("registered");                 //zjistí se, jak byl uživatel úspěšný při registraci
    }
    String message="";
    if(registered.equals("success")){
        message="<div>Přihláška byla úspěšně podána, zanedlouho by vám měl přijít email s přihlašovacími údaji, kde můžete sledovat stav vyřízení své elektronické přihlášky</div>";
    }else if(registered.equals("fail")){
        message="<div>Bohužel se vyplnění elektronické přihlášky nezdařilo, zkuste ji vyplnit znovu nebo kontaktujte administrátora.</div>";
    }else if(registered.equals("ip")){
        message="<div>Bohužel již bylo dnes z vaší IP adresy odesláno 10 přihlšek. Zkuste podat přihlášku zítra nebo kontaktujte administrátora.</div>";
    }else if(registered.equals("spam")){
        message="<div>Stránky vás vyhodnotily jako robota, zkuste znovu načíst stránku a znovu vyplnit formulář.</div>";
    }
    
    boolean[] notFilled = new boolean[43];
    String[] empty=new String[43];
    if(session.getAttribute("formCheck")!=null){
        notFilled=(boolean[]) session.getAttribute("formCheck");                    //zjistí se, jak byl uživatel úspěšný při registraci
        for (int i = 2; i < notFilled.length; i++) {
            if (notFilled[i]) {
                empty[i]=" style=\"font-weight: bolder;color: red\"";
            }
            else{
                empty[i]="";
            }
        }
    }
    String[] content=new String[43];
    for (int i = 0; i < content.length; i++) {
            content[i]="";
        }
    if(session.getAttribute("formContent")!=null){
        content=(String[]) session.getAttribute("formContent");
    }
    
    String muz="";                                                                 //u selectu je třeba to ošetřit zvlášť pro každou možnost
    String zena="";
    if(content[6].equals("muž")){
        muz="selected=\"selected\"";
    } else if(content[6].equals("žena")){
        zena="selected=\"selected\"";
    }
    
    String[] mesic=new String[12];
    for (int i = 0; i < mesic.length; i++) {
        mesic[i]="";
    }
    if(!content[11].equals("")){
        int mesicCislo=Integer.parseInt(content[11]);
        switch(mesicCislo){
            case 1:mesic[0]="selected=\"selected\"";break;
            case 2:mesic[1]="selected=\"selected\"";break;
            case 3:mesic[2]="selected=\"selected\"";break;
            case 4:mesic[3]="selected=\"selected\"";break;
            case 5:mesic[4]="selected=\"selected\"";break;
            case 6:mesic[5]="selected=\"selected\"";break;
            case 7:mesic[6]="selected=\"selected\"";break;
            case 8:mesic[7]="selected=\"selected\"";break;
            case 9:mesic[8]="selected=\"selected\"";break;
            case 10:mesic[9]="selected=\"selected\"";break;
            case 11:mesic[10]="selected=\"selected\"";break;
            case 12:mesic[11]="selected=\"selected\"";break;
        }
    }
    



%>
    <h1 class="title-header">Pro uchazeče</h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">

            <%= message %>
            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
    <div class="entry-summary"><p>Přihlašovací formulář</p></div>
    <div>
        <form action="registercheck" method="POST" id="registerForm">
            <fieldset>
                <legend>nacionále</legend>
            <div>                
            <div style="position: absolute; top:1000px; z-index: -100">
                <label for="stoletizkousky" style="z-index:-20;">další okno:</label>
                <input id="stoletizkousky"  style="z-index:-20;" type="text" name="stoletizkousky">
            </div>
                <label for="jmeno"<%= empty[2] %>>jméno:</label>
                <input id="jmeno" type="text" name="jmeno" value="<%= content[2] %>">
            </div>
            <div>                
                <label for="prijmeni"<%= empty[3] %>>příjmení:</label>
                <input id="prijmeni" type="text" name="prijmeni" value="<%= content[3] %>">
            </div>
            <div>
                <label for="studijniprogram"<%= empty[4] %>>studijní program:</label>
                <input id="studijniprogram" type="text" name="studijniprogram" value="<%= content[4] %>">
            </div>
            <div>                
                <label for="studijniobor"<%= empty[5] %>>studijní obor:</label>
                <input id="studijniobor" type="text" name="studijniobor" value="<%= content[5] %>">
            </div>
            <div>
                <label for="pohlavi"<%= empty[6] %>>pohlaví:</label>
                <select id="pohlavi" name="pohlavi">
                    <option value="muž" <%= muz %>>muž</option>
                    <option value="žena" <%= zena %>>žena</option>
                </select>
            </div>
            <div>                
                <label for="statniprislusnost"<%= empty[7] %>>státní příslušnost:</label>
                <input id="statniprislusnost" type="text" name="statniprislusnost" value="<%= content[7] %>">
            </div>
            <div>
                <label for="email"<%= empty[8] %>>email:</label>
                <input id="email" type="text" name="email" value="<%= content[8] %>">
            </div>
            <div>                
                <label for="rodinnystav"<%= empty[9] %>>rodinný stav:</label>
                <input id="rodinnystav" type="text" name="rodinnystav" value="<%= content[9] %>">
            </div>
            </fieldset>
            <fieldset>
                <legend>narození</legend>
            <div>
                <label for="narden"<%= empty[10] %>>den:</label>
                <input id="narden" type="text" name="narden" value="<%= content[10] %>">
            </div>
            <div>                
                <label for="narmesic"<%= empty[11] %>>měsíc:</label>
                <select id="narmesic" name="narmesic">
                    <option value="1" <%= mesic[0] %>>leden</option>
                    <option value="2" <%= mesic[1] %>>únor</option>
                    <option value="3" <%= mesic[2] %>>březen</option>
                    <option value="4" <%= mesic[3] %>>duben</option>
                    <option value="5" <%= mesic[4] %>>květen</option>
                    <option value="6" <%= mesic[5] %>>červen</option>
                    <option value="7" <%= mesic[6] %>>červenec</option>
                    <option value="8" <%= mesic[7] %>>srpen</option>
                    <option value="9" <%= mesic[8] %>>září</option>
                    <option value="10" <%= mesic[9] %>>říjen</option>
                    <option value="11" <%= mesic[10] %>>listopad</option>
                    <option value="12" <%= mesic[11] %>>prosinec</option>
                </select>
            </div>
            <div> 
                <label for="narrok"<%= empty[12] %>>rok:</label>
                <input id="narrok" type="text" name="narrok" value="<%= content[12] %>">
            </div>
            <div>                
                <label for="narmisto"<%= empty[13] %>>místo:</label>
                <input id="narmisto" type="text" name="narmisto" value="<%= content[13] %>">
            </div>
            <div>                
                <label for="narokres"<%= empty[14] %>>okres:</label>
                <input id="narokres" type="text" name="narokres" value="<%= content[14] %>">
            </div>
            <div>                
                <label for="cisloOP"<%= empty[15] %>>číslo občanského průkazu:</label>
                <input id="cisloOP" type="text" name="cisloOP" value="<%= content[15] %>">
            </div>
            <div>
                <label for="rodnecislo"<%= empty[16] %>>rodné číslo:</label>
                <input id="rodnecislo" type="text" name="rodnecislo" value="<%= content[16] %>">
            </div>
            <div>
                <label for="cislopasu"<%= empty[17] %>>číslo pasu:</label>
                <input id="cislopasu" type="text" name="cislopasu" value="<%= content[17] %>">
            </div>
            </fieldset>
            <fieldset>
                <legend>adresa trvalého bydliště</legend>
            <div>
                <label for="ulice"<%= empty[18] %>>ulice:</label>
                <input id="ulice" type="text" name="ulice" value="<%= content[18] %>">
            </div>
            <div>
                <label for="cislodomu"<%= empty[19] %>>číslo domu:</label>
                <input id="cislodomu" type="text" name="cislodomu" value="<%= content[19] %>">
            </div>
            <div>
                <label for="telefon"<%= empty[20] %>>telefon:</label>
                <input id="telefon" type="text" name="telefon" value="<%= content[20] %>">
            </div>
            <div>
                <label for="castobce"<%= empty[21] %>>část obce:</label>
                <input id="castobce" type="text" name="castobce" value="<%= content[21] %>">
            </div>
            <div>
                <label for="obec"<%= empty[22] %>>obec:</label>
                <input id="obec" type="text" name="obec" value="<%= content[22] %>">
            </div>
            <div>
                <label for="okres"<%= empty[23] %>>okres:</label>
                <input id="okres" type="text" name="okres" value="<%= content[23] %>">
            </div>
            <div>
                <label for="psc"<%= empty[24] %>>PSČ:</label>
                <input id="psc" type="text" name="psc" value="<%= content[24] %>">
            </div>
            <div>
                <label for="posta"<%= empty[25] %>>pošta:</label>
                <input id="posta" type="text" name="posta" value="<%= content[25] %>">
            </div>
            <div>
                <label for="stat"<%= empty[26] %>>stát:</label>
                <input id="stat" type="text" name="stat" value="<%= content[26] %>">
            </div>
            </fieldset>
            <fieldset>
                <legend>kontaktní adresa</legend>
            <div>
                <label for="kulice"<%= empty[27] %>>ulice:</label>
                <input id="kulice" type="text" name="kulice" value="<%= content[27] %>">
            </div>
            <div>
                <label for="kcislodomu"<%= empty[28] %>>číslo domu:</label>
                <input id="kcislodomu" type="text" name="kcislodomu" value="<%= content[28] %>">
            </div>
            <div>
                <label for="ktelefon"<%= empty[29] %>>telefon:</label>
                <input id="ktelefon" type="text" name="ktelefon" value="<%= content[29] %>">
            </div>
            <div>
                <label for="kcastobce"<%= empty[30] %>>část obce:</label>
                <input id="kcastobce" type="text" name="kcastobce" value="<%= content[30] %>">
            </div>
            <div>
                <label for="kobec"<%= empty[31] %>>obec:</label>
                <input id="kobec" type="text" name="kobec" value="<%= content[31] %>">
            </div>
            <div>
                <label for="kokres"<%= empty[32] %>>okres:</label>
                <input id="kokres" type="text" name="kokres" value="<%= content[32] %>">
            </div>
            <div>
                <label for="kpsc"<%= empty[33] %>>PSČ:</label>
                <input id="kpsc" type="text" name="kpsc" value="<%= content[33] %>">
            </div>
            <div>
                <label for="kposta"<%= empty[34] %>>pošta:</label>
                <input id="kposta" type="text" name="kposta" value="<%= content[34] %>">
            </div>
            <div>
                <label for="kstat"<%= empty[35] %>>stát:</label>
                <input id="kstat" type="text" name="kstat" value="<%= content[35] %>">
            </div>
            </fieldset>
            <fieldset>
                <legend>střední škola (absolvovaná nebo studovaná)</legend>
            <div>
                <label for="ssnazev"<%= empty[36] %>>název:</label>
                <input id="ssnazev" type="text" name="ssnazev" value="<%= content[36] %>">
            </div>
            <div>
                <label for="ssadresa"<%= empty[37] %>>adresa:</label>
                <input id="ssadresa" type="text" name="ssadresa" value="<%= content[37] %>">
            </div>
            <div>
                <label for="ssobor"<%= empty[38] %>>obor-název:</label>
                <input id="ssobor" type="text" name="ssobor" value="<%= content[38] %>">
            </div>
            <div>
                <label for="jkov"<%= empty[39] %>>JKOV:</label>
                <input id="jkov" type="text" name="jkov" value="<%= content[39] %>">
            </div>
            <div>
                <label for="kkov"<%= empty[40] %>>KKOV:</label>
                <input id="kkov" type="text" name="kkov" value="<%= content[40] %>">
            </div>
            <div>
                <label for="izo"<%= empty[41] %>>IZO:</label>
                <input id="izo" type="text" name="izo" value="<%= content[41] %>">
            </div>
            <div>
                <label for="rokmaturity"<%= empty[42] %>>rok maturitní zkoušky:</label>
                <input id="rokmaturity" type="text" name="rokmaturity" value="<%= content[42] %>">
            </div>
            </fieldset>
            <input type="submit" name="odeslat" value="odeslat přihlášku">
        </form>
    </div>
                </div>


    <%@ include file="/footer.jsp"%>