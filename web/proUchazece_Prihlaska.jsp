<%-- 
    Document   : proUchazece_Prihlaska
    Created on : 21.2.2014, 14:09:42
    Author     : Azathoth
--%>

<%@page import="enums.SQLTables"%>
<%@page import="java.util.ArrayList"%>
<%@page import="enums.FormularovaSkupina"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
<%
    session = request.getSession(true);                                         //zpřístupní se session
    String registered="";
    if(session.getAttribute("registered")!=null){
        registered=(String) session.getAttribute("registered");                 //zjistí se, jak byl uživatel úspěšný při registraci
    }
    String message="";
    
    HashMap<Label, String> empty=new HashMap<Label, String>();                           //měnit v případě změny počtu položek ve formuláři
    if(session.getAttribute("formCheck")!=null){
        empty=(HashMap<Label, String>) session.getAttribute("formCheck");                     //zjistí se, jak byl uživatel úspěšný při registraci
    }
    HashMap<Label, String> content=new HashMap<Label, String>();
    for (Label label : Label.values()) {
        if((!label.isAutomatickeVyplneni())&&label.isInTables(SQLTables.uchazeci, SQLTables.login)){
            content.put(label, "");
        }
    }
    if(session.getAttribute("formContent")!=null){
        content=(HashMap<Label, String>) session.getAttribute("formContent");
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
                <div style="position: absolute; top:1000px; z-index: -100">
                    <label for="stoletizkousky" style="z-index:-20;">další okno:</label>
                    <input id="stoletizkousky"  style="z-index:-20;" type="text" name="stoletizkousky">
                </div>
                <%
                    ArrayList<Label> list=new ArrayList<Label>();               //kvůli fieldsetu, aby se bral první pouze z labelů, které projdou ifem
                    FormularovaSkupina[] skupina=new FormularovaSkupina[]{FormularovaSkupina.nacionale, FormularovaSkupina.narozeni, FormularovaSkupina.kontakt, FormularovaSkupina.trvalebydliste, FormularovaSkupina.kontaktniudaje, FormularovaSkupina.stredniskola};
                    for(Label label : Label.values()){
                        if((!label.isAutomatickeVyplneni())&&label.isInTables(SQLTables.uchazeci, SQLTables.login)){
                            list.add(label);
                        }
                    }
                    for(Label label : Label.values()){
                        if((!label.isAutomatickeVyplneni())&&label.isInTables(SQLTables.uchazeci, SQLTables.login)){
                            for (int i = 0; i < skupina.length; i++) {
                                if(skupina[i].getFirst(list).equals(label)){
                %>
                            <fieldset>
                                <legend><%= skupina[i].getNazev() %></legend>
                <%
                                }
                            }
                %>
                       <div>
                            <label for="<%= label.getNazevRaw() %>"<%= empty.get(label) %>><%= label.getNazevProUzivatele() %></label>
                <%
                            if(label.isTelefonniCislo()){
                %>
                           <input class="predvolba" type="text" name="<%= "predvolba"+label.getNazevRaw() %>" value="+420">
                <%
                            }
                %>
                            <input id="<%= label.getNazevRaw() %>" type="text" name="<%= label.getNazevRaw() %>" value="<%= content.get(label) %>">
                        </div>
                <%
                            for (int i = 0; i < skupina.length; i++) {
                                if(skupina[i].getLast(list).equals(label)){
                %>
                            </fieldset>
                <%
                                }
                            }
                        }
                    }
                %>
            <input type="submit" name="odeslat" value="odeslat přihlášku">
        </form>
    </div>
                </div>


    <%@ include file="/footer.jsp"%>