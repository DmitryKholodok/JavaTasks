package by.kholodok.task1.action.impl;

import by.kholodok.task1.action.Action;
import by.kholodok.task1.action.exception.NotQuadrException;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;

import java.awt.geom.Point2D;

public class QuadrAction implements Action {

    private class VectorAction {

        double calcVectorWork(Point vector1, Point vector2) {
            return vector1.getX() * vector2.getY() - vector2.getX() * vector1.getY();
        }

        double calcVectorWork(Point pCenter, Point p1, Point p2) {
            Point vector1 = calcVector(pCenter, p1);
            Point vector2 = calcVector(pCenter, p2);
            return calcVectorWork(vector1, vector2);
        }

        Point calcVector(Point p1, Point p2) {
            return new Point(p1.getX() - p2.getX(), p1.getY() - p2.getY());
        }
    }

    @Override
    public double calcArea(Quadrilateral quadr) throws NotQuadrException {
        if (!isQuadr(quadr))
            throw new NotQuadrException(quadr.toString() + " is not a quadrilateral!");
        Point[] points = quadr.getPoints();
        double[] determinants = getSecondOrderDeterms(points);
        double sum = calcSum(determinants) / 2;
        return sum > 0 ? sum : -sum;
    }

    @Override
    public double calcPerimeter(Quadrilateral quadr) throws NotQuadrException {
        if (!isQuadr(quadr))
            throw new NotQuadrException(quadr.toString() + " is not a quadrilateral!");
        double result = 0;
        double[] distances = calcDistances(quadr.getPoints());
        for(int i = 0; i < Quadrilateral.SIDES_COUNT; i++)
            result += distances[i];
        return result;
    }

    @Override
    public boolean isConvex(Quadrilateral quadr) throws NotQuadrException {
        if (!isQuadr(quadr))
            throw new NotQuadrException(quadr.toString() + " is not a quadrilateral!");
        Point[] points = quadr.getPoints();
        double[] vectorsWork = calcVectorWorks(points);
        return areAllSignsTheSame(vectorsWork) ? true : false;
    }

    @Override
    public boolean isQuadr(Quadrilateral quadr) { // 3 points on the one line
        Point[] points = quadr.getPoints();
        return isShapeQuadr(points) ? true : false;
    }

    @Override
    public boolean isSquare(Quadrilateral quadr) throws NotQuadrException {
        if (!isQuadr(quadr))
            throw new NotQuadrException(quadr.toString() + " is not a quadrilateral!");
        double[] distances = calcDistances(quadr.getPoints());
        return isAllDistEqual(distances) ?  true : false;
    }

    private double calcSum(double[] nums) {
        double sum = 0;
        for(int i = 0; i < nums.length; i++)
            sum += nums[i];
        return sum;
    }

    private double[] getSecondOrderDeterms(Point[] p) {
        double[] determinants = new double[Quadrilateral.SIDES_COUNT];
        determinants[0] = getSecondOrderDeterm(p[0], p[1]);
        determinants[1] = getSecondOrderDeterm(p[1], p[2]);
        determinants[2] = getSecondOrderDeterm(p[2], p[3]);
        determinants[3] = getSecondOrderDeterm(p[3], p[0]);
        return determinants;
    }

    private double getSecondOrderDeterm(Point p1, Point p2) {
        return p1.getX() * p2.getY() - p2.getX() * p1.getY();
    }

    private boolean areAllSignsTheSame(double[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            if (!isTheSameSigns(nums[i], nums[i + 1]))
                return false;
        }
        return true;
    }

    private boolean isTheSameSigns(double num1, double num2) { // num1 != 0 && num2 != 0
        return (num1 * num2 > 0) ? true :  false;
    }

    private double[] calcVectorWorks(Point[] p) {
        VectorAction vectorAction = new VectorAction();
        double[] vectorWorks = new double[Quadrilateral.SIDES_COUNT];
        vectorWorks[0] = vectorAction.calcVectorWork(p[1], p[0], p[2]);
        vectorWorks[1] = vectorAction.calcVectorWork(p[2], p[1], p[3]);
        vectorWorks[2] = vectorAction.calcVectorWork(p[3], p[2], p[0]);
        vectorWorks[3] = vectorAction.calcVectorWork(p[0], p[3], p[1]);
        return vectorWorks;
    }

    private boolean isShapeQuadr(Point[] p) {
        if (isOnOneLine(p[0], p[2], p[1])) return false;
        if (isOnOneLine(p[0], p[2], p[3])) return false;
        if (isOnOneLine(p[1], p[3], p[0])) return false;
        if (isOnOneLine(p[1], p[3], p[2])) return false;
        return true;
    }

    private boolean isOnOneLine(Point p1, Point p2, Point testP) {
        double xRes = (testP.getX() - p1.getX()) / (p2.getX() - p1.getX());
        double yRes = (testP.getY() - p1.getY()) / (p2.getY() - p1.getY());
        return (xRes == yRes) ? true : false;
    }

    private boolean isAllDistEqual(double[] mass) {
        for(int i = 0; i < mass.length - 1; i++)
            if (mass[i] != mass[mass.length - 1])
                return false;
        return true;
    }

    private double calcDistance(Point a, Point b) {
        return Point2D.distance(a.getX(), a.getY(), b.getX(), b.getY());
    }

    private double[] calcDistances(Point[] points) {
        double[] distances = new double[Quadrilateral.SIDES_COUNT];
        for(int i = 0; i < 3; i++)
            distances[i] += calcDistance(points[i], points[i + 1]);
        distances[3] += calcDistance(points[3], points[0]);
        return distances;
    }
}
