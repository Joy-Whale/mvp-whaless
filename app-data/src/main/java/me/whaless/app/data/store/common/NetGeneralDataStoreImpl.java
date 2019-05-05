package me.whaless.app.data.store.common;


import me.whaless.app.data.entity.ResponseEntity;
import me.whaless.app.data.entity.common.TestListEntity;
import me.whaless.app.data.net.common.ICommonNet;
import me.whaless.app.data.store.NetDataStoreImpl;
import me.whaless.app.domain.model.Listable;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017/1/3
 * Time: 15:34
 */
@Singleton
class NetGeneralDataStoreImpl extends NetDataStoreImpl<ICommonNet> implements IGeneralDataStore {

	@Inject
	NetGeneralDataStoreImpl(ICommonNet service) {
		super(service);
	}


	@Override
	public Observable<ResponseEntity> test(String test) {
		return getService().test(test);
	}

	@Override
	public Observable<TestListEntity> testList(Listable listable) {
		return null;
		//	return getService().testList(listable).doOnNext(testListEntity -> getDb().saveTestList(testListEntity.getItems()));
	}
}
