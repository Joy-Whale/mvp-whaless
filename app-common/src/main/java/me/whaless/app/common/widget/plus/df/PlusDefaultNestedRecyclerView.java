package me.whaless.app.common.widget.plus.df;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * **********************
 * Author: Joy
 * Date:   2018-01-15
 * Time:   11:42
 * **********************
 */

public class PlusDefaultNestedRecyclerView extends PlusDefaultFrameLayout {

	private NestedScrollView mContentView;
	private RecyclerView mRecyclerView;

	public PlusDefaultNestedRecyclerView(Context context) {
		this(context, null);
	}

	public PlusDefaultNestedRecyclerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PlusDefaultNestedRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContentView = new NestedScrollView(context, attrs, defStyleAttr);
		addView(mContentView, -1, -1);


	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

	}
}
