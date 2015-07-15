package ua.woodyutilities.dto;

import org.w3c.dom.Document;
import ua.woodyutilities.util.LocalizationManager;

import java.io.Serializable;

/**
 * @autor Alex Iakovenko
 * Date: 7/14/15
 * Time: 4:30 PM
 */
public class Edgeband implements Serializable, Comparable<Edgeband> {
    private static final LocalizationManager LM = LocalizationManager.getInstance();
    private Long materialId;
    private String name;
    private Double thickness;
    private Integer width;

    public Edgeband(){
        materialId = 0l;
        name = LM.getProperty("TITLE_NEW_EDGEBAND");
        thickness = 0.35;
        width = 22;
    }
    public Edgeband(Long materialId, String name, Double thickness, Integer width){
        this.materialId = materialId;
        this.name = name;
        this.thickness = thickness;
        this.width = width;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edgeband edgeband = (Edgeband) o;

        if (name != null ? !name.equals(edgeband.name) : edgeband.name != null) return false;
        if (thickness != null ? !thickness.equals(edgeband.thickness) : edgeband.thickness != null) return false;
        if (width != null ? !width.equals(edgeband.width) : edgeband.width != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (thickness != null ? thickness.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Edgeband edgeband) {
        return name.compareTo(edgeband.name);
    }
}
