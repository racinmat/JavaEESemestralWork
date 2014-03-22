<%-- 
    Document   : proUchazece_Prihlaska
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
    
    boolean[] notFilled = new boolean[label.length];
    String[] empty=new String[label.length];
    if(session.getAttribute("formCheck")!=null){
        empty=(String[]) session.getAttribute("formCheck");                     //zjistí se, jak byl uživatel úspěšný při registraci
    }
    String[] content=new String[43];
    for (int i = 0; i < content.length; i++) {
        content[i]="";
    }
    if(session.getAttribute("formContent")!=null){
        content=(String[]) session.getAttribute("formContent");
    }
    
    if(registered.equals("success")){
        message="<div>Přihláška byla úspěšně podána, zanedlouho by vám měl přijít email s přihlašovacími údaji, kde můžete sledovat stav vyřízení své elektronické přihlášky.</div>";
    }else if(registered.equals("fail")){
        message="<div>Bohužel se vyplnění elektronické přihlášky nezdařilo, zkuste ji vyplnit znovu nebo kontaktujte administrátora.</div>";
    }else if(registered.equals("ip")){
        message="<div>Bohužel již bylo dnes z vaší IP adresy odesláno 10 přihlášek. Zkuste podat přihlášku zítra nebo kontaktujte administrátora.</div>";
    }else if(registered.equals("spam")){
        message="<div>Stránky vás vyhodnotily jako robota, zkuste znovu načíst stránku a znovu vyplnit formulář.</div>";
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
    
    session.setAttribute("registered", null);
    session.setAttribute("formCheck", null);
    session.setAttribute("formContent", null);
    
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
            <div>    
                        <label for="<%= labelRaw[1] %>"<%= empty[1] %>><%= label[1] %>:</label>
                        <input id="<%= labelRaw[1] %>" type="text" name="<%= labelRaw[1] %>" value="<%= content[1] %>">
                    </div>
                    <div>    
                        <label for="<%= labelRaw[2] %>"<%= empty[2] %>><%= label[2] %>:</label>
                        <input id="<%= labelRaw[2] %>" type="text" name="<%= labelRaw[2] %>" value="<%= content[2] %>">
                    </div>
                    <div>    
                        <label for="<%= labelRaw[4] %>"<%= empty[4] %>><%= label[4] %>:</label>
                        <input id="<%= labelRaw[4] %>" type="text" name="<%= labelRaw[4] %>" value="<%= content[4] %>">
                    </div>
                    <div>    
                        <label for="<%= labelRaw[5] %>"<%= empty[5] %>><%= label[5] %>:</label>
                        <input id="<%= labelRaw[5] %>" type="text" name="<%= labelRaw[5] %>" value="<%= content[5] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[6] %>"<%= empty[6] %>><%= label[6] %>:</label>
                        <select id="<%= labelRaw[6] %>" name="<%= labelRaw[6] %>">
                            <option value="muž" <%= muz %>>muž</option>
                            <option value="žena" <%= zena %>>žena</option>
                        </select>
                    </div>
                    <div>    
                        <label for="<%= labelRaw[7] %>"<%= empty[7] %>><%= label[7] %>:</label>
                        <input id="<%= labelRaw[7] %>" type="text" name="<%= labelRaw[7] %>" value="<%= content[7] %>">
                    </div>
                    <div>    
                        <label for="<%= labelRaw[8] %>"<%= empty[8] %>><%= label[8] %>:</label>
                        <input id="<%= labelRaw[8] %>" type="text" name="<%= labelRaw[8] %>" value="<%= content[8] %>">
                    </div>
                    <div>    
                        <label for="<%= labelRaw[9] %>"<%= empty[9] %>><%= label[9] %>:</label>
                        <input id="<%= labelRaw[9] %>" type="text" name="<%= labelRaw[9] %>" value="<%= content[9] %>">
                    </div>
                    <div>    
                        <label for="<%= labelRaw[10] %>"<%= empty[10] %>><%= label[10] %>:</label>
                        <input id="<%= labelRaw[10] %>" type="text" name="<%= labelRaw[10] %>" value="<%= content[10] %>">
                    </div>
                    <div>                
                        <label for="<%= labelRaw[11] %>"<%= empty[11] %>><%= label[11] %>:</label>
                        <select id="<%= labelRaw[11] %>" name="<%= labelRaw[11] %>">
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
                        <label for="<%= labelRaw[12] %>"<%= empty[12] %>><%= label[12] %>:</label>
                        <input id="<%= labelRaw[12] %>" type="text" name="<%= labelRaw[12] %>" value="<%= content[12] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[13] %>"<%= empty[13] %>><%= label[13] %>:</label>
                        <input id="<%= labelRaw[13] %>" type="text" name="<%= labelRaw[13] %>" value="<%= content[13] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[14] %>"<%= empty[14] %>><%= label[14] %>:</label>
                        <input id="<%= labelRaw[14] %>" type="text" name="<%= labelRaw[14] %>" value="<%= content[14] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[15] %>"<%= empty[15] %>><%= label[15] %>:</label>
                        <input id="<%= labelRaw[15] %>" type="text" name="<%= labelRaw[15] %>" value="<%= content[15] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[16] %>"<%= empty[16] %>><%= label[16] %>:</label>
                        <input id="<%= labelRaw[16] %>" type="text" name="<%= labelRaw[16] %>" value="<%= content[16] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[17] %>"<%= empty[17] %>><%= label[17] %>:</label>
                        <input id="<%= labelRaw[17] %>" type="text" name="<%= labelRaw[17] %>" value="<%= content[17] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[18] %>"<%= empty[18] %>><%= label[18] %>:</label>
                        <input id="<%= labelRaw[18] %>" type="text" name="<%= labelRaw[18] %>" value="<%= content[18] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[19] %>"<%= empty[19] %>><%= label[19] %>:</label>
                        <input id="<%= labelRaw[19] %>" type="text" name="<%= labelRaw[19] %>" value="<%= content[19] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[20] %>"<%= empty[20] %>><%= label[20] %>:</label>
                        <input id="<%= labelRaw[20] %>" type="text" name="<%= labelRaw[20] %>" value="<%= content[20] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[21] %>"<%= empty[21] %>><%= label[21] %>:</label>
                        <input id="<%= labelRaw[21] %>" type="text" name="<%= labelRaw[21] %>" value="<%= content[21] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[22] %>"<%= empty[22] %>><%= label[22] %>:</label>
                        <input id="<%= labelRaw[22] %>" type="text" name="<%= labelRaw[22] %>" value="<%= content[22] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[23] %>"<%= empty[23] %>><%= label[23] %>:</label>
                        <input id="<%= labelRaw[23] %>" type="text" name="<%= labelRaw[23] %>" value="<%= content[23] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[24] %>"<%= empty[24] %>><%= label[24] %>:</label>
                        <input id="<%= labelRaw[24] %>" type="text" name="<%= labelRaw[24] %>" value="<%= content[24] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[25] %>"<%= empty[25] %>><%= label[25] %>:</label>
                        <input id="<%= labelRaw[25] %>" type="text" name="<%= labelRaw[25] %>" value="<%= content[25] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[26] %>"<%= empty[26] %>><%= label[26] %>:</label>
                        <input id="<%= labelRaw[26] %>" type="text" name="<%= labelRaw[26] %>" value="<%= content[26] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[27] %>"<%= empty[27] %>><%= label[27] %>:</label>
                        <input id="<%= labelRaw[27] %>" type="text" name="<%= labelRaw[27] %>" value="<%= content[27] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[28] %>"<%= empty[28] %>><%= label[28] %>:</label>
                        <input id="<%= labelRaw[28] %>" type="text" name="<%= labelRaw[28] %>" value="<%= content[28] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[29] %>"<%= empty[29] %>><%= label[29] %>:</label>
                        <input id="<%= labelRaw[29] %>" type="text" name="<%= labelRaw[29] %>" value="<%= content[29] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[30] %>"<%= empty[30] %>><%= label[30] %>:</label>
                        <input id="<%= labelRaw[30] %>" type="text" name="<%= labelRaw[30] %>" value="<%= content[30] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[31] %>"<%= empty[31] %>><%= label[31] %>:</label>
                        <input id="<%= labelRaw[31] %>" type="text" name="<%= labelRaw[31] %>" value="<%= content[31] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[32] %>"<%= empty[32] %>><%= label[32] %>:</label>
                        <input id="<%= labelRaw[32] %>" type="text" name="<%= labelRaw[32] %>" value="<%= content[32] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[33] %>"<%= empty[33] %>><%= label[33] %>:</label>
                        <input id="<%= labelRaw[33] %>" type="text" name="<%= labelRaw[33] %>" value="<%= content[33] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[34] %>"<%= empty[34] %>><%= label[34] %>:</label>
                        <input id="<%= labelRaw[34] %>" type="text" name="<%= labelRaw[34] %>" value="<%= content[34] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[35] %>"<%= empty[35] %>><%= label[35] %>:</label>
                        <input id="<%= labelRaw[35] %>" type="text" name="<%= labelRaw[35] %>" value="<%= content[35] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[36] %>"<%= empty[36] %>><%= label[36] %>:</label>
                        <input id="<%= labelRaw[36] %>" type="text" name="<%= labelRaw[36] %>" value="<%= content[36] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[37] %>"<%= empty[37] %>><%= label[37] %>:</label>
                        <input id="<%= labelRaw[37] %>" type="text" name="<%= labelRaw[37] %>" value="<%= content[37] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[38] %>"<%= empty[38] %>><%= label[38] %>:</label>
                        <input id="<%= labelRaw[38] %>" type="text" name="<%= labelRaw[38] %>" value="<%= content[38] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[39] %>"<%= empty[39] %>><%= label[39] %>:</label>
                        <input id="<%= labelRaw[39] %>" type="text" name="<%= labelRaw[39] %>" value="<%= content[39] %>">
                    </div>
                    <div>
                        <label for="<%= labelRaw[40] %>"<%= empty[40] %>><%= label[40] %>:</label>
                        <input id="<%= labelRaw[40] %>" type="text" name="<%= labelRaw[40] %>" value="<%= content[40] %>">
                    </div>
            </fieldset>
            <input type="submit" name="odeslat" value="odeslat přihlášku">
        </form>
    </div>
                </div>


    <%@ include file="/footer.jsp"%>