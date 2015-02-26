package edu.luc.etl.cs313.android.shapes.model;

/**
 * A point, implemented as a location without a shape.
 */
public class Point extends Location {
    protected final int radius = 0;
    final Shape point = new Circle(radius);

	// TODO your job
	// HINT: use a circle with radius 0 as the shape!

	public Point(final int x, final int y) {
		super(-1, -1, null);
		assert x >= 0;
		assert y >= 0;
    }

    public int getRadius() {
        final int radius = 0;
        return radius;
    }

    @Override
    public <Result> Result accept(final Visitor<Result> v) {
        return v.onLocation(this);
    }
}

