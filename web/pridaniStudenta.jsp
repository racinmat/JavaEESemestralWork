<%-- 
    Document   : pridaniStudenta
    Created on : 24.3.2014, 22:33:25
    Author     : Azathoth
--%>

<%@page import="java.util.ArrayList"%>
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
    ArrayList<String[]> listOfStudents=new ArrayList<String[]>();
    if(session.getAttribute("newstudent")!=null){
        listOfStudents=(ArrayList<String[]>) session.getAttribute("newstudent");
    }
    
    String[][] empty=new String[listOfStudents.size()][label.length];
    if(session.getAttribute("formCheck")!=null){
        empty=(String[][]) session.getAttribute("formCheck");                     //zjistí se, jak byl uživatel úspěšný při registraci
    }
    
    String[][] content=new String[listOfStudents.size()][label.length];
    for (int i = 0; i < content.length; i++) {
        content[i][0]=listOfStudents.get(i)[0];
        content[i][1]=listOfStudents.get(i)[1];
        content[i][2]=listOfStudents.get(i)[2];
        for (int j = 3; j < content[0].length; j++) {
            content[i][j]="";
        }
    }

    if(session.getAttribute("formContent")!=null){
        content=(String[][]) session.getAttribute("formContent");
    }
    
    if(registered.equals("success")){
        message="<div>Přidání studentů proběhlo úspěšně.</div>";
    }else if(registered.equals("fail")){
        message="<div>Bohužel se přidání studentů nezdařilo, zkuste provést přidání znovu nebo kontaktujte administrátora.</div>";
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
                <div class="entry-summary"><p>Přidání nových studentů</p></div>
                <%
                    if(message.equals("")){
                %>
                <div>
                    <form action="AddStudentCheck" method="POST" id="registerForm">
                        <div>
                            <span id="addStudentsLabel">
                                <%= label[0] %>
                            </span>
                            <span id="addStudentsLabel">
                                <%= label[1] %>
                            </span>
                            <span id="addStudentsLabel">   
                                <%= label[2] %>
                            </span>
                            <span id="addStudentsLabel">   
                                <%= label[4] %>
                            </span>
                            <span id="addStudentsLabel">   
                                <%= label[5] %>
                            </span>
                            <span id="addStudentsLabel">    
                                <%= label[6] %>
                            </span>
                        </div>
                        <%
                            for (int i = 0; i < listOfStudents.size(); i++) {
                        %>
                        <div>
                            <span id="addStudentsLabel">    
                                <%= content[i][0] %>
                            </span>
                            <span id="addStudentsLabel">    
                                <%= content[i][1] %>
                            </span>
                            <span id="addStudentsLabel">    
                                <%= content[i][2] %>
                            </span>
                            <span id="addStudents">    
                                <input id="<%= labelRaw[4]+"+"+i %>" type="text" name="<%= labelRaw[4]+"+"+i %>" value="<%= content[i][4] %>">
                            </span>
                            <span id="addStudents">    
                                <input id="<%= labelRaw[5]+"+"+i %>" type="text" name="<%= labelRaw[5]+"+"+i %>" value="<%= content[i][5] %>">
                            </span>
                            <span id="addStudents">    
                                <input id="<%= labelRaw[6]+"+"+i %>" type="text" name="<%= labelRaw[6]+"+"+i %>" value="<%= content[i][6] %>">
                            </span>
                        </div>
                        <%
                            }
                        %>    
                        
                        <input type="submit" name="odeslat" value="přidat studenty">
                    </form>
                </div>
                <%
                    }
                %>
                
            </div>


    <%@ include file="/footer.jsp"%>