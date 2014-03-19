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
        SecurityCheck security=new SecurityCheck(request);
    
    %>
<h1 class="title-header">Pro Pedagogy</h1>
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
                    <div>
                        Změna osobních údajů:
                    </div>
                    <div>
                        <form action="registercheck" method="POST" id="registerForm">
                            <label for="<%= labelRaw[4] %>">Zdejte své heslo:</label>
                            <input id="<%= labelRaw[4] %>" type="text" name="<%= labelRaw[4] %>">
                            <label for="noveheslo">Zadejte své nové heslo:</label>
                            <input id="noveheslo" type="password" name="noveheslo">
                            <label for="kontrola">Zadejte pro kontrolu znovu své nové heslo:</label>
                            <input id="kontrola" type="password" name="kontrola">
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
