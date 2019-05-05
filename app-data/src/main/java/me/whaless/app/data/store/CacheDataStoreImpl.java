package me.whaless.app.data.store;

import me.whaless.app.data.cache.ICache;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * User: JiYu
 * DateModel: 2016-09-05
 * Time: 17-48
 */
@Singleton
public class CacheDataStoreImpl<T extends ICache> implements IDataStore {

	private T cache;

	@Inject
	protected CacheDataStoreImpl(T cache) {
		this.cache = cache;
	}

	public T getCache() {
		return cache;
	}
}
