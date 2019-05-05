package me.whaless.app.data.store;


import me.whaless.app.data.cache.ICache;
import me.whaless.app.data.net.INet;

import javax.inject.Inject;

/**
 * User: JiYu
 * Date: 2016-09-05
 * Time: 10-05
 * @param <R> retrofit service类型
 */
public class NetDataStoreImpl2<R extends INet, C extends ICache> implements IDataStore {

	private R service;
	private C cache;

	@Inject
	protected NetDataStoreImpl2(R service, C cache) {
		this.service = service;
		this.cache = cache;
	}

	protected R getService() {
		return service;
	}

	protected C getCache() {
		return cache;
	}

}
