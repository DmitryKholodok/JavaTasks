package by.kholodok.task1.action.impl;

import by.kholodok.task1.action.QuadrProperty;
import by.kholodok.task1.action.ShapeCalculation;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.entity.Vector;
import by.kholodok.task1.exception.ShapeException;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.geom.Point2D;

public class QuadrAction implements QuadrProperty, ShapeCalculation {

    private final static Logger LOGGER = LogManager.getLogger(QuadrAction.class);

    @Override
    public double calcArea(Entity entity) throws ShapeException {
        LOGGER.log(Level.DEBUG, "method : calcArea, entity - " + entity.toString());
        if (!isQuadr(entity))
            throw new ShapeException(entity.toString() + " is not a quadrilateral!");

        Point[] points = ((Quadrilateral)entity).getPoints();
        double[] determinants = getSecondOrderDeterms(points);
        double sum = calcSum(determinants) / 2;
        return sum > 0 ? sum : -sum;
    }

    @Override
    public double calcPerimeter(Entity entity) throws ShapeException {
        LOGGER.log(Level.DEBUG, "method : calcPerimeter, entity - " + entity.toString());
        if (!isQuadr(entity))
            throw new ShapeException(entity.toString() + " is not a quadrilateral!");

        double result = 0;
        double[] distances = calcDistances(((Quadrilateral)entity).getPoints());
        for(int i = 0; i < Quadrilateral.SIDES_COUNT; i++)
            result += distances[i];
        return result;
    }

    @Override
    public boolean isConvex(Entity entity) throws ShapeException {
        LOGGER.log(Level.DEBUG, "method : isConvex, entity - " + entity.toString());
        if (!isQuadr(entity))
            throw new ShapeException(entity.toString() + " is not an quadrilateral!");

        Point[] points = ((Quadrilateral)entity).getPoints();
        double[] vectorsWork = calcVectorWorks(points);
        return areAllSignsTheSame(vectorsWork) ? true : false;
    }

    @Override
    public boolean isQuadr(Entity entity) {
        LOGGER.log(Level.DEBUG, "method : isQuadr, entity - " + entity.toString());
        Point[] points = ((Quadrilateral)entity).getPoints();
        return isShapeQuadr(points);
    }

    @Override
    public boolean isSquare(Entity entity) throws ShapeException {
        LOGGER.log(Level.DEBUG, "method : isSquare, entity - " + entity.toString());
        if (!isQuadr(entity))
            throw new ShapeException(entity.toString() + " is not a quadrilateral!");

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
        int i = 0;
        while(i < nums.length - 1) {
            if (!isTheSameSigns(nums[i], nums[i + 1]))
                return false;
            i++;
        }
        return true;
    }

    private boolean isTheSameSigns(double num1, double num2) { // num1 != 0 && num2 != 0
        return (num1 * num2 > 0) ? true :  false;
    }

    private double[] calcVectorWorks(Point[] p) {
        double[] vectorWorks = new double[Quadrilateral.SIDES_COUNT];
        MathVectorAction vectorAction = new MathVectorAction();
        for(int i = 0; i < Quadrilateral.SIDES_COUNT; i++) {
            Vector vector1 = new Vector(p[calcIndex(i)], p[calcIndex(i + 1)]);
            Vector vector2 = new Vector(p[calcIndex(i)], p[calcIndex(i + 2)]);
            vectorWorks[i] = vectorAction.calculateVectorsWork(vector1, vector2);
        }
        return vectorWorks;
    }

    private int calcIndex(int i) {
        return i % Quadrilateral.SIDES_COUNT;
    }

    private boolean isShapeQuadr(Point[] p) {
        return (isOnOneLine(p[0], p[2], p[1]) || isOnOneLine(p[0], p[2], p[3]) || isOnOneLine(p[1], p[3], p[0]) || isOnOneLine(p[1], p[3], p[2]));
    }

    private boolean isOnOneLine(Point p1, Point p2, Point testP) {
        double xRes = (testP.getX() - p1.getX()) / (p2.getX() - p1.getX());
        double yRes = (testP.getY() - p1.getY()) / (p2.getY() - p1.getY());
        return xRes != yRes;
    }

    private boolean isAllDistEqual(double[] mass) {
        int i = 0;
        while(i++ < mass.length - 1) {
            if (mass[i] != mass[mass.length - 1])
                return false;
        }
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
