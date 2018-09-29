package com.example.customview;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * 创建日期：2017/12/17 on 下午9:41
 * 描述:
 * 作者:yangliang
 */
public class LikeStarEvaluator implements TypeEvaluator<Point> {

    private Point controllPoint;

    public LikeStarEvaluator(Point controllPoint) {
        this.controllPoint = controllPoint;
    }

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int x = (int) ((1 - fraction) * (1 - fraction) * startValue.x + 2 * fraction * (1 - fraction) * controllPoint.x + fraction * fraction * endValue.x);
        int y = (int) ((1 - fraction) * (1 - fraction) * startValue.y + 2 * fraction * (1 - fraction) * controllPoint.y + fraction * fraction * endValue.y);
        return new Point(x, y);//返回获取path上的坐标点
    }
}
