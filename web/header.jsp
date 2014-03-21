<%-- 
    Document   : header
    Created on : 21.2.2014, 16:34:17
    Author     : Azathoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="source.MenuColoring" %>
<%@ page import="source.SecurityCheck" %>
<%@ page import="source.Label" %>


<% 
    MenuColoring menu=new MenuColoring(request.getRequestURI());                //vytvoří se insrtance objektu barvícího horní menu, u všech položek menu jsou jeho gettery
    session = request.getSession(true);                                         //zpřístupní se session
    String loggingURL=menu.getPageName();                                       //získá se název současné stránky
    session.setAttribute("loggingURL", loggingURL);                             //nastaví se session proměnná loggingURL, abych věděl, odkud se uživatel přihlašovat a tedy, kam jej po přihlášení "vrátit"
    SecurityCheck security=new SecurityCheck(request);
        
%>
<!DOCTYPE html>
<html lang="cs-CZ">
<head>
    
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <meta charset="UTF-8" >

    <title>Vysoká škola pedagogicko psychologická</title>
    
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
                <li id="menu-item-20" class="menu-item menu-item-type-post_type menu-item-object-page 
                    <%= menu.getIndex() %>
                    menu-item-20"><a href="index.jsp">Úvodní stránka</a></li>
                <li id="menu-item-17" class="menu-item menu-item-type-post_type menu-item-object-page 
                    <%= menu.getProPedagogy() %>
                    menu-item-17"><a href="proPedagogy.jsp">Pro pedagogy</a></li>
                <li id="menu-item-18" class="menu-item menu-item-type-post_type menu-item-object-page 
                    <%= menu.getProUchazece() %>
                    menu-item-18"><a href="proUchazece.jsp">Pro uchazeče o studium</a></li>
                <li id="menu-item-19" class="menu-item menu-item-type-post_type menu-item-object-page 
                    <%= menu.getProStudenty() %>
                    menu-item-19"><a href="proStudenty.jsp">Pro studenty</a></li>
                <li id="menu-item-23" class="menu-item menu-item-type-post_type menu-item-object-page 
                    <%= menu.getProAdministrativu() %>
                    menu-item-23"><a href="proAdministrativu.jsp">Pro administrativu</a></li>
                <li id="menu-item-23" class="menu-item menu-item-type-post_type menu-item-object-page 
                    <%= menu.getUredniDeska() %>
                    menu-item-23"><a href="uredniDeska.jsp">Úřední deska</a></li>
                <%
                    if(security.hasUchazecRights()){ 
                %>
                <li id="menu-item-23" class="menu-item menu-item-type-post_type menu-item-object-page 
                    <%= menu.getProPrihlasene()%>
                    menu-item-23"><a href="proPrihlasene.jsp">Můj profil</a></li>
                <%
                    }
                %>
            </ul>			</div><!-- end #main-nav -->

        <div id="logo">
            <h1 id="site-title">
                <!--hlavní nadpis-->
                <a href="index.jsp" title="vysoká škola pedagogicko psychologická - " rel="home">
                    vysoká škola pedagogicko psychologická</a>
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
