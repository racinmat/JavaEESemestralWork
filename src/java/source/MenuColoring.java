package source;










/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Azathoth
 */
public class MenuColoring {
    private String uri;
    private String pageName="";
    private String pageNameStripped="";
    private String index="";
    private String proUchazece="";
    private String proAdministrativu="";
    private String proStudenty="";
    private String proPedagogy="";
    private String uredniDeska="";
    private String proPrihlasene="";
    private String oSkole="";
    private String colored="current-menu-item";
    
    public MenuColoring(String uri) {
        this.uri=uri;
        if (!"".equals(uri)) {
            pageName=uri.substring(uri.lastIndexOf("/")+1);                     //získá poslední úroveň stránky
        }
        else{
            pageName="";
        }
        
        pageNameStripped=pageName;
        
        if (pageNameStripped.contains(".")) {
            pageNameStripped=pageNameStripped.substring(0, pageNameStripped.lastIndexOf("."));        //odtrhne .jsp
            if (pageNameStripped.contains("_")) {
                pageNameStripped=pageNameStripped.substring(0, pageNameStripped.lastIndexOf("_"));        //odtrhne název podstránky
            }
        }
    }

    public String getPageName() {
        return pageName;
    }
    
    public String getIndex() {
        if("index".equals(pageNameStripped)){
            index=colored;
        }
        return index;
    }

    public String getProUchazece() {
        if("proUchazece".equals(pageNameStripped)){
            proUchazece=colored;
        }
        return proUchazece;
    }

    public String getProAdministrativu() {
        if("proAdministrativu".equals(pageNameStripped)){
            proAdministrativu=colored;
        }
        return proAdministrativu;
    }

    public String getProStudenty() {
        if("proStudenty".equals(pageNameStripped)){
            proStudenty=colored;
        }
        return proStudenty;
    }

    public String getProPedagogy() {
        if("proPedagogy".equals(pageNameStripped)){
            proPedagogy=colored;
        }
        return proPedagogy;
    }

    public String getUredniDeska() {
        if("uredniDeska".equals(pageNameStripped)){
            uredniDeska=colored;
        }
        return uredniDeska;
    }
    
    public String getProPrihlasene() {
        if("proPrihlasene".equals(pageNameStripped)){
            proPrihlasene=colored;
        }
        return proPrihlasene;
    }

    public String getoSkole() {
        if("oSkole".equals(pageNameStripped)){
            oSkole=colored;
        }
        return oSkole;
    }
}
