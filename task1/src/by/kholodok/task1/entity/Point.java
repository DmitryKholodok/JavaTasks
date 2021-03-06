package by.kholodok.task1.entity;

public class Point extends Entity implements Cloneable {

    private int pointId;

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public int getPointId() {
        return pointId;
    }
    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + (int) x;
        return result = result * prime + (int) y;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Point p = (Point) obj;
        return (p.x == this.x && p.y == this.y);

    }

    @Override
    public String toString() {
        return "(" + String.valueOf(x) + ", " + String.valueOf(y) + ")";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
