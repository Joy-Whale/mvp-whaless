package me.whaless.app.presentation.view;

import androidx.annotation.StringRes;


/**
 * User: JiYu
 * DateModel: 2016-08-04
 * Time: 18-38
 * 加载数据类型的view
 */

public interface ILoadingView extends IView {

	// 页面加载类型loading
	int LOADING_TYPE_PAGE = 0;
	// 数据提交类型loading
	int LOADING_TYPE_POST = 1;

	void showLoading(int type);

	/**
	 * 显示加载框
	 */
	void showLoading();

	/**
	 * 隐藏加载框
	 */
	void hideLoading();

	/**
	 * 显示当前的消息
	 * @param message 消息内容
	 */
	void showMessage(String message);

	/**
	 * 显示当前的消息
	 * @param resId 消息内容ResourceId{@link StringRes}
	 */
	void showMessage(@StringRes int resId);

}
