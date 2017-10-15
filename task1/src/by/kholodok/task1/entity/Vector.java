package by.kholodok.task1.entity;

public class Vector extends Entity {

    private Point beginP;
    private Point endP;

    public Vector(Point pBegin, Point pEnd) {
        this.beginP = pBegin;
        this.endP = pEnd;
    }

    public Point getBeginP() {
        return new Point(beginP.getX(), beginP.getY());
    }

    public Point getEndP() {
        return new Point(endP.getX(), endP.getY());
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int result = 1;
        result = result * prime + beginP.hashCode();
        return result = result * prime + endP.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Vector p = (Vector) obj;
        return (p.getBeginP() == this.beginP && p.getEndP() == this.endP);

    }

    @Override
    public String toString() {
        return "Vector{" + "pBegin=" + beginP + ", pEnd=" + endP + '}';
    }
}
