package me.whaless.app.common.widget.plus.df;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;
import com.whaless.app.common.R;
import me.whaless.app.common.widget.plus.IPlusFrame;
import me.whaless.app.common.widget.recyclerview.LinearLayoutManagerWrapper;


/**
 * User: JiYu
 * Date: 2016-09-20
 * Time: 21-42
 * 自定义RecyclerView
 */

public class PlusDefaultRecyclerView extends PlusRecyclerView implements IPlusFrame {

	RecyclerView mRecycler;

	public PlusDefaultRecyclerView(Context context) {
		this(context, null);
	}

	public PlusDefaultRecyclerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PlusDefaultRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mRecycler = new RecyclerView(context, attrs, defStyleAttr);
		mRecycler.setId(R.id.plus_content_view);
		addView(mRecycler, -1, -1);
	}

	/**
	 * default init
	 */
	@Override
	public void initDefault() {
		super.initDefault();
		setRecycler(mRecycler);
		setLayoutManager(new LinearLayoutManagerWrapper(getContext()));
	}
}
