package cn.lxw.us.views.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import cn.lxw.us.utils.DensityUtils;

/**
 * Created by Lianxw on 2015/5/26.
 * 圆形ImageView
 */
public class CircleImageView extends ImageView {

    private Path clipPath;
    private Path borderPath;
    private Paint borderPaint;
    private int borderWidth;

    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        borderWidth = DensityUtils.dip2px(1.5f);
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Rect rect = new Rect();
        rect.left = getPaddingLeft();
        rect.top = getPaddingTop();
        rect.right = w - getPaddingRight();
        rect.bottom = h - getPaddingBottom();
        int _radius = Math.min(rect.width(), rect.height());
        clipPath = new Path();
        clipPath.addCircle(rect.centerX(), rect.centerY(), (_radius / 2) - borderWidth, Path.Direction.CW);
        int _patch = borderWidth = DensityUtils.dip2px(0.5f);
        borderPath = new Path();
        borderPath.addCircle(rect.centerX(), rect.centerY(), (_radius / 2) -_patch, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (borderPath!=null) {
            canvas.save();
            canvas.drawPath(borderPath, borderPaint);
            canvas.clipPath(clipPath);
        }
        super.onDraw(canvas);
        if (borderPath!=null) {
            canvas.restore();
        }
    }
}
