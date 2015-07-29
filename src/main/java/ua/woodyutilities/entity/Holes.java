package ua.woodyutilities.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 7/28/15
 * Time: 12:41 PM
 */
public class Holes implements Serializable {
    private List<Drill> drills;

    public Holes() {
    }

    public Holes(List<Drill> drills) {
        this.drills = drills;
    }

    public List<Drill> getDrills() {
        return drills;
    }

    public void setDrills(List<Drill> drills) {
        this.drills = drills;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<Holes>\n");
        drills.forEach(drill ->{
            sb.append(drill.toString());
        });
        sb.append("</Holes>\n");

        return sb.toString();
    }
}
