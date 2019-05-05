package me.whaless.app.common.widget.plus.df;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.whaless.app.common.R;
import me.whaless.app.common.widget.plus.IPlusRecyclerFooter;
import me.whaless.app.common.widget.plus.PlusRecyclerDecorationView;

/**
 * **********************
 * Author: Joy
 * Date:   2017-12-27
 * Time:   11:51
 * **********************
 */

public class PlusDefaultRecyclerFooterView extends PlusRecyclerDecorationView implements IPlusRecyclerFooter {

	private static final int LOADING_MORE_HINT_LOADING = R.string.plus_refresh_footer_loading;
	private static final int LOADING_MORE_HINT_ERROR = R.string.plus_refresh_footer_error;
	private static final int LOADING_MORE_HINT_NOMORE = R.string.plus_refresh_footer_nomore;

	View mFooterLayout;
	TextView mFooterText;
	View mFooterProgress;
	View mFooterNoMore;

	public PlusDefaultRecyclerFooterView(Context context) {
		this(context, null);
	}

	public PlusDefaultRecyclerFooterView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PlusDefaultRecyclerFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		inflate(context, R.layout.plus_recycler_footerview, this);

		mFooterLayout = findViewById(R.id.plus_footer_layout);
		mFooterProgress = findViewById(R.id.plus_footer_progress);
		mFooterText = (TextView) findViewById(R.id.plus_footer_hint);
		mFooterNoMore = findViewById(R.id.plus_footer_image);
	}

	public void show() {
		mFooterLayout.setVisibility(VISIBLE);
	}

	public void hide() {
		mFooterLayout.setVisibility(GONE);
	}

	@Override
	public void onLoading() {
		show();
		setLoadEnabled(true);
		mFooterNoMore.setVisibility(GONE);
		mFooterProgress.setVisibility(VISIBLE);
		mFooterText.setVisibility(VISIBLE);
		mFooterText.setText(LOADING_MORE_HINT_LOADING);
		loadMoreComplete();
	}

	@Override
	public void onLoadNone() {
		setLoadEnabled(false);
		mFooterLayout.setVisibility(GONE);
	}

	@Override
	public void onLoadEmpty() {
		setLoadEnabled(false);
		hide();
		loadMoreComplete();
	}

	@Override
	public void onLoadError() {
		show();
		setLoadEnabled(true);
		mFooterNoMore.setVisibility(GONE);
		mFooterProgress.setVisibility(GONE);
		mFooterText.setVisibility(VISIBLE);
		mFooterText.setText(LOADING_MORE_HINT_ERROR);
		loadMoreComplete();
	}

	@Override
	public void onLoadFinish() {
		show();
		setLoadEnabled(false);
		mFooterNoMore.setVisibility(VISIBLE);
		mFooterProgress.setVisibility(GONE);
		mFooterText.setVisibility(GONE);
		mFooterText.setText(LOADING_MORE_HINT_NOMORE);
		loadMoreComplete();
	}
}
