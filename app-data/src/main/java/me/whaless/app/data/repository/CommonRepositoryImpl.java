package me.whaless.app.data.repository;


import me.whaless.app.data.em.ResponseEntityMapper;
import me.whaless.app.data.em.common.TestListEntityMapper;
import me.whaless.app.data.store.common.GeneralDataStoreFactory;
import me.whaless.app.data.store.common.IGeneralDataStore;
import me.whaless.app.domain.model.Listable;
import me.whaless.app.domain.model.ResponseModel;
import me.whaless.app.domain.model.common.TestListModel;
import me.whaless.app.domain.repository.ICommonRepository;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017-01-15
 * Time: 13:55
 */
@Singleton
public class CommonRepositoryImpl extends RepositoryImpl<IGeneralDataStore, GeneralDataStoreFactory> implements ICommonRepository {

	private ResponseEntityMapper responseEntityMapper;
	private TestListEntityMapper testListMapper;

	@Inject
    CommonRepositoryImpl(GeneralDataStoreFactory dataStoreFactory, TestListEntityMapper testListMapper, ResponseEntityMapper responseEntityMapper) {
		super(dataStoreFactory);
		this.testListMapper = testListMapper;
		this.responseEntityMapper = responseEntityMapper;
	}

	@Override
	public Observable<ResponseModel> test(String test) {
		return getNetDataStore().test(test).map(responseEntityMapper::transform);
	}

	@Override
	public Observable<TestListModel> testList(Listable listable) {
		return getNetDataStore().testList(listable).map(testListMapper::transform);
	}
}
