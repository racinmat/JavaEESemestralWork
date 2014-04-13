<%-- 
    Document   : pridaniAdministrativy
    Created on : 24.3.2014, 21:49:22
    Author     : Azathoth
--%>

<%@page import="java.util.HashMap"%>
<%@page import="enums.SQLTables"%>
<%@page import="enums.Rights"%>
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
        if(label.isInTables(SQLTables.login, SQLTables.administrativa)){
            content.put(label, "");
        }
    }
    if(session.getAttribute("formContent")!=null){
        content=(HashMap<Label, String>) session.getAttribute("formContent");
    }
    
    if(registered.equals("success")){
        message="<div>Přidání administrativy proběhlo úspěšně.</div>";
    }else if(registered.equals("fail")){
        message="<div>Bohužel se přidání administrativy nezdařilo, zkuste provést přidání znovu nebo kontaktujte administrátora.</div>";
    }
    
    session.setAttribute("registered", null);
    session.setAttribute("formCheck", null);
    session.setAttribute("formContent", null);
    
    security.accesedTo(Rights.administrativa, response);
%>
    <h1 class="title-header">Pro administrativu</h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">

            <%= message %>
            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <div class="entry-summary"><p>Přidání nové administrativy</p></div>
                <div>
                    <form action="AddAdministrativaCheck" method="POST" id="registerForm">

                        <%
                            for (Label label : Label.values()) {
                                if(label.isInTables(SQLTables.login, SQLTables.administrativa)&&!label.isAutoFill()){
                        %>
                            <div>    
                                <label for="<%= label.getNameRaw() %>"<%= empty.get(label) %>><%= label.getNameForUsers() %>:</label>
                        <%
                            if(label.isPhonenumber()){
                        %>
                           <input class="predvolba" type="text" name="<%= "predvolba"+label.getNameRaw() %>" value="+420">
                        <%
                            }
                        %>
                                <input id="<%= label.getNameRaw() %>" type="text" name="<%= label.getNameRaw() %>" value="<%= content.get(label) %>">
                            </div>
                        <%
                                }
                            }
                        %>
                        <input type="submit" name="odeslat" value="přidat administrativu">
                    </form>
                </div>
            </div>


    <%@ include file="/footer.jsp"%>