package com.hxy.view_zidingyi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xianyu.hxy on 2015/8/10.
 */
public class MyView extends View {
    Paint mPaint;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.MyView);
        int textColor=array.getColor(R.styleable.MyView_textColor, 0xff00ff00);
        float textSize=array.getDimension(R.styleable.MyView_textSize,36);
        mPaint.setColor(textColor);
        mPaint.setTextSize(textColor);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(10, 10, 800, 100, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawText("hello haha",15,120,mPaint);
    }


}
