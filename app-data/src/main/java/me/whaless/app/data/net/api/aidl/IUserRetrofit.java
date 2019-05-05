package me.whaless.app.data.net.api.aidl;


import me.whaless.app.data.Constants;
import me.whaless.app.data.entity.ResponseEntity;
import me.whaless.app.data.entity.user.UserEntity;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * User: Joy
 * Date: 2017-01-14
 * Time: 11:41
 */

public interface IUserRetrofit {

	@FormUrlEncoded
	@POST(Constants.HttpHost.SIGN_IN)
	Observable<UserEntity> signIn(
			@Field("username") String user,
			@Field("password") String password
	);

	@GET(Constants.HttpHost.INFO)
	Observable<UserEntity> info();

	@FormUrlEncoded
	@POST(Constants.HttpHost.SIGN_UP)
	Observable<ResponseEntity> signUp(
			@Field("username") String user,
			@Field("password") String password
	);

}
