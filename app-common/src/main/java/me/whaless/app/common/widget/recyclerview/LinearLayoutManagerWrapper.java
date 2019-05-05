package me.whaless.app.common.widget.recyclerview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * **********************
 * Author: 54563
 * Date:   2017-12-19
 * Time:   13:23
 * **********************
 */

public class LinearLayoutManagerWrapper extends LinearLayoutManager {

	public LinearLayoutManagerWrapper(Context context) {
		super(context);
	}

	public LinearLayoutManagerWrapper(Context context, int orientation, boolean reverseLayout) {
		super(context, orientation, reverseLayout);
	}

	public LinearLayoutManagerWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
		try {
			super.onLayoutChildren(recycler, state);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean supportsPredictiveItemAnimations() {
		return false;
	}
}
