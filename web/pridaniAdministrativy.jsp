<%-- 
    Document   : pridaniAdministrativy
    Created on : 24.3.2014, 21:49:22
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
    String[] content=new String[label.length];
    for (int i = 0; i < content.length; i++) {
        content[i]="";
    }
    if(session.getAttribute("formContent")!=null){
        content=(String[]) session.getAttribute("formContent");
    }
    
    if(registered.equals("success")){
        message="<div>Přidání administrativy proběhlo úspěšně.</div>";
    }else if(registered.equals("fail")){
        message="<div>Bohužel se přidání administrativy nezdařilo, zkuste provést přidání znovu nebo kontaktujte administrátora.</div>";
    }
    
    session.setAttribute("registered", null);
    session.setAttribute("formCheck", null);
    session.setAttribute("formContent", null);
    
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

                        <div>    
                            <label for="<%= labelRaw[1] %>"<%= empty[0] %>><%= label[1] %>:</label>
                            <input id="<%= labelRaw[1] %>" type="text" name="<%= labelRaw[1] %>" value="<%= content[0] %>">
                        </div>
                        <div>    
                            <label for="<%= labelRaw[2] %>"<%= empty[1] %>><%= label[2] %>:</label>
                            <input id="<%= labelRaw[2] %>" type="text" name="<%= labelRaw[2] %>" value="<%= content[1] %>">
                        </div>
                        <div>    
                            <label for="<%= labelRaw[9] %>"<%= empty[2] %>><%= label[9] %>:</label>
                            <input id="<%= labelRaw[9] %>" type="text" name="<%= labelRaw[9] %>" value="<%= content[2] %>">
                        </div>
                        <div>    
                            <label for="<%= labelRaw[25] %>"<%= empty[3] %>><%= label[25] %>:</label>
                            <input class="predvolba" type="text" name="<%= "predvolba"+labelRaw[25] %>" value="+420">
                            <input id="<%= labelRaw[25] %>" type="text" name="<%= labelRaw[25] %>" value="<%= content[3] %>">
                        </div>
                        <div>    
                            <label for="<%= labelRaw[43] %>"<%= empty[4] %>><%= label[43] %>:</label>
                            <input class="predvolba" type="text" name="<%= "predvolba"+labelRaw[43] %>" value="+420">
                            <input id="<%= labelRaw[43] %>" type="text" name="<%= labelRaw[43] %>" value="<%= content[4] %>">
                        </div>
                        
                        <input type="submit" name="odeslat" value="přidat pedagoga">
                    </form>
                </div>
            </div>


    <%@ include file="/footer.jsp"%>