package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import java.util.Iterator;
import java.util.List;

import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

	// TODO entirely your job (except onCircle)

	private final Canvas canvas;

	private final Paint paint;

	public Draw(final Canvas canvas, final Paint paint) {
		this.canvas = canvas; // FIXME
		this.paint = paint; // FIXME
		paint.setStyle(Style.STROKE);
	}

	@Override
	public Void onCircle(final Circle c) {
		canvas.drawCircle(0, 0, c.getRadius(), paint);
		return null;
	}

	@Override
	public Void onStroke(final Stroke c) {

        int color;
        color = paint.getColor();
        paint.setColor(c.getColor());
        paint.setColor(color);

        c.getShape().accept(this);

		return null;
	}

	@Override
	public Void onFill(final Fill f) {
        final Style style = paint.getStyle();
        paint.setStyle(style.FILL_AND_STROKE); //this part was confusing to me until...
        //...I took a better look at the test!
        paint.setStyle(style);

        f.getShape().accept(this);

		return null;
	}

	@Override
	public Void onGroup(final Group g) {
        //need to iterate so that simple group and complex group will work
        Iterator<?extends Shape> shape = g.getShapes().iterator();
        while (shape.hasNext())
        {
            shape.next().accept(this);
        }

        return null;
	}

	@Override
	public Void onLocation(final Location l) {
        float x = l.getX();
        float y = l.getY();
        //another place where it really helped to look at the tests!
        canvas.translate(x,y);
        canvas.translate(-x,-y);

        l.getShape().accept(this); //recently had a break through in figuring out...
        //...how to use "this"...better late than never figuring it out!

		return null;
	}

	@Override
	public Void onRectangle(final Rectangle r) {
        float x = r.getHeight();
        float y = r.getWidth();
        canvas.drawRect(0,0,x,y, paint);
		return null;
	}

	@Override
	public Void onOutline(Outline o) {
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        canvas.drawRect(0,0,h,w,paint);
		return null;
	}

	@Override
	public Void onPolygon(final Polygon s) {

        int n = s.getPoints().size();
        final float[] pts = new float[4*(n+1)];
        int i = 0;

        for(Point p: s.getPoints()) {
            while(i < pts.length) {
                float x = p.getX();
                float y = p.getY();
                pts[i] = x;
                pts[i+1] = y;
                i++;
            }
        }

		canvas.drawLines(pts, paint);
		return null;
	}
}
