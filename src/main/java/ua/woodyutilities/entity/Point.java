package ua.woodyutilities.entity;

import java.io.Serializable;

/**
 * @autor Alex Iakovenko
 * Date: 7/28/15
 * Time: 1:00 PM
 */
public class Point implements Serializable {
    private Coordinates point;

    public Point() {

    }

    public Point(Coordinates point) {
        this.point = point;
    }

    public Coordinates getPoint() {
        return point;
    }

    public void setPoint(Coordinates point) {
        this.point = point;
    }
}
