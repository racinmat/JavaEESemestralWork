<%-- 
    Document   : proPrihlasene
    Created on : 19.3.2014, 18:37:50
    Author     : Azathoth
--%>

<%@page import="source.Mysql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
    <%
        String[] label=Label.getLabel();
        String[] labelRaw=Label.getLabelRaw();
        String[] empty=new String[22];                                          //měnit v případě změny počtu položek ve formuláři
        Mysql sql=new Mysql();
        if(session.getAttribute("formCheck")!=null){
            empty=(String[]) session.getAttribute("formCheck");                 //zjistí se, jak byl uživatel úspěšný při registraci
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
        
    %>
<h1 class="title-header"></h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <h2></h2>
                <p class="entry-meta">
                </p><!-- end .entry-meta -->
                <%
                    if(security.hasUchazecRights()){ 
                %>
                <div class="entry-summary"><p></p>
                    
                    <div>
                        Osobní údaje:
                    </div>
                    
                    <%
                        
                        String username=(String) session.getAttribute("username");
                        String[] info;
                        if(security.isUchazec()||security.isStudent()){
                            String table=sql.findTableWithApplicant(username);
                            info=sql.showApplicant(username, table);
                        } else {
                            info=sql.showLoginInfoOfUser(username);
                        }
                        for(int i = 0; i < info.length; i++) {
                            if(i==3){
                            }
                            else {
                                if(i==0||i==9||i==10||i==18||i==27||i==36||i==43){
                            %>
                                <fieldset>
                                    <legend>
                                    <%
                                        if(i==0){
                                    %>nacionále<%
                                        }
                                        if(i==9){
                                    %>kontakt<%
                                        }
                                        if(i==10){
                                    %>narození<%
                                        }
                                        if(i==18){
                                    %>trvalé bydliště<%
                                        }
                                        if(i==27){
                                    %>kontaktní adresa<%
                                        }
                                        if(i==36){
                                    %>střední škola<%
                                        }
                                        if(i==43){
                                    %>ostatní<%
                                        }
                                        
                                    %>
                    </legend>
                            <%
                                }
                    %>
                    <div>
                        <span id="listOfApplicantsLabel"><%= label[i] %></span>
                        <span id="listOfApplicants"><%= info[i] %></span>
                    </div>
                    <%
                                if(i==8||i==9||i==17||i==26||i==35||i==42||i==45||(i==2&&!(security.isUchazec()||security.isStudent()))){
                            %>
                                </fieldset>
                            <%
                                }
                            }
                        }
                    %>
                    
                    <%= message %>
                    <div>
                        Změna osobních údajů:
                    </div>
                    <div>
                        <form action="ChangeDataCheck" method="POST" id="registerForm">
                            <div>
                            <label for="<%= labelRaw[4] %>"<%= empty[0] %>>Zadejte své heslo:</label>
                            <input id="<%= labelRaw[4] %>" type="password" name="<%= labelRaw[4] %>">
                            </div>
                            <div>
                            <label for="noveheslo"<%= empty[1] %>>Zadejte své nové heslo:</label>
                            <input id="noveheslo" type="password" name="noveheslo">
                            </div>
                            <div>
                            <label for="kontrola"<%= empty[2] %>>Zadejte pro kontrolu znovu své nové heslo:</label>
                            <input id="kontrola" type="password" name="kontrola">
                            </div>
                            <input type="submit" name="zmenitheslo" value="změnit heslo">
                        </form>
                    </div>
                </div>
                <%
                    }
                    else {
                        response.sendRedirect("notLogged.jsp");
                    }
                    if(security.isUchazec()||security.isStudent()){ 
                %>
                    <div>
                        <form action="ChangeDataCheck" method="POST" id="registerForm">
                            <div>
                            <label for="<%= labelRaw[9] %>"<%= empty[3] %>><%= label[9] %></label>
                            <input id="<%= labelRaw[9] %>" type="text" name="<%= labelRaw[9] %>">
                            </div>
                        <%
                            for (int i = 18; i <= 35; i++) {                      //-2 protože poslední dvě položky uchazeč nebude vyplňovat, vyplní se samy předdefinovaným stringem
                        %>
                            <div>
                            <label for="<%= labelRaw[i] %>"<%= empty[i-14] %>><%= label[i] %></label>
                            <input id="<%= labelRaw[i] %>" type="text" name="<%= labelRaw[i] %>">
                            </div>
                        <%
                            }
                        %>
                            <input type="submit" name="zmenitostatniudaje" value="změnit údaje">
                        </form>
                    </div>
                <%
                    }
                %>
</div>

    <%@ include file="/footer.jsp"%>
