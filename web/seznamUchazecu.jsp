<%-- 
    Document   : seznamUchazecu
    Created on : 7.3.2014, 13:58:59
    Author     : Azathoth
--%>

<%@page import="source.SecurityCheck"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="source.Label" %>
    <%--@ include file="/header.jsp"--%>
    <link rel='stylesheet' id='academica-style-css'  href='style.css?ver=3.8.1' type='text/css' media='all' />
<%  
    String[][] uchazec=(String[][]) session.getAttribute("allApplicants");
    String[] label=Label.getLabel();
    String[] labelRaw=Label.getLabelRaw();
    String[] show=new String[label.length];
    SecurityCheck security=new SecurityCheck(request);
    
%>
    <h1 class="title-header">Pro Administrativu</h1>
        </div>

       <%--@ include file="/leftColumn.jsp"--%>
  <%--     <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">--%>
            <%
                if(security.isAdministrativa()){
            %>
                <h2>Seznam uchazečů:</h2>
                
                <form id="showPeopleForm" action="uchazeci" method="POST">
                    <%  
                        String[] checked = new String [label.length];
                        for (int i = 0; i < checked.length; i++) {
                            if(show[i].equals("show")){
                                checked[i]="checked";
                            }
                            else {
                                checked[i]="";
                            }
                        }
                        String[] input = new String [label.length];
                        for (int i = 0; i < input.length; i++) {
                            input[i]="sloupec"+i;
                        }
                        for(int i = 0; i < input.length; i++){
                    %>
                    <span style="checkBoxForm">
                        <label for="<%= input[i] %>"><%= label[i] %></label>
                        <input type="checkbox" id="<%= input[i] %>" name="<%= input[i] %>" value="checked" <%= checked[i] %>>
                    <br/>
                    </span>
                    <%
                        }
                    %>
                    <div>
                        <input type="submit" name="zobrazitvysledky" value="zobrazit výsledky">
                    </div>
                </form>
                    <div>
                    <%  
                        for(int j = 0; j < label.length; j++){
                            if(show[j]!=null&&show[j].equals("show")){
                        %>
                                    <span id="listOfApplicants">
                                        <%= label[j] %>
                                    </span>
                        <%
                            }
                        }
                    %>
                        <br/>
                    </div>
                    <form action="uchazeci" method="POST">
                    <%
                        for(int i = 0; i < uchazec.length; i++){
                            if(show[0]!=null&&show[0].equals("show")){
                    %>
                    <div>
                        <span id="listOfApplicants">
                            <%= uchazec[i][0] %>
                        </span>
                        <%  
                            }
                            for(int j = 1; j < label.length; j++){
                                if(show[j]!=null&&show[j].equals("show")){
                        %>
                                    <span >
                                        <input type="text" name="<%= labelRaw[j]+"+"+i %>" value="<%= uchazec[i][j] %>">
                                    </span>
                        <%
                                }
                            }
                        %>
                        <br/>
                    </div>
                    <%
                        }
                    %>
                    <input type="submit" name="zmenitudaje" value="změnit údaje">
                    </form>
            <%
                }
                else {
                    response.sendRedirect("notLogged.jsp");
                }
            %>
       <%--     </div>--%>

    <%--@ include file="/footer.jsp"--%>