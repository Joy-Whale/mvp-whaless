package me.whaless.app.domain.repository;


/**
 * **********************
 * Author: Joy
 * Date:   2017-12-20
 * Time:   11:28
 * **********************
 */


import me.whaless.app.domain.model.ResponseModel;
import me.whaless.app.domain.model.user.SignEditor;
import me.whaless.app.domain.model.user.UserModel;
import rx.Observable;


public interface IUserRepository {

	Observable<UserModel> signIn(SignEditor editor);

	Observable<ResponseModel> signUp(SignEditor editor);

    Observable<UserModel> info();
}



