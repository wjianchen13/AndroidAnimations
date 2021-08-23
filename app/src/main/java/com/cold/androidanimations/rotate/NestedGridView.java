package com.cold.androidanimations.rotate;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决 和ScrollView 冲突的GridView
 * @author qule
 *
 */
public class NestedGridView extends GridView {

	public NestedGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public NestedGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NestedGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
	    // TODO Auto-generated method stub 
	    int expandSpec = MeasureSpec.makeMeasureSpec(
	               Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
	    
	    super.onMeasure(widthMeasureSpec, expandSpec); 
	    
	    
//	    int heightSpec;
//	    
//        if (getLayoutParams().CODEC_HEIGHT == LayoutParams.WRAP_CONTENT) {
//            // The great Android "hackatlon", the love, the magic.
//            // The two leftmost bits in the CODEC_HEIGHT measure spec have
//            // a special meaning, hence we can't use them to describe CODEC_HEIGHT.
//            heightSpec = MeasureSpec.makeMeasureSpec(
//                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        }
//        else {
//            // Any other CODEC_HEIGHT should be respected as is.
//            heightSpec = heightMeasureSpec;
//        }
// 
//        super.onMeasure(widthMeasureSpec, heightSpec);
//    }

	} 
	
	
}
