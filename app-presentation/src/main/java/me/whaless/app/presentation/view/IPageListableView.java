package me.whaless.app.presentation.view;

/**
 * User: Joy
 * Date: 2017-01-15
 * Time: 14:13
 */

public interface IPageListableView<L> extends ILoadingView {

	void onGet(L list);

	void onMore(L list);
}
