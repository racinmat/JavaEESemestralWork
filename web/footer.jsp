<%-- 
    Document   : footer
    Created on : 21.2.2014, 16:35:29
    Author     : Azathoth
--%>

<%@page import="source.LoggedUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session = request.getSession(true);                                         //zpřístupní se session
    String failMessage="";
    String userInfo="";
    String form="";
    if(user!=null&&user.getLogged().equals("success")){                         //ošetřena inicializace proměnné
        String name=user.getName();                                             //user načten v headeru
        String lastname=user.getLastName();
        String pravaString=user.getRights().getRightsString();
        form="<form action=\"logout\" method=\"POST\"><input type=\"submit\" name=\"odhlásit se\" value=\"odhlásit se\"></form>";
        userInfo="<div class=\"clearfix\">Jste přihlášen jako "+name+" "+lastname+", váš status je "+pravaString+"</div>";
    }                                                                           //pokud se uživatel úspěšně nepřihlásil, pak se to pod přihlašovacím formulářem vypíše
    if(user==null){                                                           //výpis formuláře je podmíněný
        form="<form action=\"login\" method=\"POST\">"
            + "<fieldset class=\"loginForm\">"
                + "<div>"
                    + "uživatelské jméno:"
                + "</div>"
                + "<div>"
                    + "<input type=\"text\" name=\"username\">"
                + "<div>"
                    + "heslo:"
                + "</div>"
                + "<div>"
                    + "<input type=\"password\" name=\"password\">"
                + "</div>"
                + "<div>"
                    + "<br/>"
                + "</div>"
                + "<div>"
                    + "<input type=\"submit\" name=\"přihlásit se\" value=\"přihlásit se\">"
                + "</div>"
            + "</fieldset>"
            + "</form>";
    }                                                                           
    if(user!=null&&user.getLogged().equals("fail")){                                     //ošetřena inicializace proměnné
        form="<form action=\"login\" method=\"POST\">"
            + "<fieldset class=\"loginForm\">"
                + "<div>"
                    + "uživatelské jméno:"
                + "</div>"
                + "<div>"
                    + "<input type=\"text\" name=\"username\">"
                + "<div>"
                    + "heslo:"
                + "</div>"
                + "<div>"
                    + "<input type=\"password\" name=\"password\">"
                + "</div>"
                + "<div>"
                    + "<br/>"
                + "</div>"
                + "<div>"
                    + "<input type=\"submit\" name=\"přihlásit se\" value=\"přihlásit se\">"
                + "</div>"
            + "</fieldset>"
            + "</form>";
        failMessage="<div>Přihlášení se nezdařilo, bylo nesprávně zadáno uživatelské jméno nebo heslo</div>";
        user=user.clear();                                                  //špatně přihlášeného uživatele změní na nepřihlášeného uživatele
        session.setAttribute("user", user);
    }                                                                           //pokud se uživatel úspěšně nepřihlásil, pak se to pod přihlašovacím formulářem vypíše
    
%>
            
        </div><!-- end .column-content -->

            <div class="column column-narrow column-last">
		<div id="academica-featured-posts-gallery-2" class="widget clearfix academica-featured-posts-gallery">
			<ul class="posts">

				
				<li class="clearfix post">
                                    <%= form %>
                                    <%= failMessage %>
                                    <%= userInfo %>
                                </li>

				
			</ul><!-- end .posts -->

			</div>	</div><!-- end .column-narrow -->
                        
    </div><!-- end #content -->

    <div id="footer" class="clearfix">

        <p class="copy">
            <span class="sep">Inspired by <a href="http://wordpress.org/">WordPress</a> template <a href="http://www.wpzoom.com/">Academica by WPZOOM</a></span>
            © 2014 Všechna práva vyhrazena. autor: Matěj Račinský.
            <a href="changelog.jsp">Verze: 1.7.0</a>
        </p>
    </div><!-- end #footer -->
</div><!-- end #wrap -->

<script type='text/javascript' >
    ( function( $ ) {
	$( document ).ready( function() {
            $( "#menuhead ul" ).css( { display : "none" } ); // Opera Fix
            $( "#menuhead li" ).hover( function() {
                $( this ).find( 'ul:first' ).css( { visibility : "visible", display : "none" } ).show( 268 );
            }, function() {
                $( this ).find( 'ul:first' ).css( { visibility : "hidden" } );
            } );
	} );
    } )( jQuery );
</script>
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