package ua.woodyutilities.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @autor Alex Iakovenko
 * Date: 7/14/15
 * Time: 4:23 PM
 */
public class PartDTO implements Serializable {
    public static final Integer LEFT_EDGE = 1;
    public static final Integer BOTTOM_EDGE = 2;
    public static final Integer RIGHT_EDGE = 3;
    public static final Integer TOP_EDGE = 4;

    private static final int EDGES_NUMBER = 4;

    private String name;
    private String matName;
    private Double thickness;
    private Double width;
    private Double height;
    private Double totalWidth;
    private Double totalHeight;

    private Map<Integer, Edgeband> edge;

    public PartDTO(){
        edge = new HashMap<Integer, Edgeband>(EDGES_NUMBER);
        edge.put(LEFT_EDGE, new Edgeband());
        edge.put(BOTTOM_EDGE, new Edgeband());
        edge.put(RIGHT_EDGE, new Edgeband());
        edge.put(TOP_EDGE, new Edgeband());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getTotalWidth() {
        return totalWidth;
    }

    public void setTotalWidth(Double totalWidth) {
        this.totalWidth = totalWidth;
    }

    public Double getTotalHeight() {
        return totalHeight;
    }

    public void setTotalHeight(Double totalHeight) {
        this.totalHeight = totalHeight;
    }

    public Edgeband getEdge(Integer side) {
        return edge.get(side);
    }

    public void putEdge(Integer side, Edgeband edgeband) {
        if (edge.containsKey(side)){
            edge.replace(side, edgeband);
        }
    }

    public Collection<Edgeband> getEdge(){
        return edge.values();
    }
}
