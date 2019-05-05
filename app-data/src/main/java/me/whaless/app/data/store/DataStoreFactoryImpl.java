package me.whaless.app.data.store;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: JiYu
 * DateModel: 2016-09-05
 * Time: 10-19
 * @param <D> IDataStore 类型
 */
@Singleton
public class DataStoreFactoryImpl<D extends IDataStore> {

	private Context context;
	private D apiDataStore;
	private D cacheDataStore;
	private D dbDataStore;

	/**
	 * 构造参数
	 * @param context      context
	 * @param apiDataStore 需要生成的ApiDataStore
	 */
	@Inject
	protected DataStoreFactoryImpl(Context context, D apiDataStore, D cacheDataStore, D dbDataStore) {
		this.context = context;
		this.apiDataStore = apiDataStore;
		this.cacheDataStore = cacheDataStore;
		this.dbDataStore = dbDataStore;
	}

	public Context getContext() {
		return context;
	}

	/**
	 * 获取网络接口Store
	 */
	public D getNetDataStore() {
		return apiDataStore;
	}

	/**
	 * 获取本地缓存Store
	 */
	public D getCacheDataStore() {
		return cacheDataStore;
	}

	public D getDbDataStore(){
		return dbDataStore;
	}
}
