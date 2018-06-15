package com.example.landandpoirtlifecycle.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.landandpoirtlifecycle.R;

public class PathAndMatrixView extends View {
    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作
    private Paint mDeafultPaint;
    private int mViewWidth;
    private int mViewHeight;

    public PathAndMatrixView(Context context) {
        this(context, null);
    }

    public PathAndMatrixView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathAndMatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 6;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();
        mDeafultPaint = new Paint();
        mDeafultPaint.setColor(Color.BLACK);
        mDeafultPaint.setTextSize(15);
        mDeafultPaint.setAntiAlias(true);
        mDeafultPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);      // 平移坐标系

        Path path = new Path();                                 // 创建 Path

        //     path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形
        RectF rect = new RectF(-200, -200, 200, 200);
        // path.addRect(rect, Path.Direction.CW);
        path.addRoundRect(rect, 100, 100, Path.Direction.CW);
        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure

        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }

        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势

        mMatrix.reset();                                                        // 重置Matrix
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180 / Math.PI); // 计算图片旋转角度
        Log.e("wyw", "----- " + degrees);

        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, mDeafultPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mDeafultPaint);                     // 绘制箭头
        invalidate();
    }
}
