<%-- 
    Document   : proAdministrativu
    Created on : 21.2.2014, 14:10:23
    Author     : Azathoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>

    <%
        session = request.getSession(true);
        String loggedIn="";
        String temp="";
        int rights=Integer.MAX_VALUE;
        if(session.getAttribute("rights")!=null&&session.getAttribute("logged")!=null){
        temp=(String) session.getAttribute("rights").toString();
        loggedIn=(String) session.getAttribute("logged");
            if(temp!=null){
                rights=Integer.parseInt(temp);
            }
        }
    %>
    <h1 class="title-header">Pro Administrativu</h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <% if(loggedIn.equals("success")&&rights<=1){ %>
                    
                <h2>Seznam uchazečů:</h2>
                <p class="entry-meta">
                  </p><!-- end .entry-meta -->
                <div class="entry-summary">
                    <p><a href="uchazeci?table=uchazeci">Vypsat seznam uchazečů.</a></p>
                    <p><a href="uchazeci?table=uchazeci_spam">Vypsat seznam uchazečů, kteří vyplnili skryté pole</a></p>
                    <p><a href="uchazeci?table=uchazeci_ipspam">Vypsat seznam uchazečů, kteří podali více než 10 přihlášek za den z jedné IP adresy.</a></p>
                    <% } %>
                </div>
            </div>
                


    <%@ include file="/footer.jsp"%>