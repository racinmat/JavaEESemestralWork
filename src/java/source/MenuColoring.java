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
    private final String uri;
    private final String pageName;
    private String index="";
    private String proUchazece="";
    private String proAdministrativu="";
    private String proStudenty="";
    private String proPedagogy="";
    private String uredniDeska="";
    private String proPrihlasene="";
    private String oSkole="";
    private final String colored="current-menu-item";
    
    /**
     * Constructor used for putting URL address into class.
     * @param uri URL address which is used for determining on which page user is right now
     */
    public MenuColoring(String uri) {
        this.uri=uri;
        String temp = "";
        if (!"".equals(uri)) {
            temp=uri.substring(uri.lastIndexOf("/")+1);                     //získá poslední úroveň stránky
        }
        if (temp.contains(".")) {
            if (temp.contains("_")) {
                pageName=temp.substring(0, temp.lastIndexOf("_"));        //odtrhne název podstránky
            } else {
                pageName=temp.substring(0, temp.lastIndexOf("."));        //odtrhne .jsp
            }
        } else {
            pageName=temp;
        }
    }

    /**
     * 
     * @return substring from URL in constructor, used for determining whether to color column with name of page in header of page or not
     */
    public String getPageName() {
        return pageName;
    }
    
    /**
     * 
     * @return css class if user is on this page, otherwise return ""
     */
    public String getIndex() {
        if("index".equals(pageName)){
            index=colored;
        }
        return index;
    }

    /**
     * 
     * @return css class if user is on this page, otherwise return ""
     */
    public String getProUchazece() {
        if("proUchazece".equals(pageName)){
            proUchazece=colored;
        }
        return proUchazece;
    }

    /**
     * 
     * @return css class if user is on this page, otherwise return ""
     */
    public String getProAdministrativu() {
        if("proAdministrativu".equals(pageName)){
            proAdministrativu=colored;
        }
        return proAdministrativu;
    }

    /**
     * 
     * @return css class if user is on this page, otherwise return ""
     */
    public String getProStudenty() {
        if("proStudenty".equals(pageName)){
            proStudenty=colored;
        }
        return proStudenty;
    }

    /**
     * 
     * @return css class if user is on this page, otherwise return ""
     */
    public String getProPedagogy() {
        if("proPedagogy".equals(pageName)){
            proPedagogy=colored;
        }
        return proPedagogy;
    }

    /**
     * 
     * @return css class if user is on this page, otherwise return ""
     */
    public String getUredniDeska() {
        if("uredniDeska".equals(pageName)){
            uredniDeska=colored;
        }
        return uredniDeska;
    }
    
    /**
     * 
     * @return css class if user is on this page, otherwise return ""
     */
    public String getProPrihlasene() {
        if("proPrihlasene".equals(pageName)){
            proPrihlasene=colored;
        }
        return proPrihlasene;
    }

    /**
     * 
     * @return css class if user is on this page, otherwise return ""
     */
    public String getoSkole() {
        if("oSkole".equals(pageName)){
            oSkole=colored;
        }
        return oSkole;
    }
}
