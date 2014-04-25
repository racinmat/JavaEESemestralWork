<%-- 
    Document   : proPrihlasene
    Created on : 19.3.2014, 18:37:50
    Author     : Azathoth
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="enums.Rights"%>
<%@page import="enums.FormGroup"%>
<%@page import="java.util.HashMap"%>
<%@page import="enums.SQLTable"%>
<%@page import="source.Mysql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
    <%
        Map<Label, String> empty=new HashMap<Label, String>();                           //měnit v případě změny počtu položek ve formuláři
        Mysql sql=new Mysql();
        if(session.getAttribute("formCheck")!=null){
            empty=(HashMap<Label, String>) session.getAttribute("formCheck");   //zjistí se, jak byl uživatel úspěšný při registraci
        }
        String registered="";
        if(session.getAttribute("registered")!=null){
            registered=(String) session.getAttribute("registered");             //zjistí se, jak byl uživatel úspěšný při registraci
        }
        
        String message="";
        if(registered.equals("success")){
            message="<div>Údaje byly úspěšně změněny.</div>";
        }else if(registered.equals("fail")){
            message="<div>Bohužel se změna údajů nezdařila, zkuste změnit údaje znovu nebo kontaktujte administrátora.</div>";
        }else if(registered.equals("passwordsuccess")){
            message="<div>Bohužel se změna hesla nezdařila, zkuste změnit heslo znovu nebo kontaktujte administrátora.</div>";
        }else if(registered.equals("passwordfail")){
            message="<div>Bohužel se změna hesla nezdařila, zkuste změnit heslo znovu nebo kontaktujte administrátora.</div>";
        }
        
        session.setAttribute("formCheck", null);
        session.setAttribute("registered", null);                               //session proměnná registered určuje, zda se podařil zápis do mysql databáze
        security.noDirectAccess(response);
    %>
<h1 class="title-header"></h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <h2></h2>
                <p class="entry-meta">
                </p><!-- end .entry-meta -->
                <div class="entry-summary"><p></p>
                    
                    <%
                        Map<Label, String> info=(Map<Label, String>) session.getAttribute("info");
                        for(Label label : info.keySet()) {
                        //    if(FormGroup.contact.getFirst(info)!=null&&FormGroup.contact.getFirst(info).equals(label)){   //ošetření proti tomu, když nic z hashmapy info nespadá do kategorie
                    
                    %>
                    <!--<fieldset>
                        <legend><%= FormGroup.CONTACT.getName() %></legend>-->
                    <%
                            //} 
                    %>
                    <div>
                        <span id="listOfApplicantsLabel"><%= label.getNameForUsers() %></span>
                        <span id="listOfApplicants"><%= info.get(label) %></span>
                    </div>
                    <%
                            //if(FormGroup.contact.getLast(info)!=null&&FormGroup.contact.getLast(info).equals(label)){     //ošetření proti tomu, když nic z hashmapy info nespadá do kategorie
                    %>
                    <!-- </fieldset> -->
                    <%
                            //}
                        }
                    %>
                    <%= message %>
                    <div>
                        Změna osobních údajů:
                    </div>
                    <div>
                        <form action="ChangeDataCheck" method="POST" id="registerForm">
                            <%
                                for(Label label : Label.values()){
                                    if (label.isPasswordChange()) {
                            %>
                            <div>
                                <label for="<%= label.getNameRaw() %>"<%= empty.get(label) %>><%=label.getNameForApplicants() %></label>
                                <input id="<%= label.getNameRaw() %>" type="password" name="<%= label.getNameRaw() %>">
                            </div>
                            <%
                                    }
                                }
                            %>
                            <input type="submit" name="zmenitheslo" value="změnit heslo">
                        </form>
                    </div>
                </div>
                <div>
                    <form action="ChangeDataCheck" method="POST" id="registerForm">
                <%
                    for(Label label : Label.values()){
                        if(label.isChangableByUser()&&label.isInTable(security.getUser().getRights().getTable())){ 
                %>
                        <div>
                            <label for="<%= label.getNameRaw() %>"<%= empty.get(label) %>><%= label.getNameForUsers() %></label>
                            <input id="<%= label.getNameRaw() %>" type="text" name="<%= label.getNameRaw() %>">
                        </div>
                <%
                        }
                    }
                %>
                        <input type="submit" name="zmenitostatniudaje" value="změnit údaje">
                    </form>
                </div>
                
</div>

    <%@ include file="/footer.jsp"%>
