package me.whaless.app.common.widget.plus;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * **********************
 * Author: Joy
 * Date:   2018-01-15
 * Time:   13:10
 * **********************
 */

public class PlusRecyclerItemDecoration extends RecyclerView.ItemDecoration {

	private int height;
	private int width;
	private int rowSpan = 1;

	private boolean isReversed;
	private boolean isVertical;

	private boolean isHeader = true;
	RecyclerView.LayoutManager layoutManager;

	PlusRecyclerItemDecoration(RecyclerView.LayoutManager manager) {
		this.layoutManager = manager;
		initLayoutManager();
	}

	public PlusRecyclerItemDecoration setHeight(int height) {
		this.height = height;
		return this;
	}

	public PlusRecyclerItemDecoration setWidth(int width) {
		this.width = width;
		return this;
	}

	 PlusRecyclerItemDecoration setIsHeader(boolean isHeader) {
		this.isHeader = isHeader;
		return this;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		boolean relatedPosition = false;
		if (isHeader) {
			relatedPosition = parent.getChildLayoutPosition(view) < rowSpan;
		} else {
			int lastSum = 1;
			int itemCount = layoutManager.getItemCount();
			if (itemCount > 0 && rowSpan > 1) {
				lastSum = itemCount % rowSpan;
				if (lastSum == 0) {
					lastSum = rowSpan;
				}
			}

			int count = itemCount - lastSum;
			int lastPosition = parent.getChildLayoutPosition(view);
			relatedPosition = lastPosition >= count;
		}

		int heightOffset = relatedPosition && isVertical ? height : 0;
		int widthOffset = relatedPosition && !isVertical ? width : 0;

		if (isHeader) {
			if (isReversed) {
				outRect.bottom = heightOffset;
				outRect.right = widthOffset;
			} else {
				outRect.top = heightOffset;
				outRect.left = widthOffset;
			}
		} else {
			if (isReversed) {
				outRect.top = heightOffset;
				outRect.left = widthOffset;
			} else {
				outRect.bottom = heightOffset;
				outRect.right = widthOffset;
			}
		}
	}

	public void initLayoutManager() {
		if (layoutManager instanceof GridLayoutManager) {
			GridLayoutManager grid = (GridLayoutManager) layoutManager;
			this.rowSpan = grid.getSpanCount();
			this.isReversed = grid.getReverseLayout();
			this.isVertical = grid.getOrientation() == RecyclerView.VERTICAL;
		} else if (layoutManager instanceof LinearLayoutManager) {
			LinearLayoutManager linear = (LinearLayoutManager) layoutManager;
			this.rowSpan = 1;
			this.isReversed = linear.getReverseLayout();
			this.isVertical = linear.getOrientation() == RecyclerView.VERTICAL;
		} else if (layoutManager instanceof StaggeredGridLayoutManager) {
			StaggeredGridLayoutManager staggeredGrid = (StaggeredGridLayoutManager) layoutManager;
			this.rowSpan = staggeredGrid.getSpanCount();
			this.isReversed = staggeredGrid.getReverseLayout();
			this.isVertical = staggeredGrid.getOrientation() == LinearLayoutManager.VERTICAL;
		}
	}
}
