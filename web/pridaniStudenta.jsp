<%-- 
    Document   : pridaniStudenta
    Created on : 24.3.2014, 22:33:25
    Author     : Azathoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
<%
    session = request.getSession(true);                                         //zpřístupní se session
    String registered="";
    String[] label=Label.getLabelStudent();
    String[] labelRaw=Label.getLabelStudentRaw();
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
        message="<div>Přidání studenta proběhlo úspěšně.</div>";
    }else if(registered.equals("fail")){
        message="<div>Bohužel se přidání studenta nezdařilo, zkuste provést přidání znovu nebo kontaktujte administrátora.</div>";
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
                    <form action="AddStudentCheck" method="POST" id="registerForm">

                        <div>    
                            <label for="<%= labelRaw[1] %>"<%= empty[1] %>><%= label[1] %>:</label>
                            <input id="<%= labelRaw[1] %>" type="text" name="<%= labelRaw[1] %>" value="<%= content[1] %>">
                        </div>
                        <div>    
                            <label for="<%= labelRaw[2] %>"<%= empty[2] %>><%= label[2] %>:</label>
                            <input id="<%= labelRaw[2] %>" type="text" name="<%= labelRaw[2] %>" value="<%= content[2] %>">
                        </div>
                        <div>    
                            <label for="<%= labelRaw[3] %>"<%= empty[3] %>><%= label[3] %>:</label>
                            <input id="<%= labelRaw[3] %>" type="text" name="<%= labelRaw[3] %>" value="<%= content[3] %>">
                        </div>
                        <div>    
                            <label for="<%= labelRaw[4] %>"<%= empty[4] %>><%= label[4] %>:</label>
                            <input id="<%= labelRaw[4] %>" type="text" name="<%= labelRaw[4] %>" value="<%= content[4] %>">
                        </div>
                        <div>    
                            <label for="<%= labelRaw[5] %>"<%= empty[5] %>><%= label[5] %>:</label>
                            <input id="<%= labelRaw[5] %>" type="text" name="<%= labelRaw[5] %>" value="<%= content[5] %>">
                        </div>
                        
                        <input type="submit" name="odeslat" value="přidat pedagoga">
                    </form>
                </div>
            </div>


    <%@ include file="/footer.jsp"%>