package me.whaless.app.common.widget.plus;

/**
 * **********************
 * Author: Joy
 * Date:   2018-02-08
 * Time:   10:15
 * **********************
 */

public interface IPlusRecyclerFooter {

	void onLoading();

	void onLoadNone();

	void onLoadEmpty();

	void onLoadError();

	void onLoadFinish();
}
