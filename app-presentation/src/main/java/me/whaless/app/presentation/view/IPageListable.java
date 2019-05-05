package me.whaless.app.presentation.view;


import me.whaless.app.domain.model.Listable;

/**
 * User: JiYu
 * Date: 2016-09-20
 * Time: 14-13
 */

public interface IPageListable {

	/**
	 * 列表加载类型  加载、刷新、更多
	 */
	int LOADING_TYPE_GET = Listable.LoadType.GET;
	int LOADING_TYPE_REFRESH = Listable.LoadType.REFRESH;
	int LOADING_TYPE_MORE = Listable.LoadType.MORE;

	/**
	 * 列表加载状态  none、空、可加载、全部加载、加载出错
	 */
	int LOADING_STATE_NONE = 1;
	int LOADING_STATE_EMPTY = 2;
	int LOADING_STATE_LOADING = 3;
	int LOADING_STATE_NOMORE = 4;
	int LOADING_STATE_ERROR = 5;

	/**
	 * 加载状态改变时
	 * @param state 状态值
	 */
	void onLoadingStateChanged(int state);

	/**
	 * 开始加载时
	 * @param loadType 加载类型
	 */
	void onLoadingTypeChanged(int loadType);
}
