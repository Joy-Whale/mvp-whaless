package me.whaless.app.domain.repository;

import me.whaless.app.domain.model.Listable;
import me.whaless.app.domain.model.ResponseModel;
import me.whaless.app.domain.model.common.TestListModel;
import rx.Observable;

/**
 * User: Joy
 * Date: 2017/1/13
 * Time: 10:19
 */

public interface ICommonRepository {

	Observable<ResponseModel> test(String test);

	Observable<TestListModel> testList(Listable listable);

}
