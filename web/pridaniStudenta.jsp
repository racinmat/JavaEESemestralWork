<%-- 
    Document   : pridaniStudenta
    Created on : 24.3.2014, 22:33:25
    Author     : Azathoth
--%>

<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="enums.Rights"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
<%
    session = request.getSession(true);                                         //zpřístupní se session
    String registered="";
    if(session.getAttribute("registered")!=null){
        registered=(String) session.getAttribute("registered");                 //zjistí se, jak byl uživatel úspěšný při registraci
    }
    String message="";
    
    ArrayList<LinkedHashMap<Label, String>> listOfStudents=new ArrayList<LinkedHashMap<Label, String>>();
    if(session.getAttribute("newstudent")!=null){
        listOfStudents=(ArrayList<LinkedHashMap<Label, String>>) session.getAttribute("newstudent");
    }
    
    ArrayList<LinkedHashMap<Label, String>> empty=new ArrayList<LinkedHashMap<Label, String>>();
    if(session.getAttribute("formCheck")!=null){
        empty=(ArrayList<LinkedHashMap<Label, String>>) session.getAttribute("formCheck");                     //zjistí se, jak byl uživatel úspěšný při registraci
    }
    
    ArrayList<LinkedHashMap<Label, String>> content=new ArrayList<LinkedHashMap<Label, String>>();
    for (int i = 0; i < listOfStudents.size(); i++) {
        content.add(new LinkedHashMap<Label, String>());
    }
    for (int i = 0; i < content.size(); i++) {
        for (Label label : listOfStudents.get(i).keySet()) {
            content.get(i).put(label, listOfStudents.get(i).get(label));
        }
        for (Label label : Label.values()) {
            if (label.isStudenti()&&!label.isPrimaryKey()) {
                content.get(i).put(label, "");
            }
        }
    }
    
    if(session.getAttribute("formContent")!=null){
        content=(ArrayList<LinkedHashMap<Label, String>>) session.getAttribute("formContent");
    }
    
    if(registered.equals("success")){
        message="<div>Přidání studentů proběhlo úspěšně.</div>";
    }else if(registered.equals("fail")){
        message="<div>Bohužel se přidání studentů nezdařilo, zkuste provést přidání znovu nebo kontaktujte administrátora.</div>";
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
                <div class="entry-summary"><p>Přidání nových studentů</p></div>
                <%
                    if(message.equals("")){
                %>
                <div>
                    <form action="AddStudentCheck" method="POST" id="registerForm">
                        <div>
                            <%
                                for (Label label : content.get(0).keySet()) {
                            %>
                            
                                    <span id="addStudentsLabel">
                                        <%= label.getNazevProUzivatele() %>
                                    </span>
                            <%
                                }
                            %>
                            
                        </div>
                        <%
                            for (int i = 0; i < listOfStudents.size(); i++) {
                        %>
                        <div>
                            <%
                                for (Label label : content.get(i).keySet()) {
                                    if (label.isLogin()) {
                                            
                            %>
                                        <span id="addStudentsLabel">    
                                            <%= content.get(i).get(label) %>
                                        </span>
                            <%
                                    }
                                    else if(label.isStudenti()&&!label.isAutomatickeVyplneni()){
                            %>
                            <span id="addStudents">    
                                <input type="text" name="<%= label.getNazevRaw()+"+"+i %>" value="<%= content.get(i).get(label) %>">
                            </span>
                            
                            <%
                                    }
                                }
                            %>
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