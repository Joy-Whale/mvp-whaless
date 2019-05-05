package me.whaless.app.data.store.user;


import me.whaless.app.data.entity.ResponseEntity;
import me.whaless.app.data.entity.user.UserEntity;
import me.whaless.app.data.store.IDataStore;
import me.whaless.app.domain.model.user.SignEditor;
import rx.Observable;

/**
 * User: Joy
 * Date: 2017/1/13
 * Time: 11:01
 */

public interface IUserDataStore extends IDataStore {

    Observable<UserEntity> signIn(SignEditor editor);

    Observable<ResponseEntity> signUp(SignEditor editor);

    Observable<UserEntity> info();
}
