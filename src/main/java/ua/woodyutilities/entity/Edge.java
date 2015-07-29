package ua.woodyutilities.entity;

import java.io.Serializable;

/**
 * @autor Alex Iakovenko
 * Date: 7/28/15
 * Time: 12:30 PM
 */
public class Edge implements Serializable {
    public static final String ATTR_MATCLASS = "matclass";
    public static final String ATTR_MATNAME = "matname";
    public static final String ATTR_MAT_ID = "mat_id";


    private String matClass;
    private String matName;
    private String matId;

    public Edge(){}
    public Edge(String matClass, String matName, String matId){
        this.matClass = matClass;
        this.matName = matName;
        this.matId = matId;
    }

    public String getMatClass() {
        return matClass;
    }

    public void setMatClass(String matClass) {
        this.matClass = matClass;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatId() {
        return matId;
    }

    public void setMatId(String matId) {
        this.matId = matId;
    }

    @Override
    public String toString(){
        return ATTR_MATCLASS + "=\"" + matClass + "\" " +
               ATTR_MATNAME + "=\"" + matName + "\" " +
               ATTR_MAT_ID + "=\"" + matId + "\" ";

    }
}
