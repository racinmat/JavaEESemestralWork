<%-- 
    Document   : seznamUchazecu
    Created on : 7.3.2014, 13:58:59
    Author     : Azathoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="source.Label" %>
    <%--@ include file="/header.jsp"--%>
    <link rel='stylesheet' id='academica-style-css'  href='style.css?ver=3.8.1' type='text/css' media='all' />
<%  
    session = request.getSession(true);                                         //zpřístupní se session
    String[][] uchazec=(String[][]) session.getAttribute("allApplicants");
    String[] show=new String[uchazec[0].length];
    for (int i = 0; i < show.length; i++) {
        show[i]="";
    }
    if(session.getAttribute("show")!=null){
        show=(String[]) session.getAttribute("show");
    }
    
%>
    <h1 class="title-header">Pro Administrativu</h1>
        </div>

       <%--@ include file="/leftColumn.jsp"--%>
  <%--     <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">--%>
                <h2>Seznam uchazečů:</h2>
                
                <form id="showPeopleForm" action="uchazeci" method="POST">
                    <%  
                        Label lab=new Label();
                        String[] label=lab.getLabel();
                        String[] labelRaw=lab.getLabelRaw();
                        
                        
                        String[] checked = new String [uchazec[0].length];
                        for (int i = 0; i < checked.length; i++) {
                            if(show[i].equals("show")){
                                checked[i]="checked";
                            }
                            else {
                                checked[i]="";
                            }
                        }
                        String[] input = new String [uchazec[0].length];
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
                        for(int j = 0; j < uchazec[0].length; j++){
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
                    %>
                    <div>
                        <%  
                            
                            for(int j = 0; j < uchazec[0].length; j++){
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
                
       <%--     </div>--%>

    <%--@ include file="/footer.jsp"--%>