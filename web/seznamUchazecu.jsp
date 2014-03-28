<%-- 
    Document   : seznamUchazecu
    Created on : 7.3.2014, 13:58:59
    Author     : Azathoth
--%>

<%@page import="source.SecurityCheck"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="source.Label" %>
    <%--@ include file="/header.jsp"--%>

<html>
    <body>

    <link rel='stylesheet' id='academica-style-css'  href='style.css?ver=3.8.1' type='text/css' media='all' />
<%  
    SecurityCheck security=new SecurityCheck(request);
    
%>
    <h1 class="title-header">Pro Administrativu</h1>
        <!--</div>-->

       <%--@ include file="/leftColumn.jsp"--%>
  <%--     <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">--%>
            <div class="wideList">
            <%
                if(security.hasAdministrativaRights()){
                String tabulka=(String)session.getAttribute("tabulka");
            %>
            <h2>Seznam uchazečů z tabulky <%= tabulka %>:</h2>
                
                <form id="showPeopleForm" action="uchazeci" method="POST">
                    <%  
                        String[][] uchazec=(String[][]) session.getAttribute("allApplicants");
                        boolean spam=(Boolean) session.getAttribute("spam");
                        String[] label=Label.getLabel();
                        String[] labelRaw=Label.getLabelRaw();
                        if(tabulka.equals("studenti")){
                            label=Label.getLabelStudent();
                            labelRaw=Label.getLabelStudentRaw();
                        }
                        String[] show=new String[label.length+1];
                        String[] checked = new String [label.length+1];
                        String[] input = new String [label.length+1];           //kvůli políčko zaškrtnout všechno je tam o 1 víc než labelů
                        String[] stavPrihlasky={"přijat","zaplacen registrační poplatek", "nezaplacen registrační poplatek", "nezevidován administrativou"};
                        String[] stavPrihlaskyRaw={"prijat","zaplacenregistracnipoplatek", "nezaplacenregistracnipoplatek", "nezevidovanadministrativou"};
                        String[][] stavPrihlaskySelected=new String[uchazec.length][stavPrihlasky.length];              //pole polí: pro každého uživatele pole se všemi možnostmi ze kterých jedna bude vypsána a zbytek bude prázdný string
                        
                        if(!tabulka.equals("studenti")){
                            for (int i = 0; i < stavPrihlaskySelected.length; i++) {//naplnení pole polí hodnotami podle toho, co je u uživatele v mysql tabulce
                                if(stavPrihlaskySelected[0]!=null){
                                    for (int j = 0; j < stavPrihlaskySelected[0].length; j++) {
                                        if (stavPrihlasky[j].equals(uchazec[i][43])) {
                                            stavPrihlaskySelected[i][j]="selected=\"selected\"";
                                        } else {
                                            stavPrihlaskySelected[i][j]="";
                                        }
                                    }
                                }
                            }
                        }
                        for (int i = 0; i < show.length; i++) {
                            show[i]="";
                        }
                        if(session.getAttribute("show")!=null){
                            show=(String[]) session.getAttribute("show");
                        }    
                        for (int i = 0; i < checked.length; i++) {
                            if(show[i].equals("show")){
                                checked[i]="checked";
                            }
                            else {
                                checked[i]="";
                            }
                        }
                        for (int i = 0; i < input.length; i++) {
                            input[i]="sloupec"+i;
                        }
                        for(int i = 0; i < input.length-1; i++){
                            %>
                            <span style="checkBoxForm">
                                <label for="<%= input[i] %>"><%= label[i] %></label>
                                <input type="checkbox" id="<%= input[i] %>" name="<%= input[i] %>" value="checked" <%= checked[i] %>>
                            <br/>
                            </span>
                            <%
                        }
                    %>
                    <span style="checkBoxForm">
                        <label for="<%= input[input.length-1] %>">Všechny sloupce</label>
                        <input type="checkbox" id="<%= input[input.length-1] %>" name="<%= input[input.length-1] %>" value="checked" <%= checked[input.length-1] %>>
                    <br/>
                    </span>
                    <div>
                        <input type="submit" name="zobrazitvysledky" value="zobrazit výsledky">
                    </div>
                </form>
                    <div>
                    <%  
                        for(int j = 0; j < label.length; j++){
                            if(show[j]!=null&&show[j].equals("show")){
                        %>
                                <span id="listOfApplicantsLabel">
                                    <%= label[j] %>
                                </span>
                        <%
                            }
                        }
                    %>
                    <%
                        if(spam){
                    %>
                        <span id="listOfApplicantsLabel">
                            přesunout ze spamu mezi běžné uchazeče
                        </span>
                    <%
                        } else if(tabulka.equals("uchazeci")){
                    %>
                        <span id="listOfApplicantsLabel">
                            vytvořit studenta
                        </span>
                    
                    <%
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
                        <span id="listOfApplicantsLabel">
                            <%= uchazec[i][0] %>
                        </span>
                        <%  
                            }
                            for(int j = 1; j < label.length; j++){
                                if(show[j]!=null&&show[j].equals("show")){
                                    if(j==44) {                                 //stav přihlášky se vypisuje jako select
                        %>
                                    <span id="listOfApplicants">
                                        <select name="<%= labelRaw[j]+"+"+i %>">
                                            <option value="<%= stavPrihlasky[0] %>" <%= stavPrihlaskySelected[i][0] %>><%= stavPrihlasky[0] %></option>
                                            <option value="<%= stavPrihlasky[1] %>" <%= stavPrihlaskySelected[i][1] %>><%= stavPrihlasky[1] %></option>
                                            <option value="<%= stavPrihlasky[2] %>" <%= stavPrihlaskySelected[i][2] %>><%= stavPrihlasky[2] %></option>
                                        </select>
                                    </span>
                        <%
                                    } 
                                    else {
                        %>
                                    <span id="listOfApplicants">
                                        <input type="text" name="<%= labelRaw[j]+"+"+i %>" value="<%= uchazec[i][j] %>">
                                    </span>
                        <%
                                    }
                                }
                            }
                        
                            if(spam){
                            %>
                                <span id="listOfApplicantsLabel">
                                    <input type="checkbox" name="<%= "transfer"+"+"+i %>" value="checked">
                                </span>
                            <%
                            } else if(tabulka.equals("uchazeci")){
                            %>
                                <span id="listOfApplicantsLabel">
                                    <input type="checkbox" name="<%= "createstudent"+"+"+i %>" value="checked">
                                </span>
                            <%
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
            </div>

    </body>
</html>
            
            <%--     </div>--%>

    <%--@ include file="/footer.jsp"--%>