package com.example.hsy.test4surfaceviewcircleanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private SurfaceView surface;
    private SurfaceHolder holder;
    boolean stop = false;
    private int radiusArray[] = {0, 0};
    private static int baseRadius = 50;
    private static int maxRadius = 200;
    private static int baseSpeed = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surface = (SurfaceView) findViewById(R.id.circle_surface);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    holder = surface.getHolder();
                    if (!holder.getSurface().isValid()) {
                        continue;
                    }

                    Canvas canvas = null;
                    try {
                        canvas = holder.lockCanvas();
                        synchronized (holder) {
                            if (canvas != null) {
                                draw(canvas);
                            }
                        }
                    } catch (Exception e) {
                    } finally {
                        if (canvas != null) {
                            holder.unlockCanvasAndPost(canvas);
                        }
                    }

                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        stop = true;
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void draw(Canvas canvas) {
        updateRadius();
        drawCircles(canvas);
    }

    private void updateRadius() {
        for (int i = 0; i < radiusArray.length; i++) {
            if (radiusArray[i] >= maxRadius - 1) {
                radiusArray[i] = baseRadius;
            } else {
                radiusArray[i] += baseSpeed;
            }
        }

        Arrays.sort(radiusArray);
    }

    private void drawCircles(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xffffffff);

        RectF r = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawRect(r, paint);

        for (int i = radiusArray.length - 1; i >= 0; i--) {
            if (radiusArray[i] >= baseRadius) {
                paint.setColor(getColor(radiusArray[i]));

                canvas.drawCircle(canvas.getWidth() / 2,
                        canvas.getHeight() / 2, radiusArray[i], paint);
            }
        }

        paint.setColor(Color.argb(255, 0xfe, 0xaa, 0x4b));
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2,
                baseRadius, paint);
    }

    private int getColor(int radius) {
        int total = maxRadius - baseRadius;
        float ratio = (radius - baseRadius) * 1.0f / total;
        if (ratio < 0) {
            ratio = 0;
        }
        int alpha = (int) ((1.0f - ratio) * 255);
        return Color.argb(alpha, 0xfd, 0xdb, 0xb6);
    }
}
