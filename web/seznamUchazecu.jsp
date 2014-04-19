<%-- 
    Document   : seznamUchazecu
    Created on : 7.3.2014, 13:58:59
    Author     : Azathoth
--%>

<%@page import="source.MyLogger"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="enums.SQLTables"%>
<%@page import="enums.ApplicationState"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="enums.Rights"%>
<%@page import="source.SecurityCheck"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="enums.Label" %>
    <%--@ include file="/header.jsp"--%>

<html>
    <body>

    <link rel='stylesheet' id='academica-style-css'  href='style.css?ver=3.8.1' type='text/css' media='all' />
<%  
    SecurityCheck security=new SecurityCheck(request);
    security.noDirectAccess(response);
    security.accesedTo(Rights.administrativa, response);
    String temp="";                    //implicitní hodnota, která je vždy přepsána, je zde pouze proto, že při pokusu o přímý přístup session proměnná "tabulka" nemá žádnou hodnotu a stránka spadne ještě před přesměrováním
    if ((String)session.getAttribute("tabulka")!=null) {
        temp=(String)session.getAttribute("tabulka");
    }
    SQLTables tabulka=SQLTables.getTableFromNumberInString(temp);
    if((ArrayList<HashMap<Label, String>>) session.getAttribute("allApplicants")!=null&&(Boolean) session.getAttribute("spam")!=null){//podmínka, která je false při direct accessu
%>
    <h1 class="title-header">Pro Administrativu</h1>
        <!--</div>-->

       <%--@ include file="/leftColumn.jsp"--%>
  <%--     <div id="column-content" class="column column-content posts">


            <div id="post-1" class="post-1 post type-post status-publish format-standard hentry category-nezarazene clearfix">--%>
            <div class="wideList">
                <a href="proAdministrativu.jsp"><input type="button" name="zpet" value="Zpět"></a>
            <h2>Seznam uchazečů z tabulky <%= tabulka.getTable() %>:</h2>
                
                <form id="showPeopleForm" action="applicants" method="POST">
                    <%  
                        ArrayList<HashMap<Label, String>> uchazec=(ArrayList<HashMap<Label, String>>) session.getAttribute("allApplicants");
                        boolean spam=(Boolean) session.getAttribute("spam");
                        LinkedHashMap<Label, String> checked = new LinkedHashMap<Label, String>();      //kvůli zachování pořadí při vkládání hodnotse používá linked
                        ArrayList<HashMap<ApplicationState, String>> stavPrihlaskySelected=new ArrayList<HashMap<ApplicationState, String>>();
                        String selected="selected=\"selected\"";
                        if(uchazec.size()>0&&uchazec.get(0).containsKey(Label.applicationstate)){
                            for (int i = 0; i < uchazec.size(); i++) {
                                stavPrihlaskySelected.add(new HashMap<ApplicationState, String>());
                                for (ApplicationState stav : ApplicationState.values()) {
                                    if (uchazec.get(i).get(Label.applicationstate).equals(stav.getNameRaw())) {
                                        stavPrihlaskySelected.get(i).put(stav, selected);
                                    } else {
                                        stavPrihlaskySelected.get(i).put(stav, "");
                                    }
                                }
                            }
                        }
                        for (Label label : Label.values()) {
                            if((label.isShowToAdministrativa()&&label.isInTables(SQLTables.login, tabulka))||label.equals(Label.allColumns)){
                                checked.put(label, "");
                            }
                        }
                        if(session.getAttribute("checked")!=null){
                            checked=(LinkedHashMap<Label, String>) session.getAttribute("checked");
                        }    
                        for (Label label : checked.keySet()) {
                            %>
                            <span style="checkBoxForm">
                                <label for="<%= label.getNameRaw() %>"><%= label.getNameForUsers() %></label>
                                <input type="checkbox" id="<%= label.getNameRaw() %>" name="<%= label.getNameRaw() %>" value="checked" <%= checked.get(label) %>>
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
                        for (Label label : checked.keySet()) {
                            if(label.isChangableByAdministrativa()){
                                if(checked.get(label).equals("checked")){
                    %>
                                <span id="listOfApplicantsLabel">
                                    <%= label.getNameForUsers() %>
                                </span>
                        <%
                                }
                            }
                        }
                        if(spam){
                    %>
                        <span id="listOfApplicantsLabel">
                            přesunout ze spamu mezi běžné uchazeče
                        </span>
                    <%
                        } else if(tabulka.equals(SQLTables.applicants)){
                    %>
                        <span id="listOfApplicantsLabel">
                            vytvořit studenta
                        </span>
                    
                    <%
                        } 
                    %>
                        <br/>
                    </div>
                        
                    <form action="applicants" method="POST">
                        <%
                        for(int i = 0; i < uchazec.size(); i++){
                        %>
                            <div>
                        <%  
                            for (Label label : Label.values()) {
                                if(label.isShowToAdministrativa()&&label.isInTables(SQLTables.login, tabulka)){
                                    if(checked.get(label)!=null&&checked.get(label).equals("checked")){
                                        if(label.equals(Label.applicationstate)) {                                 //stav přihlášky se vypisuje jako select
                        %>
                                    <span id="listOfApplicants">
                                        <select name="<%= label.getNameRaw()+"+"+i %>">
                                        <%
                                            for (ApplicationState stav : ApplicationState.values()) {
                                        %>
                                        <option value="<%= stav.getNameRaw() %>" <%= stavPrihlaskySelected.get(i).get(stav) %>><%= stav.getName() %></option>
                                        <%
                                            } 
                                        %>
                                        </select>
                                    </span>
                        <%
                                        } 
                                        else {
                        %>
                                    <span id="listOfApplicants">
                                        <input type="text" name="<%= label.getNameRaw()+"+"+i %>" value="<%= uchazec.get(i).get(label) %>">
                                    </span>
                        <%
                                        }
                                    }
                                }
                            }
                            if(spam){
                            %>
                                <span id="listOfApplicantsLabel">
                                    <input type="checkbox" name="<%= "transfer"+"+"+i %>" value="checked">
                                </span>
                            <%
                            } else if(tabulka.equals(SQLTables.applicants)){
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
            </div>

<%
    }
%>
    </body>
</html>
            
            <%--     </div>--%>

    <%--@ include file="/footer.jsp"--%>