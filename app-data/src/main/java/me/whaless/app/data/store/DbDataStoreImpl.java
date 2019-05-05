package me.whaless.app.data.store;

import me.whaless.app.data.db.IDb;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * User: JiYu
 * DateModel: 2016-09-05
 * Time: 17-48
 */
@Singleton
public class DbDataStoreImpl<T extends IDb> implements IDataStore {

	private T db;

	@Inject
	protected DbDataStoreImpl(T db) {
		this.db = db;
	}

	public T getDb() {
		return db;
	}
}
