<%-- 
    Document   : seznamUchazecu
    Created on : 7.3.2014, 13:58:59
    Author     : Azathoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp"%>
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

       <%@ include file="/leftColumn.jsp"%>
       <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">
                <h2>Seznam uchazečů:</h2>
                
                <form id="showPeopleForm" action="uchazeci" method="POST">
                    <%  
                        String[] label = new String [uchazec[0].length];
                        label[0]="uživatelské jméno";
                        label[1]="jméno";
                        label[2]="příjmení";
                        label[3]="hash hesla";
                        label[4]="studijní program";
                        label[5]="studijní obor";
                        label[6]="pohlaví";
                        label[7]="státní příslušnost";
                        label[8]="rodinný stav";
                        label[9]="email";
                        label[10]="den narození";
                        label[11]="měsíc narození";
                        label[12]="rok narození";
                        label[13]="číslo OP";
                        label[14]="rodné číslo";
                        label[15]="číslo pasu";
                        label[16]="místo narození";
                        label[17]="okres narození";
                        label[18]="ulice";
                        label[19]="číslo domu";
                        label[20]="část obce";
                        label[21]="obec";
                        label[22]="okres";
                        label[23]="psč";
                        label[24]="stát";
                        label[25]="telefon";
                        label[26]="pošta";
                        label[27]="kontaktní adresa: ulice";
                        label[28]="kontaktní adresa: číslo domu";
                        label[29]="kontaktní adresa: část obce";
                        label[30]="kontaktní adresa: obec";
                        label[31]="kontaktní adresa: okres";
                        label[32]="kontaktní adresa: psč";
                        label[33]="kontaktní adresa: stát";
                        label[34]="kontaktní adresa: telefon";
                        label[35]="kontaktní adresa: pošta";
                        label[36]="název střední školy";
                        label[37]="adresa střední školy";
                        label[38]="obor střední školy";
                        label[39]="jkov";
                        label[40]="kkov";
                        label[31]="izo";
                        label[42]="rok maturity";
                        label[43]="přijat";
                        
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
                        <input type="submit" name="odeslat" value="zobrazit výsledky">
                    </div>
                </form>
                    <%  
                        for(int i = 0; i < uchazec.length; i++){
                    %>
                    <div id="listOfApplicants">
                        <%  
                            for(int j = 0; j < uchazec[0].length; j++){
                                if(show[j]!=null&&show[j].equals("show")){
                        %>
                                    <span id="listOfApplicants">
                                        <%= uchazec[i][j] %>
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
                
            </div>

    <%@ include file="/footer.jsp"%>