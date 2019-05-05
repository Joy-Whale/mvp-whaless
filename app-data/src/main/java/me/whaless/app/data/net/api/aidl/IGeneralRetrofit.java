package me.whaless.app.data.net.api.aidl;

import me.whaless.app.data.Constants;
import me.whaless.app.data.entity.ResponseEntity;
import me.whaless.app.data.entity.common.TestListEntity;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * User: Joy
 * Date: 2017-01-14
 * Time: 11:41
 */

public interface IGeneralRetrofit {

	@FormUrlEncoded
	@POST(Constants.HttpHost.TEST)
	Observable<ResponseEntity> test(@Field(Constants.HttpParams.ID) String id);

	@FormUrlEncoded
	@POST(Constants.HttpHost.TEST_LIST)
	Observable<TestListEntity> testList(@Field(Constants.HttpParams.PAGE) int page,              //
			@Field(Constants.HttpParams.PAGE_SIZE) int pageSize      //
	);
}
