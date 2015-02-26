package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

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
        canvas.drawPaint(paint);
		return null;
	}

	@Override
	public Void onFill(final Fill f) {
        canvas.drawPaint(paint);
		return null;
	}

	@Override
	public Void onGroup(final Group g) {
        canvas.drawPaint(paint);
		return null;
	}

	@Override
	public Void onLocation(final Location l) {
        float h = canvas.getHeight();
        float w = canvas.getWidth();
        canvas.drawPoint(h,w,paint);
		return null;
	}

	@Override
	public Void onRectangle(final Rectangle r) {
        float h = canvas.getHeight();
        float w =canvas.getWidth();
        canvas.drawLine(0,0,h,w, paint);
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
            }
        }

		canvas.drawLines(pts, paint);
		return null;
	}
}
