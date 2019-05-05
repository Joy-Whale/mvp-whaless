package me.whaless.app.data.net.api.service;


import me.whaless.app.data.entity.ResponseEntity;
import me.whaless.app.data.entity.common.TestListEntity;
import me.whaless.app.data.net.api.aidl.IGeneralRetrofit;
import me.whaless.app.data.net.common.ICommonNet;
import me.whaless.app.domain.model.Listable;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017/1/3
 * Time: 16:04
 */
@Singleton
public class CommonRetrofitNetImpl extends BaseRetrofitNetImpl<IGeneralRetrofit> implements ICommonNet {

	@Inject
	CommonRetrofitNetImpl() {

	}

	@Override
	public Observable<ResponseEntity> test(String test) {
		return getService().test(test);
	}

	@Override
	public Observable<TestListEntity> testList(Listable listable) {
		return getService().testList(listable.getPage(), listable.getPageSize());
	}
}
