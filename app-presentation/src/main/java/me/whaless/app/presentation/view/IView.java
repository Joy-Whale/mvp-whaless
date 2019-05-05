package me.whaless.app.presentation.view;

/**
 * User: Joy
 * DateModel: 2016-11-21
 * Time: 21:35
 */

public interface IView {

	void onInitiate();

	/**
	 * 加载数据成功时调用
	 */
	void onSuccess();

	/**
	 * 加载数据失败时调用
	 */
	void onError();

//	/**
//	 * 加载数据失败时调用
//	 * @param clazz 调用当前ILoadingView的Presenter的class，目的是在一个页面中有多个Presenter时区分是哪个Presenter
//	 *              调用了onError（）
//	 */
//	void onError(Class clazz);

	/**
	 * 加载数据结束时调用
	 * 在onSuccess()或onError()后调用
	 */
	void onFinish();
}
