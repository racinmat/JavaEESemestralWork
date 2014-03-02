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
    int[] notFilled = new int[43];
    String[] empty=new String[43];
    if(session.getAttribute("formCheck")!=null){
        notFilled=(int[]) session.getAttribute("formCheck");                    //zjistí se, jak byl uživatel úspěšný při registraci
        for (int i = 2; i < notFilled.length; i++) {
            if (notFilled[i]==1) {
                empty[i]=" style=\"font-weight: bolder;color: red\"";
            }
            else{
                empty[i]="";
            }
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
                <input id="jmeno" type="text" name="jmeno">
            </div>
            <div>                
                <label for="prijmeni"<%= empty[3] %>>příjmení:</label>
                <input id="prijmeni" type="text" name="prijmeni">
            </div>
            <div>
                <label for="studijniprogram"<%= empty[4] %>>studijní program:</label>
                <input id="studijniprogram" type="text" name="studijniprogram">
            </div>
            <div>                
                <label for="studijniobor"<%= empty[5] %>>studijní obor:</label>
                <input id="studijniobor" type="text" name="studijniobor">
            </div>
            <div>
                <label for="pohlavi"<%= empty[6] %>>pohlaví:</label>
                <input id="pohlavi" type="text" name="pohlavi">
            </div>
            <div>                
                <label for="statniprislusnost"<%= empty[7] %>>státní příslušnost:</label>
                <input id="statniprislusnost" type="text" name="statniprislusnost">
            </div>
            <div>
                <label for="tituly"<%= empty[8] %>>tituly:</label>
                <input id="tituly" type="text" name="tituly">
            </div>
            <div>                
                <label for="rodinnystav"<%= empty[9] %>>rodinný stav:</label>
                <input id="rodinnystav" type="text" name="rodinnystav">
            </div>
            </fieldset>
            <fieldset>
                <legend>narození</legend>
            <div>
                <label for="narden"<%= empty[10] %>>den:</label>
                <input id="narden" type="text" name="narden">
            </div>
            <div>                
                <label for="narmesic"<%= empty[11] %>>měsíc:</label>
                <input id="narmesic" type="text" name="narmesic">
            </div>
            <div> 
                <label for="narrok"<%= empty[12] %>>rok:</label>
                <input id="narrok" type="text" name="narrok">
            </div>
            <div>                
                <label for="narmisto"<%= empty[13] %>>místo:</label>
                <input id="narmisto" type="text" name="narmisto">
            </div>
            <div>                
                <label for="narokres"<%= empty[14] %>>okres:</label>
                <input id="narokres" type="text" name="narokres">
            </div>
            <div>                
                <label for="cisloOP"<%= empty[15] %>>číslo občanského průkazu:</label>
                <input id="cisloOP" type="text" name="cisloOP">
            </div>
            <div>
                <label for="rodnecislo"<%= empty[16] %>>rodné číslo:</label>
                <input id="rodnecislo" type="text" name="rodnecislo">
            </div>
            <div>
                <label for="cislopasu"<%= empty[17] %>>číslo pasu:</label>
                <input id="cislopasu" type="text" name="cislopasu">
            </div>
            </fieldset>
            <fieldset>
                <legend>adresa trvalého bydliště</legend>
            <div>
                <label for="ulice"<%= empty[18] %>>ulice:</label>
                <input id="ulice" type="text" name="ulice">
            </div>
            <div>
                <label for="cislodomu"<%= empty[19] %>>číslo domu:</label>
                <input id="cislodomu" type="text" name="cislodomu">
            </div>
            <div>
                <label for="telefon"<%= empty[20] %>>telefon:</label>
                <input id="telefon" type="text" name="telefon">
            </div>
            <div>
                <label for="castobce"<%= empty[21] %>>část obce:</label>
                <input id="castobce" type="text" name="castobce">
            </div>
            <div>
                <label for="obec"<%= empty[22] %>>obec:</label>
                <input id="obec" type="text" name="obec">
            </div>
            <div>
                <label for="okres"<%= empty[23] %>>okres:</label>
                <input id="okres" type="text" name="okres">
            </div>
            <div>
                <label for="psc"<%= empty[24] %>>PSČ:</label>
                <input id="psc" type="text" name="psc">
            </div>
            <div>
                <label for="posta"<%= empty[25] %>>pošta:</label>
                <input id="posta" type="text" name="posta">
            </div>
            <div>
                <label for="stat"<%= empty[26] %>>stát:</label>
                <input id="stat" type="text" name="stat">
            </div>
            </fieldset>
            <fieldset>
                <legend>kontaktní adresa</legend>
            <div>
                <label for="kulice"<%= empty[27] %>>ulice:</label>
                <input id="kulice" type="text" name="kulice">
            </div>
            <div>
                <label for="kcislodomu"<%= empty[28] %>>číslo domu:</label>
                <input id="kcislodomu" type="text" name="kcislodomu">
            </div>
            <div>
                <label for="ktelefon"<%= empty[29] %>>telefon:</label>
                <input id="ktelefon" type="text" name="ktelefon">
            </div>
            <div>
                <label for="kcastobce"<%= empty[30] %>>část obce:</label>
                <input id="kcastobce" type="text" name="kcastobce">
            </div>
            <div>
                <label for="kobec"<%= empty[31] %>>obec:</label>
                <input id="kobec" type="text" name="kobec">
            </div>
            <div>
                <label for="kokres"<%= empty[32] %>>okres:</label>
                <input id="kokres" type="text" name="kokres">
            </div>
            <div>
                <label for="kpsc"<%= empty[33] %>>PSČ:</label>
                <input id="kpsc" type="text" name="kpsc">
            </div>
            <div>
                <label for="kposta"<%= empty[34] %>>pošta:</label>
                <input id="kposta" type="text" name="kposta">
            </div>
            <div>
                <label for="kstat"<%= empty[35] %>>stát:</label>
                <input id="kstat" type="text" name="kstat">
            </div>
            </fieldset>
            <fieldset>
                <legend>střední škola (absolvovaná nebo studovaná)</legend>
            <div>
                <label for="ssnazev"<%= empty[36] %>>název:</label>
                <input id="ssnazev" type="text" name="ssnazev">
            </div>
            <div>
                <label for="ssadresa"<%= empty[37] %>>adresa:</label>
                <input id="ssadresa" type="text" name="ssadresa">
            </div>
            <div>
                <label for="ssobor"<%= empty[38] %>>obor-název:</label>
                <input id="ssobor" type="text" name="ssobor">
            </div>
            <div>
                <label for="jkov"<%= empty[39] %>>JKOV:</label>
                <input id="jkov" type="text" name="jkov">
            </div>
            <div>
                <label for="kkov"<%= empty[40] %>>KKOV:</label>
                <input id="kkov" type="text" name="kkov">
            </div>
            <div>
                <label for="izo"<%= empty[41] %>>IZO:</label>
                <input id="izo" type="text" name="izo">
            </div>
            <div>
                <label for="rokmaturity"<%= empty[42] %>>rok maturitní zkoušky:</label>
                <input id="rokmaturity" type="text" name="rokmaturity">
            </div>
            </fieldset>
            <input type="submit" name="odeslat" value="odeslat přihlášku">
        </form>
    </div>
                </div>


    <%@ include file="/footer.jsp"%>