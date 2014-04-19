<%-- 
    Document   : header
    Created on : 21.2.2014, 16:34:17
    Author     : Azathoth
--%>

<%@page import="source.LoggedUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="source.MenuColoring" %>
<%@ page import="source.SecurityCheck" %>
<%@ page import="enums.Label" %>


<% 
    MenuColoring menu=new MenuColoring(request.getRequestURI());                //vytvoří se insrtance objektu barvícího horní menu, u všech položek menu jsou jeho gettery
    session = request.getSession(true);                                         //zpřístupní se session
    String loggingURL=menu.getPageName();                                       //získá se název současné stránky
    session.setAttribute("loggingURL", loggingURL);                             //nastaví se session proměnná loggingURL, abych věděl, odkud se uživatel přihlašovat a tedy, kam jej po přihlášení "vrátit"
    session.setAttribute("redirect", null);
    SecurityCheck security=new SecurityCheck(request);
    LoggedUser user=(LoggedUser) session.getAttribute("user");    
                            
%>
<!DOCTYPE html>
<html lang="cs-CZ">
<head>
    
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <meta charset="UTF-8" >

    <title>Vysoká škola</title>
    
    <link rel='stylesheet' id='academica-style-css'  href='style.css?ver=3.8.1' type='text/css' media='all' />

    <style type="text/css" media="screen">
        html { margin-top: 32px !important; }
        * html body { margin-top: 32px !important; }
        @media screen and ( max-width: 782px ) {
            html { margin-top: 46px !important; }
            * html body { margin-top: 46px !important; }
        }
    </style>

