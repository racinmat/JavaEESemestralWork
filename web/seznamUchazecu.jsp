<%-- 
    Document   : seznamUchazecu
    Created on : 7.3.2014, 13:58:59
    Author     : Azathoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--    <%/*@ include file="/header.jsp"*/%>
<%  
    session = request.getSession(true);                                         //zpřístupní se session
    String[][] uchazec=(String[][]) session.getAttribute("allApplicants");
%>
    <h1 class="title-header">Pro Administrativu</h1>
        </div>

       <%/*@ include file="/leftColumn.jsp"*/%>
        <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
    -->            <h2>Seznam uchazečů:</h2>
                
                
                    <%  
                        for(int i = 0; i < uchazec.length; i++){
                    %>
                    <div>
                        <%  
                            for(int j = 0; j < 44; j++){
                        %>
                            <span style="width: 100px;min-width: 100px;float: left;">
                                <%= uchazec[i][j] %>
                            </span>
                        <%
                            }
                        %>
                    </div>
                    <%
                        }
                    %>
       <!--         
            </div>
           -->     


    <%@ include file="/footer.jsp"%>