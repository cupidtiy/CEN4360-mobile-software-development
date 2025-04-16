package com.example.connectfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class WinningLineView extends View {

    private Paint paint;
    private float startX, startY, endX, endY;
    private boolean drawLine = false;

    public WinningLineView(Context context) {
        super(context);
        init();
    }

    public WinningLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(0xFF000000); // Black color
        paint.setStrokeWidth(10);   // Line thickness
        paint.setStyle(Paint.Style.STROKE);
    }

    public void setLine(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.drawLine = true;
        invalidate(); // Redraw the view
    }

    public void clearLine() {
        this.drawLine = false;
        invalidate(); // Clear the view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (drawLine) {
            canvas.drawLine(startX, startY, endX, endY, paint);
        }
    }
}
