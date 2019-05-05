package me.whaless.app.presentation.dagger.modules;


import dagger.Module;
import dagger.Provides;
import me.whaless.app.domain.interactor.UseCase;
import me.whaless.app.domain.interactor.user.UserInfoUseCaseImpl;
import me.whaless.app.domain.interactor.user.UserSignInUseCaseImpl;
import me.whaless.app.domain.interactor.user.UserSignUpUseCaseImpl;
import me.whaless.app.domain.model.ResponseModel;
import me.whaless.app.domain.model.user.SignEditor;
import me.whaless.app.domain.model.user.UserModel;
import me.whaless.app.presentation.Constants;

import javax.inject.Named;

/**
 * User: Joy
 * Date: 2017/1/3
 * Time: 11:06
 */
@Module
public class UserModule {


    @Provides
    @Named(Constants.NAMED_USER_SIGN_IN)
	UseCase<SignEditor, UserModel> provideSignInUseCase(UserSignInUseCaseImpl useCase) {
        return useCase;
    }

    @Provides
    @Named(Constants.NAMED_USER_SIGN_UP)
    UseCase<SignEditor, ResponseModel> provideSignUpUseCase(UserSignUpUseCaseImpl useCase) {
        return useCase;
    }


    @Provides
    @Named(Constants.NAMED_USER_INFO)
    UseCase<Object, UserModel> provideInfoUseCase(UserInfoUseCaseImpl useCase) {
        return useCase;
    }

}
