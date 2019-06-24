package com.nhasan.circlemenu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * @author Nazmul Hasan Masum
 */
public class PieActivity extends AppCompatActivity {
    IlluminationPieView myView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pie);

        myView = new IlluminationPieView(this);
        LinearLayout linearLayout = findViewById(R.id.linearOne);
        linearLayout.addView(myView);

        SeekBar seekBarX = findViewById(R.id.seekBar1);
        seekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myView.illuminate(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public class IlluminationPieView extends View {
        Paint paint = null;
        RectF rect;
        RectF rect2;
        RectF rect3;
        Canvas canvas;
        int color = Color.GREEN;
        int color2 = Color.RED;
        int color3 = Color.GREEN;

        int radiusDynamic = 200;

        int rOne = 0;
        int rTwo = 0;
        int rThree = 0;

        public IlluminationPieView(Context context) {
            super(context);
            paint = new Paint();
            x = getWidth();
            y = getHeight();
            radiusDynamic = (x / 2 - x / 8);
        }

        int x;
        int y;

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            this.canvas = canvas;

            x = getWidth();
            y = getHeight();

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);

            // Use Color.parseColor to define HTML colors
            RectF rectMain = new RectF((x / 2) - (x / 2 - x / 8), (y / 2) - (x / 2 - x / 8), (x / 2) + (x / 2 - x / 8), (y / 2) + (x / 2 - x / 8));
            //paint.setColor(Color.parseColor("#CD5C5C"));
            paint.setColor(Color.DKGRAY);
            //canvas.drawCircle(x / 2, y / 2, radius, paint);
            canvas.drawArc(rectMain, 180, 180, true, paint);
            canvas.drawLine((x / 2) - (x / 2 - x / 8), y / 2, (x / 2) + radiusDynamic, (y / 2), paint);


            //Light
            paint.setStyle(Paint.Style.FILL);

            rect = new RectF((x / 2) - radiusDynamic, (y / 2) - radiusDynamic, (x / 2) + radiusDynamic, (y / 2) + radiusDynamic);
            paint.setColor(color);
            canvas.drawArc(rect, 180, 60, true, paint);

            rect2 = new RectF((x / 2) - radiusDynamic, (y / 2) - radiusDynamic, (x / 2) + radiusDynamic, (y / 2) + radiusDynamic);
            paint.setColor(color2);
            canvas.drawArc(rect2, 240, 60, true, paint);

            rect3 = new RectF((x / 2) - radiusDynamic, (y / 2) - radiusDynamic, (x / 2) + radiusDynamic, (y / 2) + radiusDynamic);
            paint.setColor(color3);
            canvas.drawArc(rect3, 300, 60, true, paint);


            //Blank
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);
            //paint.setAlpha(50);
            rect = new RectF((x / 2) - (x / 2 - x / 8), (y / 2) - (x / 2 - x / 8), (x / 2) + (x / 2 - x / 8), (y / 2) + (x / 2 - x / 8));
            canvas.drawArc(rect, 180, 60, true, paint);

            rect2 = new RectF((x / 2) - (x / 2 - x / 8), (y / 2) - (x / 2 - x / 8), (x / 2) + (x / 2 - x / 8), (y / 2) + (x / 2 - x / 8));
            canvas.drawArc(rect2, 240, 60, true, paint);

            rect3 = new RectF((x / 2) - (x / 2 - x / 8), (y / 2) - (x / 2 - x / 8), (x / 2) + (x / 2 - x / 8), (y / 2) + (x / 2 - x / 8));
            canvas.drawArc(rect3, 300, 60, true, paint);

            paint.setColor(Color.BLACK);
            Path path = new Path();
            path.moveTo(50, 50);
            path.lineTo(50, 500);
            path.lineTo(200, 500);
            path.lineTo(200, 300);
            path.lineTo(350, 300);
            //canvas.drawPath(path, paint);

            float b = (x / 2 - x / 8);



        }

        void illuminate(int progress) {
            radiusDynamic = ((x / 2 - x / 8) * progress) / 100;
            invalidate();
        }


        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    System.out.println(x + ' ' + y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    System.out.println(x + ' ' + y);
                    break;
                case MotionEvent.ACTION_UP:
                    if (isInSweep(event, rect, 180, 60)) {


                        if (isInCircle(getWidth() / 2, getHeight() / 2, event, (getWidth() / 2 - getWidth() / 8))) {
                            Log.d("Tap", "onTouchEvent: Rect");

                            paint.setStyle(Paint.Style.FILL_AND_STROKE);

                            if (rOne == 0) {
                                color = Color.BLUE;
                                paint.setColor(color);
                                rOne = 1;
                            } else {
                                color = Color.GREEN;
                                paint.setColor(color);
                                rOne = 0;
                            }

                            invalidate();
                        }


                    } else if (isInSweep(event, myView.rect2, 240, 60)) {
                        Log.d("Tap", "onTouchEvent: Rect2");
                        paint.setStyle(Paint.Style.FILL_AND_STROKE);

                        if (rTwo == 0) {
                            color2 = Color.BLUE;
                            paint.setColor(color);
                            rTwo = 1;
                        } else {
                            color2 = Color.RED;
                            paint.setColor(color);
                            rTwo = 0;
                        }

                        invalidate();

                    } else if (isInSweep(event, myView.rect3, 300, 60)) {
                        Log.d("Tap", "onTouchEvent: Rect3");
                        paint.setStyle(Paint.Style.FILL_AND_STROKE);

                        if (rThree == 0) {
                            color3 = Color.BLUE;
                            paint.setColor(color);
                            rThree = 1;
                        } else {
                            color3 = Color.GREEN;
                            paint.setColor(color);
                            rThree = 0;
                        }

                        invalidate();
                    }

                case MotionEvent.ACTION_CANCEL:
                    System.out.println(x + ' ' + y);
                    break;
            }
            return true;
        }

        private boolean isInSweep(MotionEvent event, RectF bounds, float startAngle, float sweepAngle) {
            final float at = (float) Math.toDegrees(Math.atan2(event.getY() - bounds.centerY(),
                    event.getX() - bounds.centerX()));

            // Convert from atan2 to standard angle.
            final float angle = (at + 360) % 360;

            // Check if in sweep.
            return angle >= startAngle && angle <= startAngle + sweepAngle;
        }

        private boolean isInCircle(float cx, float cy, MotionEvent tapPoint, float radius) {
            float hype = (float) Math.sqrt(Math.pow((tapPoint.getX() - cx), 2) + Math.pow((tapPoint.getY() - cy), 2));
            Log.d("Circle", "isInCircle: " + hype);
            Log.d("Circle", "Radius: " + radius);

            if (radius >= hype) {
                return true;
            }

            return false;
        }

    }

}
