package edu.luc.etl.cs313.android.shapes.model;

import java.util.Iterator;
import java.util.List;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

	// TODO entirely your job (except onCircle)

	@Override
	public Location onCircle(final Circle c) {
		final int radius = c.getRadius();
		return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
	}

	@Override
	public Location onFill(final Fill f) {
        final Shape shape = f.getShape();
		return shape.accept(this);
	}

	@Override
	public Location onGroup(final Group g) {

        final Iterator<? extends Shape> list = g.getShapes().iterator();

        int Xmax = 0;
        int Ymax = 0;

        int Xmin = Integer.MAX_VALUE;
        int Ymin = Integer.MAX_VALUE;

        int h;
        int w;

        while (list.hasNext()) {
            final Location location = list.next().accept(this);

            int x = location.getX();
            int y = location.getY();
            if (location.getShape() instanceof Rectangle) {
                w = x;
                h = y;
                w = w + ((Rectangle) location.getShape()).getWidth();
                h = h+ ((Rectangle) location.getShape()).getHeight();
            }
            if (Xmin >= x) {Xmin = x;}
            if (Ymin >= y) {Ymin = y;}
            if (Xmax <= w) {Xmax = ((Rectangle) location.getShape()).getWidth();}
            if (Ymax <= h) {Ymax = ((Rectangle) location.getShape()).getHeight();}
        }
        return new Location(Xmin, Ymin, new Rectangle((Xmax - Ymax),
                (Xmax - Ymax)));
    }


	@Override
	public Location onLocation(final Location l) {
        Location mylocation = l.getShape().accept(this);
        final int x = l.getX() + mylocation.getX();
        final int y = l.getY() + mylocation.getY();
        return new Location(x,y, mylocation.getShape());
	}

	@Override
	public Location onRectangle(final Rectangle r) {
        return new Location(0,0,r);
	}

	@Override
	public Location onStroke(final Stroke c) {
        return c.getShape().accept(this);
	}

	@Override
	public Location onOutline(final Outline o) {
        return o.getShape().accept(this);
	}

	@Override
	public Location onPolygon(final Polygon s) {
        final List<? extends Point> points = s.getPoints();
        int x = points.get(0).getX();
        int y = points.get(0).getY();
        return new Location(x, y, s);
	}
}
