package study.ian.morphviewlib;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import java.util.Arrays;
import java.util.List;

public class MorphView extends android.support.v7.widget.AppCompatImageView {

    private static final String TAG = "MorphView";

    private final SvgData svgData;
    private final Paint paint = new Paint();
    private DataPath path;
    private ValueAnimator pointAnimator;
    private boolean isRunningInfiniteAnim = false;
    private int W_SIZE = 150;
    private int H_SIZE = 150;
    private int currentId;
    private long animationDuration = 500;

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

    public void performInfiniteAnimation(final Integer... ids) {
        List<Integer> idList = Arrays.asList(ids);

        svgData.setMorphRes(idList.get(0), idList.get(1), this);
        currentId = idList.get(1);

        ValueAnimator v = ValueAnimator.ofFloat(0, 1);
        v.setDuration(animationDuration);
        v.setInterpolator(new OvershootInterpolator());
        v.setRepeatCount(ValueAnimator.INFINITE);
        v.addUpdateListener(animation -> {
            path.reset();
            path = svgData.getMorphPath((float) v.getAnimatedValue());
            postInvalidate();
        });
        v.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if (!isRunningInfiniteAnim) {
                    v.end();
                } else {
                    int index = idList.indexOf(currentId);
                    int toId = (index == idList.size() - 1) ? idList.get(0) : idList.get(index + 1);
                    svgData.setMorphRes(currentId, toId, MorphView.this);
                    currentId = toId;
                }
            }
        });

        v.start();
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

        canvas.save();
        canvas.translate((getWidth() - W_SIZE) * .5f, (getHeight() - H_SIZE) * .5f);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
