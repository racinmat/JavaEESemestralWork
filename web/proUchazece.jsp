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
    String[] label=Label.getLabel();
    String[] labelRaw=Label.getLabelRaw();
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
    
    boolean[] notFilled = new boolean[label.length];
    String[] empty=new String[label.length];
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
        for (int i = 0; i < mesic.length; i++) {
            if(mesicCislo==i){
                mesic[i]="selected=\"selected\"";
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
            <div style="position: absolute; top:1000px; z-index: -100">
                <label for="stoletizkousky" style="z-index:-20;">další okno:</label>
                <input id="stoletizkousky"  style="z-index:-20;" type="text" name="stoletizkousky">
            </div>
            <%
                for (int i = 1; i < label.length-2; i++) {                      //-2 protože poslední dvě položky uchazeč nebude vyplňovat, vyplní se samy předdefinovaným stringem
                    if(i==3){
                        
                    }
                    else if(i==6){
            %>
                    <div>
                        <label for="pohlavi"<%= empty[i] %>>pohlaví:</label>
                        <select id="pohlavi" name="pohlavi">
                            <option value="muž" <%= muz %>>muž</option>
                            <option value="žena" <%= zena %>>žena</option>
                        </select>
                    </div>
            <%
                    }
                    else if(i==11){
            %>
                    <div>                
                        <label for="<%= labelRaw[i] %>"<%= empty[i] %>><%= label[i] %>:</label>
                        <select id="<%= labelRaw[i] %>" name="<%= labelRaw[i] %>">
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
            <%            
                    } else {
            %>
                    <div>    
                        <label for="<%= labelRaw[i] %>"<%= empty[i] %>><%= label[i] %>:</label>
                        <input id="<%= labelRaw[i] %>" type="text" name="<%= labelRaw[i] %>" value="<%= content[i] %>">
                    </div>
            <%
                    }
                }
            %>
            </fieldset>
            <input type="submit" name="odeslat" value="odeslat přihlášku">
        </form>
    </div>
                </div>


    <%@ include file="/footer.jsp"%>