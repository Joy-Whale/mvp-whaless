package me.whaless.app.data.store;


import me.whaless.app.data.cache.ICache;
import me.whaless.app.data.db.IDb;
import me.whaless.app.data.net.INet;

import javax.inject.Inject;

/**
 * User: JiYu
 * Date: 2016-09-05
 * Time: 10-05
 * @param <R> retrofit service类型
 */
public class NetDataStoreImpl3<R extends INet, C extends ICache, D extends IDb> implements IDataStore {

	private R service;
	private C cache;
	private D db;

	@Inject
	protected NetDataStoreImpl3(R service, C cache, D db) {
		this.service = service;
		this.cache = cache;
		this.db = db;
	}

	protected R getService() {
		return service;
	}

	protected C getCache() {
		return cache;
	}

	protected D getDb() {
		return db;
	}
}
