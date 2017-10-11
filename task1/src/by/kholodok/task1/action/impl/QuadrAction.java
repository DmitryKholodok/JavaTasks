package by.kholodok.task1.action.impl;

import by.kholodok.task1.action.Action;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.exception.NotQuadrException;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.geom.Point2D;

public class QuadrAction implements Action {

    private static Logger logger = LogManager.getLogger(QuadrAction.class);

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
    public double calcArea(Entity entity) throws NotQuadrException {
        logger.log(Level.DEBUG, "method : calcArea, entity - " + entity.toString());
        if (!isQuadr(entity))
            throw new NotQuadrException(entity.toString() + " is not a quadrilateral!");

        Point[] points = ((Quadrilateral)entity).getPoints();
        double[] determinants = getSecondOrderDeterms(points);
        double sum = calcSum(determinants) / 2;
        return sum > 0 ? sum : -sum;
    }

    @Override
    public double calcPerimeter(Entity entity) throws NotQuadrException {
        logger.log(Level.DEBUG, "method : calcPerimeter, entity - " + entity.toString());
        if (!isQuadr(entity))
            throw new NotQuadrException(entity.toString() + " is not a quadrilateral!");

        double result = 0;
        double[] distances = calcDistances(((Quadrilateral)entity).getPoints());
        for(int i = 0; i < Quadrilateral.SIDES_COUNT; i++)
            result += distances[i];
        return result;
    }

    @Override
    public boolean isConvex(Entity entity) throws NotQuadrException {
        logger.log(Level.DEBUG, "method : isConvex, entity - " + entity.toString());
        if (!isQuadr(entity))
            throw new NotQuadrException(entity.toString() + " is not an quadrilateral!");

        Point[] points = ((Quadrilateral)entity).getPoints();
        double[] vectorsWork = calcVectorWorks(points);
        return areAllSignsTheSame(vectorsWork) ? true : false;
    }

    @Override
    public boolean isQuadr(Entity entity) {
        logger.log(Level.DEBUG, "method : isQuadr, entity - " + entity.toString());
        Point[] points = ((Quadrilateral)entity).getPoints();
        return isShapeQuadr(points) ? true : false;
    }

    @Override
    public boolean isSquare(Entity entity) throws NotQuadrException {
        logger.log(Level.DEBUG, "method : isSquare, entity - " + entity.toString());
        if (!isQuadr(entity))
            throw new NotQuadrException(entity.toString() + " is not a quadrilateral!");

        double[] distances = calcDistances(((Quadrilateral)entity).getPoints());
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
        for(int i = 0; i < Quadrilateral.SIDES_COUNT; i++) {
            determinants[i] = getSecondOrderDeterm(p[i], p[calcIndex(i + 1)]);
        }
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
        for(int i = 0; i < Quadrilateral.SIDES_COUNT; i++) {
            vectorWorks[i] = vectorAction.calcVectorWork(p[calcIndex(i + 1)], p[calcIndex(i)], p[calcIndex(i + 2)]);
        }
        return vectorWorks;
    }

    private int calcIndex(int i) {
        return i % Quadrilateral.SIDES_COUNT;
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
        for(int i = 0; i < Quadrilateral.SIDES_COUNT; i++)
            distances[i] += calcDistance(points[i], points[calcIndex(i + 1)]);
        return distances;
    }
}
