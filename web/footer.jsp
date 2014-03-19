<%-- 
    Document   : footer
    Created on : 21.2.2014, 16:35:29
    Author     : Azathoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session = request.getSession(true);                                         //zpřístupní se session
    String logged=(String) session.getAttribute("logged");                      //zjistí se, jak byl uživatel úspěšný při loginu
    String name=(String) session.getAttribute("name");                    
    String lastname=(String) session.getAttribute("lastname");                    
    String pravaString=(String) session.getAttribute("rightsString");                    
    
    String failMessage="";
    String userInfo="";
    String form="";
    if(logged==null){                                                           //výpis formuláře je podmíněný
        form="<form action=\"login\" method=\"POST\">uživatelské jméno:<input type=\"text\" name=\"username\">heslo:<input type=\"password\" name=\"password\"><input type=\"submit\" name=\"přihlásit se\" value=\"přihlásit se\"></form>";
    }                                                                           
    
    if(logged!=null&&logged.equals("fail")){                                    //ošetřena inicializace proměnné
        form="<form action=\"login\" method=\"POST\">uživatelské jméno:<input type=\"text\" name=\"username\">heslo:<input type=\"password\" name=\"password\"><input type=\"submit\" name=\"přihlásit se\" value=\"přihlásit se\"></form>";
        failMessage="<div>Přihlášení se nezdařilo, bylo nesprávně zadáno uživatelské jméno nebo heslo</div>";
        if(logged.equals("fail")){
            session.setAttribute("logged", null);
        }
    }                                                                           //pokud se uživatel úspěšně nepřihlásil, pak se to pod přihlašovacím formulářem vypíše
    if(logged!=null&&logged.equals("success")){                                    //ošetřena inicializace proměnné
        form="<form action=\"logout\" method=\"POST\"><input type=\"submit\" name=\"odhlásit se\" value=\"odhlásit se\"></form>";
        userInfo="<div id=\"footer\" class=\"clearfix\">Jste přihlášen jako "+name+" "+lastname+", váš status je "+pravaString+"</div>";
    }                                                                           //pokud se uživatel úspěšně nepřihlásil, pak se to pod přihlašovacím formulářem vypíše
    
%>
            
        </div><!-- end .column-content -->

            <div class="column column-narrow column-last">
		<div id="academica-featured-posts-gallery-2" class="widget clearfix academica-featured-posts-gallery">
			<ul class="posts">

				
				<li class="clearfix post">
                                    <%= form %>
                                    <%= failMessage %>
                                </li>

				
			</ul><!-- end .posts -->

			</div>	</div><!-- end .column-narrow -->
                        <%= userInfo %>
    </div><!-- end #content -->

    <div id="footer" class="clearfix">

        <p class="copy">
            <span class="sep">Inspired by <a href="http://wordpress.org/">WordPress</a> template <a href="http://www.wpzoom.com/">Academica by WPZOOM</a></span>
            © 2014 Všechna práva vyhrazena. autor: Matěj Račinský.
        </p>
    </div><!-- end #footer -->
</div><!-- end #wrap -->

<script type='text/javascript' src='http://skola.bluefile.cz/wp-includes/js/admin-bar.min.js?ver=3.8.1'></script>
<script type='text/javascript' src='http://skola.bluefile.cz/wp-content/themes/academica/js/menu.js?ver=20120921'></script>
<script type="text/javascript">
    (function() {
        var request, b = document.body, c = 'className', cs = 'customize-support', rcs = new RegExp('(^|\\s+)(no-)?'+cs+'(\\s+|$)');

        request = true;

        b[c] = b[c].replace( rcs, ' ' );
        b[c] += ( window.postMessage && request ? ' ' : ' no-' ) + cs;
    }());
</script>


</body>
</html>