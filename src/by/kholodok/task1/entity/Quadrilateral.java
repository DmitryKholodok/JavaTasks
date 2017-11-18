package by.kholodok.task1.entity;

import by.kholodok.task1.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Quadrilateral extends Entity implements Observable {

    public final static int SIDES_COUNT = 4;

    private List<Observer> observers = new ArrayList<>();

    private Point[] points;

    public Quadrilateral(Point[] points) {

        this.points = points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
        notifyObservers();
    }

    public Point[] getPoints() { return createNewPoints(); }

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

    private Point[] createNewPoints() {
        Point[] points = new Point[this.points.length];
        try {
            for (int i = 0; i < points.length; i++) {
                points[i] = (Point)this.points[i].clone();
            }
        } catch (CloneNotSupportedException e)  {
            return null;
        }
        return points;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
