package me.whaless.app.data.store.common;

import me.whaless.app.data.entity.ResponseEntity;
import me.whaless.app.data.entity.common.TestListEntity;
import me.whaless.app.data.store.IDataStore;
import me.whaless.app.domain.model.Listable;
import rx.Observable;

/**
 * User: Joy
 * Date: 2017/1/13
 * Time: 11:01
 */

public interface IGeneralDataStore extends IDataStore {


	Observable<ResponseEntity> test(String test);

	Observable<TestListEntity> testList(Listable listable);

}
