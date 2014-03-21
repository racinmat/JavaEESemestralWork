<%-- 
    Document   : pridaniPedagoga
    Created on : 21.3.2014, 16:27:04
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
        message="<div>Přidání pedagoga proěhlo úspěšně.</div>";
    }else if(registered.equals("fail")){
        message="<div>Bohužel se přidání pedagoga nezdařilo, zkuste provést přidání znovu nebo kontaktujte administrátora.</div>";
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
    <h1 class="title-header">Pro administrativu</h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">

            <%= message %>
            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <div class="entry-summary"><p>Přidání nového pedagoga</p></div>
                <div>
                    <form action="AddPedagogCheck" method="POST" id="registerForm">

                        <div>    
                            <label for="<%= labelRaw[1] %>"<%= empty[1] %>><%= label[1] %>:</label>
                            <input id="<%= labelRaw[1] %>" type="text" name="<%= labelRaw[1] %>" value="<%= content[1] %>">
                        </div>

                        <div>    
                            <label for="<%= labelRaw[2] %>"<%= empty[2] %>><%= label[2] %>:</label>
                            <input id="<%= labelRaw[2] %>" type="text" name="<%= labelRaw[2] %>" value="<%= content[2] %>">
                        </div>

                        <input type="submit" name="odeslat" value="přidat pedagoga">
                    </form>
                </div>
            </div>


    <%@ include file="/footer.jsp"%>