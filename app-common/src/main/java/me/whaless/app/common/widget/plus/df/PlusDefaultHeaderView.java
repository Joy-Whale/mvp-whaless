package me.whaless.app.common.widget.plus.df;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.whaless.app.common.R;
import me.whaless.app.common.widget.plus.PlusRefresh;


/**
 * User: JiYu
 * Date: 2016-09-21
 * Time: 10-44
 */

class PlusDefaultHeaderView extends FrameLayout implements PlusRefresh {

	private ImageView ivArrow;

	private TextView tvRefresh;

	private ProgressBar progressBar;


	private Animation rotateUp;

	private Animation rotateDown;

	private boolean rotated = false;

	private CharSequence completeStr = "COMPLETE";
	private CharSequence refreshingStr = "REFRESHING";
	private CharSequence pullStr = "PULL TO REFRESH";
	private CharSequence releaseStr = "RELEASE TO REFRESH";

	public PlusDefaultHeaderView(Context context) {
		this(context, null);
	}

	public PlusDefaultHeaderView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PlusDefaultHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		rotateUp = AnimationUtils.loadAnimation(context, R.anim.plus_rotate_up);
		rotateDown = AnimationUtils.loadAnimation(context, R.anim.plus_rotate_down);

		View v = LayoutInflater.from(getContext()).inflate(R.layout.plus_layout_classic_refresh, null);
		addView(v);

		tvRefresh = (TextView) findViewById(R.id.tvRefresh);
		ivArrow = (ImageView) findViewById(R.id.ivArrow);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);

		refreshingStr = getResources().getString(R.string.cube_ptr_refreshing);
		releaseStr = getResources().getString(R.string.cube_ptr_release_to_refresh);
		completeStr = getResources().getString(R.string.cube_ptr_refresh_complete);
		pullStr = getResources().getString(R.string.cube_ptr_pull_down);
	}


	public CharSequence getReleaseStr() {
		return releaseStr;
	}

	public void setReleaseStr(CharSequence releaseStr) {
		this.releaseStr = releaseStr;
	}

	public CharSequence getPullStr() {
		return pullStr;
	}

	public void setPullStr(CharSequence pullStr) {
		this.pullStr = pullStr;
	}

	public CharSequence getRefreshingStr() {
		return refreshingStr;
	}

	public void setRefreshingStr(CharSequence refreshingStr) {
		this.refreshingStr = refreshingStr;
	}

	public CharSequence getCompleteStr() {
		return completeStr;
	}

	public void setCompleteStr(CharSequence completeStr) {
		this.completeStr = completeStr;
	}

	@Override
	public void onReset() {
		rotated = false;

		ivArrow.clearAnimation();
		ivArrow.setVisibility(GONE);
		progressBar.setVisibility(GONE);
	}

	@Override
	public void onPrepare() {

	}

	@Override
	public void onComplete() {
		rotated = false;

		ivArrow.clearAnimation();
		ivArrow.setVisibility(GONE);
		progressBar.setVisibility(GONE);
		tvRefresh.setText(completeStr);
	}

	@Override
	public void onRelease() {
		rotated = false;
		ivArrow.clearAnimation();
		ivArrow.setVisibility(GONE);
		progressBar.setVisibility(VISIBLE);
		tvRefresh.setText(refreshingStr);
	}

	@Override
	public void onPositionChange(float currentPercent) {
		ivArrow.setVisibility(VISIBLE);
		progressBar.setVisibility(GONE);
		if (currentPercent < 1) {
			if (rotated) {
				ivArrow.clearAnimation();
				ivArrow.startAnimation(rotateDown);
				rotated = false;
			}
			tvRefresh.setText(pullStr);
		} else {
			tvRefresh.setText(releaseStr);
			if (!rotated) {
				ivArrow.clearAnimation();
				ivArrow.startAnimation(rotateUp);
				rotated = true;
			}
		}
	}

	@Override
	public void setIsHeaderOrFooter(boolean isHead) {
		if (!isHead) {
			ivArrow.setRotation(180);
		}
	}
}
