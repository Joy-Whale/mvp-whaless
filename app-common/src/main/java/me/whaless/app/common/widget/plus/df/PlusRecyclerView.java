package me.whaless.app.common.widget.plus.df;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.whaless.app.common.R;
import me.whaless.app.common.utils.FieldUtils;
import me.whaless.app.common.utils.Logs;
import me.whaless.app.common.widget.plus.IPlusRecyclerFooter;
import me.whaless.app.common.widget.plus.LoadingState;
import me.whaless.app.common.widget.plus.LoadingType;
import me.whaless.app.common.widget.plus.OnPlusLoadMoreListener;
import me.whaless.app.common.widget.plus.PlusRecyclerDecorationView;
import me.whaless.app.common.widget.recyclerview.adapter.AdapterPlus;

/**
 * User: JiYu
 * Date: 2016-09-24
 * Time: 15-48
 * RecyclerView版PlusView，重新实现自己的加载更多样式
 */

public class PlusRecyclerView extends PlusDefaultFrameLayout {

	PlusRecyclerDecorationView mFooter;

	private RecyclerView mRecycler;

	public PlusRecyclerView(Context context) {
		super(context);
	}

	public PlusRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PlusRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void initChildView() {
		super.initChildView();
		mFooter = (PlusRecyclerDecorationView) findViewById(R.id.plus_recycler_footer);
		if (mFooter != null && !(mFooter instanceof IPlusRecyclerFooter)) {
			throw new IllegalStateException("mRecyclerFooter  error");
		}
		//  添加默认底部加载更多布局
		if (mFooter == null && mLoadMoreEnabled) {
			mFooter = new PlusDefaultRecyclerFooterView(getContext());
			addView(mFooter, -1, -2);
		}
		if (mFooter != null)
			mFooter.setLoadEnabled(mLoadMoreEnabled);
	}

	@Override
	public void setLoadMoreEnabled(boolean loadMoreEnable) {
		super.setLoadMoreEnabled(loadMoreEnable);
		if (mFooter != null)
			mFooter.setLoadEnabled(loadMoreEnable);
		if (!loadMoreEnable && getFooterWrapper() != null) {
			getFooterWrapper().onLoadNone();
		}
	}

	@Override
	public void setOnLoadMoreListener(OnPlusLoadMoreListener loadMoreListener) {
		super.setOnLoadMoreListener(loadMoreListener);
		if (mFooter != null)
			mFooter.setLoadMoreListener(() -> {
				//			if (mLoadMoreEnabled && getLoadingState() == LoadingState.Loading)
				getLoadMoreListener().onLoadMore();
			});
	}

	@Override
	protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
		try {
			super.onLayout(b, i, i1, i2, i3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setAdapter(AdapterPlus adapter) {
		if (mRecycler != null)
			mRecycler.setAdapter(adapter);
	}

	public AdapterPlus getAdapter() {
		return (AdapterPlus) mRecycler.getAdapter();
	}

	public void setLayoutManager(@NonNull RecyclerView.LayoutManager manager) {
		if (mRecycler != null) {
			mRecycler.setLayoutManager(manager);
			if (mFooter != null)
				mFooter.attachTo(mRecycler, false);
		}
	}

	public void setRecycler(RecyclerView recycler) {
		this.mRecycler = recycler;
		if (recycler == null)
			return;
		mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
			// TODO 这里需要修复下与AppbarLayout共同使用时，当AppbarLayout Fling滚动到offset为0时
			// TODO 由于RecyclerView也参加了fling，导致RecyclerView的SCROLL_STATE_改变为SCROLL_STATE_SETTING,并且继续
			// TODO 触摸调用onInterceptTouchEvent导致RecyclerView拦截onInterceptTouchEvent无法滑动问题
			// TODO 在AppbarLayout Fling滚动为0时，需要PlusView能继续向下滑动刷新
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				// 计算出当前的滚动偏移量，如果当前滚动偏移量为0，则表示已经滚动到顶部，要及时使RecyclerView停止滚动，不让其抢占onInterceptTouchEvent事件
				int offset = recyclerView.computeVerticalScrollOffset();
				if (offset == 0) {
					recyclerView.postDelayed(() -> {
						if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
							recyclerView.stopScroll();
						}
					}, 100);
					//					recyclerView.getParent().requestDisallowInterceptTouchEvent(false);
				}
			}

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				Logs._d("onScrollStateChanged " + newState);
			}
		});
	}

	public RecyclerView getRecycler() {
		return mRecycler;
	}

	private IPlusRecyclerFooter getFooterWrapper() {
		return (IPlusRecyclerFooter) mFooter;
	}

	/**
	 * 通知数据状态发生改变
	 */
	protected void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		if (mFooter != null)
			mFooter.loadMoreComplete();
	}

	@Override
	public void notifyLoading(LoadingType type) {
		super.notifyLoading(type);
		if (type == LoadingType.Get && getAdapter() != null) {
			// todo 这时候不需要清除数据把？会报错哦 #Cannot call this method while RecyclerView is computing a layout or scrolling
			// if (recycleview.getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (leagues_recycleview.isComputingLayout() == false))
			if (!FieldUtils.isEmpty(getAdapter().getList()))
				getAdapter().clear();
		}
	}

	/**
	 * 当加载状态发生改变时
	 */
	@Override
	protected void onLoadingStateChanged(LoadingState state) {
		Logs.d(state);
		super.onLoadingStateChanged(state);
		//  如果是出错
		switch (state) {
			case Error:
				switch (getLoadingType()) {
					case Refresh:
						if (getAdapter() != null && getAdapter().getList() != null) {
							if (getAdapter().getList().size() > 0) {
								hideErrorView();
							} else {
								showErrorView();
							}
						}
						break;
					case More:
						hideErrorView();
						break;
				}
				break;
		}
		changeLoadingMoreState(state);
	}

	/**
	 * 改变加载更多提示View显示效果
	 */
	private void changeLoadingMoreState(LoadingState state) {
		switch (state) {
			case None:
			case Empty:
				if (getFooterWrapper() != null)
					getFooterWrapper().onLoadEmpty();
				break;
			case Loading:
				if (mLoadMoreEnabled) {
					if (getFooterWrapper() != null)
						getFooterWrapper().onLoading();
				} else {
					if (getFooterWrapper() != null)
						getFooterWrapper().onLoadNone();
				}
				break;
			case Error:
				if (getLoadingType() == LoadingType.Get || getLoadingType() == LoadingType.Refresh) {
					if (getFooterWrapper() != null)
						getFooterWrapper().onLoadNone();
				} else if (mLoadMoreEnabled) {
					if (getFooterWrapper() != null)
						getFooterWrapper().onLoadError();
				}
				break;
			case NoMore:
				//  如果当前是正在加载更多中，则显示底部view，否则隐藏底部view
				if (getLoadingType() == LoadingType.More && mLoadMoreEnabled) {
					if (getFooterWrapper() != null) {
						getFooterWrapper().onLoadFinish();
					}
				} else {
					if (getFooterWrapper() != null)
						getFooterWrapper().onLoadNone();
				}
				break;
		}
	}
}
