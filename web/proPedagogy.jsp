<%-- 
    Document   : proPedagogy
    Created on : 21.2.2014, 14:09:16
    Author     : Azathoth
--%>

<%@page import="enums.Rights"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
<h1 class="title-header">Pro Pedagogy</h1>
        </div><!-- end .column-title -->

       <%@ include file="/leftColumn.jsp"%>
       <%
        security.accesedTo(Rights.pedagog, response);
       %>
        <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <h2></h2>
                <p class="entry-meta">
                  </p><!-- end .entry-meta -->
                <div class="entry-summary"><p>Pro pedagogy.</p></div>
            </div>

    <%@ include file="/footer.jsp"%>
