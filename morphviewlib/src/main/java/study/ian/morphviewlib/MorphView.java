package study.ian.morphviewlib;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

public class MorphView extends View {

    private static final String TAG = "MorphView";

    private final SvgData svgData;
    private final Paint paint = new Paint();
    private DataPath path;
    private ValueAnimator pointAnimator;
    private boolean isRunningInfiniteAnim = false;
    private int W_SIZE = 150;
    private int H_SIZE = 150;
    private int currentId;
    private long animationDuration = 250;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(W_SIZE, H_SIZE);
    }

    @SuppressWarnings("ClickableViewAccessibility")
    public MorphView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);

        svgData = new SvgData(context);

        initPaint();
        initAnimator();
    }

    private void initPaint() {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(W_SIZE / 10);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initAnimator() {
        pointAnimator = ValueAnimator.ofFloat(0, 1);
        pointAnimator.setDuration(animationDuration);
        pointAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        pointAnimator.addUpdateListener(valueAnimator -> {
            path.reset();
            path = svgData.getMorphPath((float) valueAnimator.getAnimatedValue());
            postInvalidate();
        });
    }

    private void initPath(int id) {
        path = svgData.getPath(id, this);
        currentId = id;
    }

    public void changeInterpolator(Interpolator interpolator) {
        pointAnimator.setInterpolator(interpolator);
    }

    public void performAnimation(int toId) {
        svgData.setMorphRes(currentId, toId, this);
        currentId = toId;
        pointAnimator.start();
    }

    public void performInfiniteAnimation(int fromId, int toId) {
        svgData.setMorphRes(fromId, toId, this);
        pointAnimator.setRepeatCount(ValueAnimator.INFINITE);
        pointAnimator.setRepeatMode(ValueAnimator.REVERSE);
        pointAnimator.start();
        isRunningInfiniteAnim = true;
    }

    public void stopInfiniteAnimation() {
        pointAnimator.end();
        isRunningInfiniteAnim = false;
    }

    public boolean isRunningInfiniteAnim() {
        return isRunningInfiniteAnim;
    }

    public void setSize(int w, int h) {
        this.W_SIZE = w;
        this.H_SIZE = h;
        initPath(currentId);
    }

    public int getW_SIZE() {
        return W_SIZE;
    }

    public int getH_SIZE() {
        return H_SIZE;
    }

    public void setCurrentId(int id) {
        initPath(id);
        postInvalidate();
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setPaintColor(String color) {
        paint.setColor(Color.parseColor(color));
    }

    public void setPaintColor(int color) {
        paint.setColor(color);
    }

    public void setPaintWidth(int w) {
        paint.setStrokeWidth(w);
    }

    public long getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(long animationDuration) {
        this.animationDuration = animationDuration;
        pointAnimator.setDuration(this.animationDuration);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path, paint);
    }
}
