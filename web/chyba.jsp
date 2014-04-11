<%-- 
    Document   : chyba
    Created on : 2.4.2014, 19:49:51
    Author     : Azathoth
--%>
<%@ page import="enums.Error" %>
<%
    String temp=request.getParameter("error");
    Error error=Error.getErrorFromNumberInString(temp);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
<h1 class="title-header">Chybové hlášení, nepanikařte!</h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
        <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <h2>Někde nastala chyba, omlouváme se za komplikace.</h2>
                <p class="entry-meta">
                  </p><!-- end .entry-meta -->
                  <div class="entry-summary"><p>
                      <%= error.getText() %>
                  </p></div>
                
            </div>

    <%@ include file="/footer.jsp"%>