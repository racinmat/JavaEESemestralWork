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






%>
    <h1 class="title-header">Pro uchazeče</h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">

            <%= message %>
            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
    <div class="entry-summary"><p>Přihlašovací formulář</p></div>
    <div>
        <form action="register" method="POST" id="registerForm">
            <fieldset>
                <legend>nacionále</legend>
            <div>                
            <div style="position: absolute; top:1000px; z-index: -100">
                <label for="stoletizkousky" style="z-index:-20;">další okno:</label>
                <input id="stoletizkousky"  style="z-index:-20;" type="text" name="stoletizkousky">
            </div>
                <label for="jmeno">jméno:</label>
                <input id="jmeno" type="text" name="jmeno">
            </div>
            <div>                
                <label for="prijmeni">příjmení:</label>
                <input id="prijmeni" type="text" name="prijmeni">
            </div>
            <div>
                <label for="studijniprogram">studijní program:</label>
                <input id="studijniprogram" type="text" name="studijniprogram">
            </div>
            <div>                
                <label for="studijniobor">studijní obor:</label>
                <input id="studijniobor" type="text" name="studijniobor">
            </div>
            <div>
                <label for="pohlavi">pohlaví:</label>
                <input id="pohlavi" type="text" name="pohlavi">
            </div>
            <div>                
                <label for="statniprislusnost">státní příslušnost:</label>
                <input id="statniprislusnost" type="text" name="statniprislusnost">
            </div>
            <div>
                <label for="tituly">tituly:</label>
                <input id="tituly" type="text" name="tituly">
            </div>
            <div>                
                <label for="rodinnystav">rodinný stav:</label>
                <input id="rodinnystav" type="text" name="rodinnystav">
            </div>
            </fieldset>
            <fieldset>
                <legend>narození</legend>
            <div>
                <label for="narden">den:</label>
                <input id="narden" type="text" name="narden">
            </div>
            <div>                
                <label for="narmesic">měsíc:</label>
                <input id="narmesic" type="text" name="narmesic">
            </div>
            <div> 
                <label for="narrok">rok:</label>
                <input id="narrok" type="text" name="narrok">
            </div>
            <div>                
                <label for="narmisto">místo:</label>
                <input id="narmisto" type="text" name="narmisto">
            </div>
            <div>                
                <label for="narokres">okres:</label>
                <input id="narokres" type="text" name="narokres">
            </div>
            <div>                
                <label for="cisloOP">číslo občanského průkazu:</label>
                <input id="cisloOP" type="text" name="cisloOP">
            </div>
            <div>
                <label for="rodnecislo">rodné číslo:</label>
                <input id="rodnecislo" type="text" name="rodnecislo">
            </div>
            <div>
                <label for="cislopasu">číslo pasu:</label>
                <input id="cislopasu" type="text" name="cislopasu">
            </div>
            </fieldset>
            <fieldset>
                <legend>adresa trvalého bydliště</legend>
            <div>
                <label for="ulice">ulice:</label>
                <input id="ulice" type="text" name="ulice">
            </div>
            <div>
                <label for="cislodomu">číslo domu:</label>
                <input id="cislodomu" type="text" name="cislodomu">
            </div>
            <div>
                <label for="telefon">telefon:</label>
                <input id="telefon" type="text" name="telefon">
            </div>
            <div>
                <label for="castobce">část obce:</label>
                <input id="castobce" type="text" name="castobce">
            </div>
            <div>
                <label for="obec">obec:</label>
                <input id="obec" type="text" name="obec">
            </div>
            <div>
                <label for="okres">okres:</label>
                <input id="okres" type="text" name="okres">
            </div>
            <div>
                <label for="psc">PSČ:</label>
                <input id="psc" type="text" name="psc">
            </div>
            <div>
                <label for="posta">pošta:</label>
                <input id="posta" type="text" name="posta">
            </div>
            <div>
                <label for="stat">stát:</label>
                <input id="stat" type="text" name="stat">
            </div>
            </fieldset>
            <fieldset>
                <legend>kontaktní adresa</legend>
            <div>
                <label for="kulice">ulice:</label>
                <input id="kulice" type="text" name="kulice">
            </div>
            <div>
                <label for="kcislodomu">číslo domu:</label>
                <input id="kcislodomu" type="text" name="kcislodomu">
            </div>
            <div>
                <label for="ktelefon">telefon:</label>
                <input id="ktelefon" type="text" name="ktelefon">
            </div>
            <div>
                <label for="kcastobce">část obce:</label>
                <input id="kcastobce" type="text" name="kcastobce">
            </div>
            <div>
                <label for="kobec">obec:</label>
                <input id="kobec" type="text" name="kobec">
            </div>
            <div>
                <label for="kokres">okres:</label>
                <input id="kokres" type="text" name="kokres">
            </div>
            <div>
                <label for="kpsc">PSČ:</label>
                <input id="kpsc" type="text" name="kpsc">
            </div>
            <div>
                <label for="kposta">pošta:</label>
                <input id="kposta" type="text" name="kposta">
            </div>
            <div>
                <label for="kstat">stát:</label>
                <input id="kstat" type="text" name="kstat">
            </div>
            </fieldset>
            <fieldset>
                <legend>střední škola (absolvovaná nebo studovaná)</legend>
            <div>
                <label for="ssnazev">název:</label>
                <input id="ssnazev" type="text" name="ssnazev">
            </div>
            <div>
                <label for="ssadresa">adresa:</label>
                <input id="ssadresa" type="text" name="ssadresa">
            </div>
            <div>
                <label for="ssobor">obor-název:</label>
                <input id="ssobor" type="text" name="ssobor">
            </div>
            <div>
                <label for="jkov">JKOV:</label>
                <input id="jkov" type="text" name="jkov">
            </div>
            <div>
                <label for="kkov">KKOV:</label>
                <input id="kkov" type="text" name="kkov">
            </div>
            <div>
                <label for="izo">IZO:</label>
                <input id="izo" type="text" name="izo">
            </div>
            <div>
                <label for="rokmaturity">rok maturitní zkoušky:</label>
                <input id="rokmaturity" type="text" name="rokmaturity">
            </div>
            </fieldset>
            <input type="submit" name="odeslat" value="odeslat přihlášku">
        </form>
    </div>
                </div>


    <%@ include file="/footer.jsp"%>