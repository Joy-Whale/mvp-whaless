package me.whaless.app.common.widget.recyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * **********************
 * Author: yu
 * Date:   2015/6/23
 * Time:   10:17
 * **********************
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

	public enum Orientation {
		Horizontal, Vertical
	}

	private int space;
	private int color = -1;
	private int start;
	private int end;
	private ColorDrawable mColorDrawable;
	private Orientation orientation = Orientation.Vertical;

	public LinearItemDecoration(int space) {
		this(space, Orientation.Vertical);
	}

	public LinearItemDecoration(int space, Orientation orientation) {
		this(space, orientation, -1);
	}

	/**
	 * @param space 间距
	 */
	public LinearItemDecoration(int space, Orientation orientation, int color) {
		this(space, orientation, color, 0, 0);
	}

	public LinearItemDecoration(int space, int start, int end) {
		this(space, Orientation.Vertical,  start, end);
	}

	/**
	 * @param space 间距
	 */
	public LinearItemDecoration(int space, Orientation orientation, int start, int end) {
		this(space, orientation, -1, start, end);
	}

	/**
	 * @param space 间距
	 */
	public LinearItemDecoration(int space, Orientation orientation, int color, int start, int end) {
		this.space = space;
		this.orientation = orientation;
		this.start = start;
		this.end = end;
		if (color != -1) {
			this.mColorDrawable = new ColorDrawable(color);
		}
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);
		if (color > 0)
			c.drawColor(color);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);
		if (mColorDrawable == null) {
			return;
		}
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			//以下计算主要用来确定绘制的位置
			final int top = child.getBottom() + params.bottomMargin;
			final int bottom = top + space;
			mColorDrawable.setBounds(left, top, right, bottom);
			mColorDrawable.draw(c);
		}
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

		//设置左右的间隔如果想设置的话自行设置，我这用不到就注释掉了
/*          outRect.left = space;
			outRect.right = space;*/

		//       System.out.println("position"+parent.getChildPosition(view));
		//       System.out.println("count"+parent.getChildCount());

		//         if(parent.getChildPosition(view) != parent.getChildCount() - 1)
		//         outRect.bottom = space;

		int position = parent.getChildAdapterPosition(view);
		if (start > 0 && position == 0) {
			setOutRect(outRect, start);
		}
		//改成使用上面的间隔来设置
		if (position != 0) {
			setOutRect(outRect, space);
		}

		if (end > 0 && position == parent.getAdapter().getItemCount() - 1) {
			switch (orientation) {
				case Vertical:
					outRect.bottom = end;
					break;
				case Horizontal:
					outRect.right = end;
					break;
			}
		}
	}

	private void setOutRect(Rect outRect, int space) {
		switch (orientation) {
			case Vertical:
				outRect.top = space;
				break;
			case Horizontal:
				outRect.left = space;
				break;
		}
	}
}