</head>
<body class="blog logged-in admin-bar no-customize-support">
<div id="wrap">
    <div id="header" class="clearfix">
            <!--horní menu-->
            
        <div id="main-nav">
            <ul id="menuhead" class="menu">
                <li id="menu-item" class="menu-item  
                    <%= menu.getoSkole()%>
                    ">
                    <div onmouseover="javascript: show( 'submenu1' );" onmouseout="javascript: hide( 'submenu1' );">
                            <a>O škole</a>
                        <div id="submenu1">
                            <ul> 
                                    <li><a href="oSkole_VybaveniSkoly.jsp">Vybavení školy</a></li>
                                    <li><a href="oSkole_ZamereniStudia.jsp">Zaměření studia</a></li>
                                    <li><a href="oSkole_VyukaCizichJazyku.jsp">Výuka cizích jazyků</a></li>
                                    <li><a href="oSkole_VolitelnePredmety.jsp">Volitelné předměty</a></li>
                                    <li><a href="oSkole_StudentskaRada.jsp">Studentská rada</a></li>
                                    <li><a href="oSkole_ICTPlan.jsp">ICT plán</a></li>
                                    <li><a href="oSkole_SkolskaRada.jsp">Školská rada</a></li>
                            </ul>    
                        </div>
                    </div>
                </li>
                <%
                    if(security.hasPedagogRights()){ 
                %>
                <li id="menu-item" class="menu-item  
                    <%= menu.getProPedagogy() %>
                    ">
                    <a href="proPedagogy.jsp">Pro pedagogy</a>
                </li>
                <%
                    }
                %>
                <li id="menu-item" class="menu-item  
                    <%= menu.getProUchazece() %>
                    ">
                    <div onmouseover="javascript: show( 'submenu2' );" onmouseout="javascript: hide( 'submenu2' );">
                        <a>Pro uchazeče o studium</a>
                        <div id="submenu2">
                            <ul> 
                                    <li><a href="proUchazece_Prihlaska.jsp">Elektronická přihláška</a></li>
                                    <li><a href="proUchazece_DnyOtevrenychDveri.jsp">Dny otevřených dveří</a></li>
                                    <li><a href="proUchazece_UkazkovePrijimaciTesty.jsp">Ukázkové přijímací testy</a></li>
                                    <li><a href="proUchazece_KriteriaPrijimacihoRizeni.jsp">Kritéria přijímacího řízení</a></li>
                                    <li><a href="proUchazece_PrijimaciZkousky.jsp">Přijímací zkoušky</a></li>
                                    <li><a href="proUchazece_VysledkyPrijimacichZkousek.jsp">Výsledky přijímacích zkoušek</a></li>
                            </ul>    
                        </div>
                    </div>
                </li>
                <li id="menu-item" class="menu-item  
                    <%= menu.getProStudenty() %>
                    ">
                    <div onmouseover="javascript: show( 'submenu3' );" onmouseout="javascript: hide( 'submenu3' );">
                        <a>Pro studenty</a>
                        <div id="submenu3">
                            <ul> 
                                    <li><a href="proStudenty_OrganizaceSkolnihoRoku.jsp">Organizace školního roku</a></li>
                                    <li><a href="proStudenty_SkolniRad.jsp">Školní řád</a></li>
                                    <li><a href="proStudenty_UcebniPlany.jsp">Učební plány</a></li>
                                    <li><a href="proStudenty_InformaceKJednotlivymPredmetum.jsp">Informace k jednotlivým předmětům</a></li>
                                    <li><a href="proStudenty_MezinarodniProgramy.jsp">Mezinárodní programy</a></li>
                                    <li><a href="proStudenty_UspechyStudentu.jsp">Úspěchy studentů</a></li>
                                    <li><a href="proStudenty_AktivityStudentu.jsp">Aktivity studentů</a></li>
                                    <li><a href="proStudenty_Knihovna.jsp">Knihovna</a></li>
                                    <li><a href="proStudenty_Suplovani.jsp">Suplování</a></li>
                                    <li><a href="proStudenty_Rozvrhy.jsp">Rozvrhy</a></li>
                            </ul>    
                        </div>
                    </div>
                </li>
                <%
                    if(security.hasAdministrativaRights()){ 
                %>
                <li id="menu-item" class="menu-item  
                    <%= menu.getProAdministrativu() %>
                    ">
                    <a href="proAdministrativu.jsp">Pro administrativu</a>
                </li>
                <%
                    }
                %>
                <li id="menu-item" class="menu-item  
                    <%= menu.getUredniDeska() %>
                    ">
                    <a href="uredniDeska.jsp">Úřední deska</a>
                </li>
                <%
                    if(security.hasUchazecRights()){ 
                %>
                <li id="menu-item" class="menu-item  
                    <%= menu.getProPrihlasene()%>
                    "><a href="ForLoggedIn">Můj profil</a></li>
                <%
                    }
                %>
            </ul>			</div><!-- end #main-nav -->

        <div id="logo">
            <h1 id="site-title">
                <!--hlavní nadpis-->
                <a href="index.jsp" title="vysoká škola pedagogicko psychologická - " rel="home">
                    vysoká škola</a>
            </h1>
            <p id="site-description"></p>
        </div><!-- end #logo -->
            <!-- lokální search zprovoznit později
        <div id="search">
            <form method="get" id="searchform" action="http://skola.bluefile.cz/?page_id=2/">
                <label for="s" class="assistive-text hidden">Search</label>
                <input id="s" type="text" name="s" placeholder="Search">
                <button id="searchsubmit" name="submit" type="submit">Search</button>
            </form>			</div><!-- end #search -->

<!-- na fb, twitter atd, nepotřebné
        <div id="social">
            <ul>
            </ul>
        </div>-->
    </div><!-- end #header -->
    <!--úvodní obrázek
    <div id="slider-wrap">
        <a href="http://skola.bluefile.cz/?page_id=2/" title="vysoká škola pedagogicko psychologická" rel="home">
            <img src="http://skola.bluefile.cz/wp-content/uploads/2014/02/cropped-14.png" width="960" height="300" alt="" />
        </a>
    </div><!-- end #slider-wrap -->

    <div id="content" class="clearfix">

        <div class="column column-title">
            <div id="crumbs">
                <p></p>
            </div><!-- end #crumbs -->		
