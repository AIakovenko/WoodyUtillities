package ua.woodyutilities.entity;

import java.io.Serializable;

/**
 * @autor Alex Iakovenko
 * Date: 7/28/15
 * Time: 12:43 PM
 */
public class Drill implements Serializable {
    public static final String ATTR_DIAMETER = "Diameter";
    public static final String ATTR_DEPTH = "Depth";
    public static final String ATTR_COUNTERSINK_IN = "CountersinkIn";
    public static final String ELEM_VECTOR = "Vector";
    public static final String ELEM_POINT = "Point";


    private String diameter;
    private String depth;
    private String countersinkIn;
    private Coordinates vector;
    private Coordinates point;

    public Drill(){}

    public Drill(String diameter, String depth, String countersinkIn, Coordinates vector, Coordinates point) {
        this.diameter = diameter;
        this.depth = depth;
        this.countersinkIn = countersinkIn;
        this.vector = vector;
        this.point = point;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getCountersinkIn() {
        return countersinkIn;
    }

    public void setCountersinkIn(String countersinkIn) {
        this.countersinkIn = countersinkIn;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public Coordinates getVector() {
        return vector;
    }

    public void setVector(Coordinates vector) {
        this.vector = vector;
    }

    public Coordinates getPoint() {
        return point;
    }

    public void setPoint(Coordinates point) {
        this.point = point;
    }

    @Override
    public String toString() {
        String attr = "<Drill " + ATTR_DIAMETER + "=\"" + diameter + "\" ";
        if ( !countersinkIn.equals("")){
            attr += ATTR_COUNTERSINK_IN + "=\"" + countersinkIn + "\" ";
        }
        attr += ATTR_DEPTH + "=\"" + depth + "\">\n";

        if (vector != null){
            attr += "<Vector x=\"" + vector.getX() + "\" " +
                    "y=\"" + vector.getY() + "\" " +
                    "z=\"" + vector.getZ() + "\" />\n";
        }
        if (point != null){
            attr += "<Point x=\"" + point.getX() + "\" " +
                    "y=\"" + point.getY() + "\" " +
                    "z=\"" + point.getZ() + "\" />\n";
        }

        attr += "</Drill>\n";

        return attr;
    }
}
