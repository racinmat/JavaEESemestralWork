<%-- 
    Document   : proPrihlasene
    Created on : 19.3.2014, 18:37:50
    Author     : Azathoth
--%>

<%@page import="enums.Rights"%>
<%@page import="enums.FormularovaSkupina"%>
<%@page import="java.util.HashMap"%>
<%@page import="enums.SQLTables"%>
<%@page import="source.Mysql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
    <%
        HashMap<Label, String> empty=new HashMap<Label, String>();                           //měnit v případě změny počtu položek ve formuláři
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
        
        security.accesedTo(Rights.applicant, response);
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
                        String username=security.getUser().getUsername();
                        HashMap<Label, String> info;
                        if(security.isUchazec()||security.isStudent()){
                            SQLTables table=sql.findTableWithApplicant(username);
                            info=sql.showApplicant(username, table);
                        } else {
                            info=sql.showLoginInfoOfUser(username);
                        }
                        for(Label label : info.keySet()) {
                            if(FormularovaSkupina.kontakt.getFirst(info)!=null&&FormularovaSkupina.kontakt.getFirst(info).equals(label)){   //ošetření proti tomu, když nic z hashmapy info nespadá do kategorie
                    %>
                    <fieldset>
                        <legend><%= FormularovaSkupina.kontakt.getNazev() %></legend>
                    <%
                            }
                    %>
                    <div>
                        <span id="listOfApplicantsLabel"><%= label.getNazevProUzivatele() %></span>
                        <span id="listOfApplicants"><%= info.get(label) %></span>
                    </div>
                    <%
                            if(FormularovaSkupina.kontakt.getLast(info)!=null&&FormularovaSkupina.kontakt.getLast(info).equals(label)){     //ošetření proti tomu, když nic z hashmapy info nespadá do kategorie
                    %>
                    </fieldset>
                    <%
                            }
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
                                    if (label.isZmenaHesla()) {
                            %>
                            <div>
                                <label for="<%= label.getNazevRaw() %>"<%= empty.get(label) %>><%=label.getNazevProUchazece() %></label>
                                <input id="<%= label.getNazevRaw() %>" type="password" name="<%= label.getNazevRaw() %>">
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
                        if(label.isMenitelneUzivatelem()&&label.isInTable(security.getUser().getRights().getTable())){ 
                %>
                        <div>
                            <label for="<%= label.getNazevRaw() %>"<%= empty.get(label) %>><%= label.getNazevProUzivatele() %></label>
                            <input id="<%= label.getNazevRaw() %>" type="text" name="<%= label.getNazevRaw() %>">
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
