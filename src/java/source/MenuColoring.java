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
    String uri;
    String pageName="";
    String index="";
    String proUchazece="";
    String proAdministrativu="";
    String proStudenty="";
    String proPedagogy="";
    String uredniDeska="";

    public MenuColoring(String uri) {
        this.uri=uri;
        if (!"".equals(uri)) {
            pageName=uri.substring(uri.lastIndexOf("/")+1);
        }
        else{
            pageName="";
        }
    }

    public String getPageName() {
        return pageName;
    }
    
    public String getIndex() {
        if("index.jsp".equals(pageName)){
            index="current-menu-item page_item page-item-2 current_page_item current_page_parent";
        }
        return index;
    }

    public String getProUchazece() {
        if("proUchazece.jsp".equals(pageName)){
            proUchazece="current-menu-item page_item page-item-2 current_page_item current_page_parent";
        }
        return proUchazece;
    }

    public String getProAdministrativu() {
        if("proAdministrativu.jsp".equals(pageName)){
            proAdministrativu="current-menu-item page_item page-item-2 current_page_item current_page_parent";
        }
        return proAdministrativu;
    }

    public String getProStudenty() {
        if("proStudenty.jsp".equals(pageName)){
            proStudenty="current-menu-item page_item page-item-2 current_page_item current_page_parent";
        }
        return proStudenty;
    }

    public String getProPedagogy() {
        if("proPedagogy.jsp".equals(pageName)){
            proPedagogy="current-menu-item page_item page-item-2 current_page_item current_page_parent";
        }
        return proPedagogy;
    }

    public String getUredniDeska() {
        if("uredniDeska.jsp".equals(pageName)){
            uredniDeska="current-menu-item page_item page-item-2 current_page_item current_page_parent";
        }
        return uredniDeska;
    }
}
