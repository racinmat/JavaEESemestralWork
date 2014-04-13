<%-- 
    Document   : proAdministrativu
    Created on : 21.2.2014, 14:10:23
    Author     : Azathoth
--%>

<%@page import="enums.ApplicationState"%>
<%@page import="enums.Rights"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
    <%@page import="enums.Label"%>

    <h1 class="title-header">Pro Administrativu</h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <% security.accesedTo(Rights.administrativa, response);                 %>
                    
                <h2>Seznam uchazečů:</h2>
                <p class="entry-meta">
                  </p><!-- end .entry-meta -->
                <div class="entry-summary">
                    <p><a href="applicants?table=0">Vypsat seznam uchazečů i studentů.</a></p>
                    <p><a href="applicants?table=0&criteriumColumn=<%=Label.applicationstate.getNameRaw()%>&criterium=<%=ApplicationState.accepted.getNameRaw()%>&negate=yes">Vypsat seznam uchazečů.</a></p>
                    <p><a href="applicants?table=1">Vypsat seznam uchazečů, kteří vyplnili skryté pole.</a></p>
                    <p><a href="applicants?table=2">Vypsat seznam uchazečů, kteří podali více než 10 přihlášek za den z jedné IP adresy.</a></p>
                    <p><a href="applicants?table=0&criteriumColumn=<%=Label.applicationstate.getNameRaw()%>&criterium=<%=ApplicationState.notchecked.getNameRaw()%>">Vypsat seznam notcheckedých uchazečů.</a></p>
                    <p><a href="applicants?table=3">Vypsat seznam studentů.</a></p>
                    <p><a href="pridaniPedagoga.jsp">Přidat pedagoga.</a></p>
                    <p><a href="pridaniAdministrativy.jsp">Přidat administrativu.</a></p>
                </div>
            </div>
                


    <%@ include file="/footer.jsp"%>