package me.whaless.app.data.net.api.service;

import me.whaless.app.data.entity.ResponseEntity;
import me.whaless.app.data.entity.user.UserEntity;
import me.whaless.app.data.net.api.aidl.IUserRetrofit;
import me.whaless.app.data.net.user.IUserNet;
import me.whaless.app.domain.model.user.SignEditor;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017/1/3
 * Time: 16:04
 */
@Singleton
public class UserRetrofitNetImpl extends BaseRetrofitNetImpl<IUserRetrofit> implements IUserNet {

	@Inject
	UserRetrofitNetImpl() {

	}

	@Override
	public Observable<UserEntity> signIn(SignEditor editor) {
		return getService().signIn(editor.getUsername(), editor.getPassword());
	}

	@Override
	public Observable<ResponseEntity> signUp(SignEditor editor) {
		return getService().signUp(editor.getUsername(), editor.getPassword());
	}

	@Override
	public Observable<UserEntity> info() {
		return getService().info();
	}

}
