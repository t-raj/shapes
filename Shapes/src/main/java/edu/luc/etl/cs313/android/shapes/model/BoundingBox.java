package edu.luc.etl.cs313.android.shapes.model;

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
		return new Location(0,0, new Fill(shape));
	}

	@Override
	public Location onGroup(final Group g) {

        final List<? extends Shape> shapes = g.getShapes();
        return  shapes.
	}

	@Override
	public Location onLocation(final Location l) {
        final int x = l.getX();
        final int y = l.getY();
        final Shape shape = l.getShape();
        return new Location(0,0, new Location(x, y, shape));
	}

	@Override
	public Location onRectangle(final Rectangle r) {
		final int width = r.getWidth();
        final int height = r.getHeight();
        return new Location(0,0, new Rectangle(width, height));
	}

	@Override
	public Location onStroke(final Stroke c) {

        final int color = c.getColor();
        final Shape shape = c.getShape();
        return new Location(0,0, new Stroke(color, shape));
	}

	@Override
	public Location onOutline(final Outline o) {

        final Shape shape = o.getShape();
        return new Location(0,0, new Outline(shape));
	}

	@Override
	public Location onPolygon(final Polygon s) {

        final List<? extends Point> points = s.getPoints();
        int x = points.get(0).getX();
        int y = points.get(0).getY();
        return new Location(x, y, s);
	}
}
//