<%-- 
    Document   : proPrihlasene
    Created on : 19.3.2014, 18:37:50
    Author     : Azathoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
    <%
        String[] label=Label.getLabel();
        String[] labelRaw=Label.getLabelRaw();
        String[] empty=new String[30];
        if(session.getAttribute("formCheck")!=null){
            empty=(String[]) session.getAttribute("formCheck");                 //zjistí se, jak byl uživatel úspěšný při registraci
        }
        String registered="";
        if(session.getAttribute("registered")!=null){
            registered=(String) session.getAttribute("registered");                 //zjistí se, jak byl uživatel úspěšný při registraci
        }
        
        String message="";
        if(registered.equals("success")){
            message="<div>Heslo bylo úspěšně změněno.</div>";
        }else if(registered.equals("fail")){
            message="<div>Bohužel se změna hesla nezdařila, zkuste změnit heslo znovu nebo kontaktujte administrátora.</div>";
        }
        
        session.setAttribute("registered", null);
        session.setAttribute("formCheck", null);
    
    %>
<h1 class="title-header">Pro přihlášené uživatele</h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <h2></h2>
                <p class="entry-meta">
                  </p><!-- end .entry-meta -->
                <%
                    if(security.isUchazec()){ 
                %>
                  <div class="entry-summary"><p>Pro přihlášené uživatele.</p>
                    <%= message %>
                    <div>
                        Změna osobních údajů:
                    </div>
                    <div>
                        <form action="ChangeDataCheck" method="POST" id="registerForm">
                            <div>
                            <label for="<%= labelRaw[4] %>"<%= empty[0] %>>Zdejte své heslo:</label>
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
                            <input type="submit" name="odeslat" value="změnit heslo">
                        </form>
                    </div>
                </div>
                <%
                }
                else {
                    response.sendRedirect("notLogged.jsp");
                }
            %>
    
</div>

    <%@ include file="/footer.jsp"%>
