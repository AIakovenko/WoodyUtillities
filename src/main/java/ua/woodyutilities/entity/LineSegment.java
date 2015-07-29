package ua.woodyutilities.entity;

import java.io.Serializable;

/**
 * @autor Alex Iakovenko
 * Date: 7/28/15
 * Time: 12:58 PM
 */
public class LineSegment implements Serializable {
    public static final String ATTR_PROPERTY = "property";
    public static final String ELEM_EDGE = "Edge";
    public static final String ELEM_START_POINT = "StartPoint";
    public static final String ELEM_END_POINT = "EndPoint";


    private String property;
    private Edge edge;
    private Point startPoint;
    private Point endPoint;

    public LineSegment() {
    }

    public LineSegment(String property, Edge edge, Point startPoint, Point endPoint) {
        this.property = property;
        this.edge = edge;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        String attr = "<LineSegment " + ATTR_PROPERTY + "=\"" + property + "\">\n";
        StringBuilder sb = new StringBuilder(attr);
        if (edge != null) {
            String strEdge = "<Edge " + edge.toString() + "/>\n";
            sb.append(strEdge);
        }
        if (startPoint != null){
            sb.append("<StartPoint>\n");
            String strPoint = "<Point x=\"" + startPoint.getPoint().getX() + "\" " +
                    "y=\"" + startPoint.getPoint().getY() + "\" " +
                    "z=\"" + startPoint.getPoint().getZ() + "\" />\n";

            sb.append(strPoint);
            sb.append("</StartPoint>\n");
        }
        if (endPoint != null){
            sb.append("<EndPoint>\n");
            String strPoint = "<Point x=\"" + endPoint.getPoint().getX() + "\" " +
                    "y=\"" + endPoint.getPoint().getY() + "\" " +
                    "z=\"" + endPoint.getPoint().getZ() + "\" />\n";

            sb.append(strPoint);
            sb.append("</EndPoint>\n");
            sb.append("</LineSegment>\n");
        }

        return sb.toString();
    }
}
