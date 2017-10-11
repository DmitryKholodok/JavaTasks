package tests.action;

import by.kholodok.task1.exception.NotQuadrException;
import by.kholodok.task1.action.impl.QuadrAction;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class QuadrActionTest {

    private final QuadrAction quadrAction = new QuadrAction();
    private Quadrilateral square;
    private Quadrilateral convexQuadr;
    private Quadrilateral unConvexQuadr;
    private Quadrilateral notQuadr;

    @BeforeClass
    private void QuadrInit() {

        Point[] points = new Point[4];
        points[0] = new Point(1, 1);
        points[1] = new Point(1, 3);
        points[2] = new Point(3, 3);
        points[3] = new Point(3, 1);
        square = new Quadrilateral(points);

        points = new Point[4];
        points[0] = new Point(1, 1);
        points[1] = new Point(3, 5);
        points[2] = new Point(7, 7);
        points[3] = new Point(7, 1);
        convexQuadr = new Quadrilateral(points);

        points = new Point[4];
        points[0] = new Point(-2, -2);
        points[1] = new Point(1, 2);
        points[2] = new Point(1, -1);
        points[3] = new Point(5, 0);
        unConvexQuadr = new Quadrilateral(points);

        points = new Point[4];
        points[0] = new Point(-5, -3);
        points[1] = new Point(-3, -1);
        points[2] = new Point(2, 4);
        points[3] = new Point(3, -3);
        notQuadr = new Quadrilateral(points);

    }

    @Test
    public void testSquarecalcPerimeter() throws NotQuadrException {
        Assert.assertEquals(quadrAction.calcPerimeter(square), 8.0);
    }

    @Test
    public void testSquareIsQuadr() {
        Assert.assertEquals(quadrAction.isQuadr(square), true);
    }

    @Test
    public void testSquareIsConvex() throws NotQuadrException {
        Assert.assertEquals(quadrAction.isConvex(square), true);
    }

    @Test
    public void testSquarecalcArea() throws NotQuadrException {
        Assert.assertEquals(quadrAction.calcArea(square), 4.0);
    }

    @Test
    public void testSquareIsSquare() throws NotQuadrException {
        Assert.assertEquals(quadrAction.isSquare(square), true);
    }

    @Test
    public void testConvexQuadrcalcPerimeter() throws NotQuadrException {
        Assert.assertEquals(quadrAction.calcPerimeter(convexQuadr), 20.94, 0.01);
    }

    @Test
    public void testConvexQuadrIsQuadr() { Assert.assertEquals(quadrAction.isQuadr(convexQuadr), true); }

    @Test
    public void testConvexQuadrIsConvex() throws NotQuadrException {
        Assert.assertEquals(quadrAction.isConvex(convexQuadr), true);
    }

    @Test
    public void testConvexQuadrcalcArea() throws NotQuadrException {
        Assert.assertEquals(quadrAction.calcArea(convexQuadr), 24.0);
    }

    @Test
    public void testConvexQuadrIsSquare() throws NotQuadrException {
        Assert.assertEquals(quadrAction.isSquare(convexQuadr), false);
    }

    @Test
    public void testUnConvexQuadrcalcPerimeter() throws NotQuadrException {
        Assert.assertEquals(quadrAction.calcPerimeter(unConvexQuadr), 19.4, 0.1);
    }

    @Test
    public void testUnConvexQuadrIsQuadr() { Assert.assertEquals(quadrAction.isQuadr(unConvexQuadr), true); }

    @Test
    public void testUnConvexQuadIsConvex() throws NotQuadrException {
        Assert.assertEquals(quadrAction.isConvex(unConvexQuadr), false);
    }                                                                                                                                        

    @Test
    public void testUnConvexQuadrcalcArea() throws NotQuadrException {
        Assert.assertEquals(quadrAction.calcArea(unConvexQuadr), 5.0, 0.1);
    }

    @Test
    public void testUnConvexQuadrIsSquare() throws NotQuadrException {
        Assert.assertEquals(quadrAction.isSquare(unConvexQuadr), false);
    }

    @Test(expectedExceptions = NotQuadrException.class)
    public void testNotQuadrcalcPerimeter() throws NotQuadrException {
        quadrAction.calcPerimeter(notQuadr);
    }

    @Test
    public void testNotQuadrIsQuadr() { Assert.assertEquals(quadrAction.isQuadr(notQuadr), false); }

    @Test(expectedExceptions = NotQuadrException.class)
    public void testNotQuadIsConvex() throws NotQuadrException {
        Assert.assertEquals(quadrAction.isConvex(notQuadr), false);
    }

    @Test(expectedExceptions = NotQuadrException.class)
    public void testNotQuadrcalcArea() throws NotQuadrException {
       quadrAction.calcArea(notQuadr) ;
    }

    @Test(expectedExceptions = NotQuadrException.class)
    public void testNotQuadrIsSquare() throws NotQuadrException {
        quadrAction.isSquare(notQuadr);
    }


}
