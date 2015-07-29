package ua.woodyutilities.entity;

import java.io.Serializable;

/**
 * @autor Alex Iakovenko
 * Date: 7/28/15
 * Time: 12:15 PM
 */
public class Part implements Serializable {
    public static final String ATTR_NAME = "name";
    public static final String ATTR_TYPE = "type";
    public static final String ATTR_CODE = "code";
    public static final String ATTR_MATCLASS = "matclass";
    public static final String ATTR_MATNAME = "matname";
    public static final String ATTR_MAT_ID = "mat_id";
    public static final String ATTR_SIZE_H = "SizeH";
    public static final String ATTR_SIZE_X = "SizeX";
    public static final String ATTR_SIZE_Y = "SizeY";
    public static final String ATTR_SIZE_XG = "SizeXg";
    public static final String ATTR_SIZE_YG = "SizeYg";
    public static final String ELEM_LEFT_EDGE = "LeftEdge";
    public static final String ELEM_RIGHT_EDGE = "RightEdge";
    public static final String ELEM_TOP_EDGE = "TopEdge";
    public static final String ELEM_BOTTOM_EDGE = "BottomEdge";

    private String name;
    private String type;
    private String code;
    private String matClass;
    private String matName;
    private String matId;
    private String sizeH;
    private String sizeX;
    private String sizeY;
    private String sizeXg;
    private String sizeYg;
    private Edge leftEdge;
    private Edge rightEdge;
    private Edge topEdge;
    private Edge bottomEdge;
    private Holes holes;
    private CPolyLine cPolyLine;

    public Part() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getSizeH() {
        return sizeH;
    }

    public void setSizeH(String sizeH) {
        this.sizeH = sizeH;
    }

    public String getSizeX() {
        return sizeX;
    }

    public void setSizeX(String sizeX) {
        this.sizeX = sizeX;
    }

    public String getSizeY() {
        return sizeY;
    }

    public void setSizeY(String sizeY) {
        this.sizeY = sizeY;
    }

    public String getSizeXg() {
        return sizeXg;
    }

    public void setSizeXg(String sizeXg) {
        this.sizeXg = sizeXg;
    }

    public String getSizeYg() {
        return sizeYg;
    }

    public void setSizeYg(String sizeYg) {
        this.sizeYg = sizeYg;
    }

    public Edge getLeftEdge() {
        return leftEdge;
    }

    public void setLeftEdge(Edge leftEdge) {
        this.leftEdge = leftEdge;
    }

    public Edge getRightEdge() {
        return rightEdge;
    }

    public void setRightEdge(Edge rightEdge) {
        this.rightEdge = rightEdge;
    }

    public Edge getTopEdge() {
        return topEdge;
    }

    public void setTopEdge(Edge topEdge) {
        this.topEdge = topEdge;
    }

    public Edge getBottomEdge() {
        return bottomEdge;
    }

    public void setBottomEdge(Edge bottomEdge) {
        this.bottomEdge = bottomEdge;
    }

    public Holes getHoles() {
        return holes;
    }

    public void setHoles(Holes holes) {
        this.holes = holes;
    }

    public CPolyLine getcPolyLine() {
        return cPolyLine;
    }

    public void setcPolyLine(CPolyLine cPolyLine) {
        this.cPolyLine = cPolyLine;
    }

    @Override
    public String toString(){
        String str = "<Part " + ATTR_NAME + "=\"" + name + "\" " +
                ATTR_TYPE + "=\"" + type + "\" " +
                ATTR_CODE + "=\"" + code + "\" " +
                ATTR_MATCLASS + "=\"" + matClass + "\" " +
                ATTR_MATNAME + "=\"" + matName + "\" " +
                ATTR_MAT_ID + "=\"" + matId + "\" " +
                ATTR_SIZE_H + "=\"" + sizeH + "\" " +
                ATTR_SIZE_X + "=\"" + sizeX + "\" " +
                ATTR_SIZE_Y + "=\"" + sizeY + "\" " +
                ATTR_SIZE_XG + "=\"" + sizeXg + "\" " +
                ATTR_SIZE_YG + "=\"" + sizeYg + "\">\n ";


        StringBuilder sb = new StringBuilder(str);
        if (leftEdge != null){
            sb.append("<LeftEdge " + leftEdge.toString() + "/>\n");
        }
        if (rightEdge != null){
            sb.append("<RightEdge " + rightEdge.toString() + "/>\n");
        }if (topEdge != null){
            sb.append("<TopEdge " + topEdge.toString() + "/>\n");
        }if (bottomEdge != null){
            sb.append("<BottomEdge " + bottomEdge.toString() + "/>\n");
        }
        if (holes != null){
            sb.append(holes.toString());
        }
        if (cPolyLine != null) {
            sb.append(cPolyLine.toString());
        }
        sb.append("</Part>\n");

        return sb.toString();



    }
}
