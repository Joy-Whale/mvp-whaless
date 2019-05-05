package me.whaless.app.common.widget.plus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import me.whaless.app.common.widget.recyclerview.adapter.HeaderAdapter;

/**
 * User: Joy
 * Date: 2016/10/8
 * Time: 17:57
 */

public class PlusRecyclerDecorationView extends FrameLayout {

	//    是否依附在RecyclerView上
	private boolean isAttached;
	//    是头部还是底部
	private boolean isHeader = true;

	//   RecyclerView是否颠倒
	private boolean isReversed;
	//    RecyclerView是否水平
	private boolean isVertical;
	//  用以缓存是否调用加载
	private boolean isCanLoad;
	//  设置是否可加载
	private boolean isLoadEnable = true;
	//  是否加载完成
	private boolean isLoadComplete = true;

	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	//  给RecyclerView的头部底部加Decoration
	private PlusRecyclerItemDecoration decoration;
	//  loadmore的监听事件
	private OnPlusLoadMoreListener loadMoreListener;

	// 滑动监听
	private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			onScrollChanged();
		}
	};

	//  依附监听
	private RecyclerView.OnChildAttachStateChangeListener onAttachListener = new RecyclerView.OnChildAttachStateChangeListener() {
		@Override
		public void onChildViewAttachedToWindow(View view) {
		}

		@Override
		public void onChildViewDetachedFromWindow(View view) {
			post(() -> {
				if (!recyclerView.isComputingLayout()) {
					recyclerView.invalidateItemDecorations();
				}
				onScrollChanged();
			});
		}
	};

	public PlusRecyclerDecorationView(Context context) {
		super(context);
	}

	public PlusRecyclerDecorationView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PlusRecyclerDecorationView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	/**
	 * 是否可以加载，没有更多时可以调用
	 */
	public void setLoadEnabled(boolean loadEnable) {
		isLoadEnable = loadEnable;
	}

	/**
	 * 设置加载监听事件
	 */
	public void setLoadMoreListener(OnPlusLoadMoreListener loadMoreListener) {
		this.loadMoreListener = loadMoreListener;
	}

	/**
	 * 加载完成时调用，避免重复加载
	 */
	public void loadMoreComplete() {
		this.isLoadComplete = true;
	}


	/**
	 * 依附的方法
	 */
	public void attachTo(@NonNull final RecyclerView recycler, boolean isHeader) {
		if (recycler.getLayoutManager() == null) {
			throw new IllegalStateException("no LayoutManager.");
		}
		this.isHeader = isHeader;
		recyclerView = recycler;
		layoutManager = recyclerView.getLayoutManager();
		initLayoutManager();
		isAttached = true;
		if (decoration != null) {
			recyclerView.removeItemDecoration(decoration);
		}
		decoration = new PlusRecyclerItemDecoration(layoutManager).setIsHeader(isHeader);
		recyclerView.addItemDecoration(decoration);
		// 如果当前view的宽高不为0的话，则刷新对应的Decoration的宽和高并刷新父RecyclerView的ItemDecorations;
		if (getWidth() > 0 && getHeight() > 0) {
			int[] wh = measureWidthAndHeight();
			decoration.setWidth(wh[0]).setHeight(wh[1]);
			recyclerView.invalidateItemDecorations();
		}
		recyclerView.removeOnScrollListener(onScrollListener);
		recyclerView.addOnScrollListener(onScrollListener);
		recyclerView.removeOnChildAttachStateChangeListener(onAttachListener);
		recyclerView.addOnChildAttachStateChangeListener(onAttachListener);
	}

	/**
	 * 通过layoutManager获取各种属性值
	 */
	private void initLayoutManager() {
		if (layoutManager instanceof GridLayoutManager) {
			GridLayoutManager grid = (GridLayoutManager) layoutManager;
			this.isReversed = grid.getReverseLayout();
			this.isVertical = grid.getOrientation() == RecyclerView.VERTICAL;
		} else if (layoutManager instanceof LinearLayoutManager) {
			LinearLayoutManager linear = (LinearLayoutManager) layoutManager;
			this.isReversed = linear.getReverseLayout();
			this.isVertical = linear.getOrientation() == RecyclerView.VERTICAL;
		} else if (layoutManager instanceof StaggeredGridLayoutManager) {
			StaggeredGridLayoutManager staggeredGrid = (StaggeredGridLayoutManager) layoutManager;
			this.isReversed = staggeredGrid.getReverseLayout();
			this.isVertical = staggeredGrid.getOrientation() == LinearLayoutManager.VERTICAL;
		}
	}


	/**
	 * 重写该方法，更新头部底部宽高
	 * @param changed
	 * @param l
	 * @param t
	 * @param r
	 * @param b
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed && isAttached) {
			if (decoration != null) {
				int[] wh = measureWidthAndHeight();
				decoration.setWidth(wh[0]).setHeight(wh[1]);
				recyclerView.invalidateItemDecorations();
			}
			onScrollChanged();
		}
		super.onLayout(changed, l, t, r, b);
	}

	private int[] measureWidthAndHeight() {
		int vertical = 0;
		int horizontal = 0;
		if (getLayoutParams() instanceof MarginLayoutParams) {
			final MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
			vertical = layoutParams.topMargin + layoutParams.bottomMargin;
			horizontal = layoutParams.leftMargin + layoutParams.rightMargin;
		}
		decoration.setHeight(getHeight() + vertical).setWidth(getWidth() + horizontal);
		return new int[]{getWidth() + horizontal, getHeight() + vertical};
	}


	/**
	 * 滚动时移动头部底部
	 */
	private void onScrollChanged() {
		if (isHeader) {
			boolean isFirst = hasItems() && isFirstRowVisible();
			translationXY(isFirst);
		} else {
			boolean isLast = hasItems() && isLastRowVisible();
			translationXY(isLast);
		}
	}


	/**
	 * 移动的方法
	 * @param isFirst
	 */
	private void translationXY(boolean isFirst) {
		setVisibility(isFirst ? VISIBLE : INVISIBLE);
		if (isFirst) {
			if (isLoadEnable && isLoadComplete && isCanLoad && loadMoreListener != null) {
				loadMoreListener.onLoadMore();
				isCanLoad = false;
				isLoadComplete = false;
			}
			int first = calculateTranslation();
			if (isVertical) {
				setTranslationY(first);
			} else {
				setTranslationX(first);
			}
		} else {
			isCanLoad = true;
		}
	}

	/**
	 * 判断头部底部进行计算距离
	 * @return
	 */
	private int calculateTranslation() {
		if (isHeader) {
			return calculateTranslationXY(!isReversed);
		} else {
			return calculateTranslationXY(isReversed);
		}
	}

	/**
	 * 计算距离的方法
	 * @param isTop
	 * @return
	 */
	private int calculateTranslationXY(boolean isTop) {
		if (!isTop) {
			int offset = getScrollOffset();
			int base = getScrollRange() - getSize();
			return base - offset;
		} else {
			return -getScrollOffset();
		}
	}


	private boolean hasItems() {
		if(recyclerView.getAdapter() == null)
			return false;
		RecyclerView.Adapter adapter = recyclerView.getAdapter();
		if(adapter instanceof HeaderAdapter){
			return ((HeaderAdapter) adapter).getRealItemCount() > 0;
		}
		return adapter.getItemCount() > 0;
	}


	private int getScrollOffset() {
		return isVertical ? recyclerView.computeVerticalScrollOffset() : recyclerView.computeHorizontalScrollOffset();
	}

	private int getSize() {
		return isVertical ? getHeight() : getWidth();
	}

	private int getScrollRange() {
		return isVertical ? recyclerView.computeVerticalScrollRange() : recyclerView.computeHorizontalScrollRange();
	}


	/**
	 * 第一项是否显示
	 * @return
	 */
	private boolean isFirstRowVisible() {
		if (layoutManager instanceof GridLayoutManager) {
			GridLayoutManager grid = (GridLayoutManager) layoutManager;
			return grid.findFirstVisibleItemPosition() == 0;
		} else if (layoutManager instanceof LinearLayoutManager) {
			LinearLayoutManager linear = (LinearLayoutManager) layoutManager;
			return linear.findFirstVisibleItemPosition() == 0;
		} else if (layoutManager instanceof StaggeredGridLayoutManager) {
			StaggeredGridLayoutManager staggeredGrid = (StaggeredGridLayoutManager) layoutManager;
			int[] positions = staggeredGrid.findFirstVisibleItemPositions(null);
			Arrays.sort(positions);
			return positions[0] == 0;
		}
		return false;
	}


	/**
	 * 最后一项是否显示
	 */
	private boolean isLastRowVisible() {
		if (layoutManager instanceof GridLayoutManager) {
			GridLayoutManager grid = (GridLayoutManager) layoutManager;
			return grid.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1;
		} else if (layoutManager instanceof LinearLayoutManager) {
			LinearLayoutManager linear = (LinearLayoutManager) layoutManager;
			return linear.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1;
		} else if (layoutManager instanceof StaggeredGridLayoutManager) {
			StaggeredGridLayoutManager staggeredGrid = (StaggeredGridLayoutManager) layoutManager;
			int[] positions = staggeredGrid.findLastVisibleItemPositions(null);
			Arrays.sort(positions);
			return positions[staggeredGrid.getSpanCount() - 1] >= layoutManager.getItemCount() - 1;
		}
		return false;
	}
}
