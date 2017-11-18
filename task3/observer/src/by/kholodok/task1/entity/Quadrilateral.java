package by.kholodok.task1.entity;

import by.kholodok.task1.collection.ObserverCollection;
import by.kholodok.task1.observer.Observer;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quadrilateral extends Entity implements Observable {

    private long quadrId;
    private Point[] points;

    public final static int SIDES_COUNT = 4;

    public Quadrilateral(Point[] points) {
        this.points = points;
        quadrId = IdGenerator.getId();
    }

    public void setPoints(Point[] points) {
        this.points = points;
        notifyObservers();
    }

    public long getQuadrId() {
        return quadrId;
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

    @Override
    public void addObserver(Observer observer) {
        ObserverCollection.getInstance().addObserver(this.quadrId, observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        ObserverCollection.getInstance().removeObserver(this.quadrId, observer);
    }

    @Override
    public void notifyObservers() {
        Iterator<Observer> observerIterator = ObserverCollection.getInstance().observers(quadrId).iterator();
        while(observerIterator.hasNext()) {
            observerIterator.next().update(this);
        }
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
}
