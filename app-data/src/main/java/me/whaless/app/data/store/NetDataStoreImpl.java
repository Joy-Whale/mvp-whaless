
package me.whaless.app.data.store;


import me.whaless.app.data.net.INet;

import javax.inject.Inject;

/**
 * User: JiYu
 * Date: 2016-09-05
 * Time: 10-05
 * @param <R> retrofit service类型
 */
public class NetDataStoreImpl<R extends INet> implements IDataStore {

	private R service;

	@Inject
	protected NetDataStoreImpl(R service) {
		this.service = service;
	}

	protected R getService() {
		return service;
	}
}
