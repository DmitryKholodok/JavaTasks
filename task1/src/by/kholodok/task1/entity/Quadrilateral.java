package by.kholodok.task1.entity;

public class Quadrilateral extends Shape {

    public final static int SIDES_COUNT = 4;

    private Point[] points;

    public Quadrilateral(Point[] points) {

        this.points = points;
    }

    public Point[] getPoints() { return points; }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        for (Point p : points) {
            result = result * prime + (int) p.getX();
            result = result * prime + (int) p.getY();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Point[] points = ((Quadrilateral) obj).getPoints();
        for(int i = 0; i < points.length; i++)
            if (!points[i].equals(this.points[i]))
                return false;
        return true;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Point p: points) {
            sb.append("(").append(p.getX()).append(", ").append(p.getY()).append(") ");
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }

}
