package ua.woodyutilities.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 7/28/15
 * Time: 12:54 PM
 */
public class CPolyLine implements Serializable {
    public static final String ATTR_TYPE = "type";
    public static final String ELEM_LINE_SEGMENT = "LineSegment";

    private String type;
    private List<LineSegment> lineSegments;

    public CPolyLine() {
    }

    public CPolyLine(String type, List<LineSegment> lineSegments) {
        this.type = type;
        this.lineSegments = lineSegments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<LineSegment> getLineSegments() {
        return lineSegments;
    }

    public void setLineSegments(List<LineSegment> lineSegments) {
        this.lineSegments = lineSegments;
    }

    @Override
    public String toString() {
        String attr = "<CPolyLine " + ATTR_TYPE +"=\"" + type + "\">\n";
        StringBuilder sb = new StringBuilder(attr);
        lineSegments.forEach(lineSegment -> {
            sb.append(lineSegment.toString());
        });
        sb.append("</CPolyLine>\n");
        return sb.toString();
    }
}
