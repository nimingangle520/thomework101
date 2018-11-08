package com.shushan.thomework101.homepage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by LIMING on 2017/1/13.
 */
public class MyGridView  extends GridView{
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /*
	 * 设置不滚动
	 */

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
