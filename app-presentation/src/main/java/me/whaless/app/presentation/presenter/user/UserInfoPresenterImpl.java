package me.whaless.app.presentation.presenter.user;

import androidx.annotation.NonNull;

import me.whaless.app.domain.interactor.UseCase;
import me.whaless.app.presentation.Constants;
import me.whaless.app.presentation.bm.user.UserMapper;
import me.whaless.app.presentation.presenter.SimpleLoadingPresenterImpl;
import me.whaless.app.presentation.view.user.IUserInfoView;
import me.whaless.app.domain.model.user.UserModel;
import me.whaless.app.presentation.bean.user.User;

import javax.inject.Inject;
import javax.inject.Named;

public class UserInfoPresenterImpl extends SimpleLoadingPresenterImpl<Object, UserModel, User, IUserInfoView> {

	private UserMapper mapper;

	@Inject
	UserInfoPresenterImpl(@Named(Constants.NAMED_USER_INFO) @NonNull UseCase<Object, UserModel> useCase, UserMapper mapper) {
		super(useCase);
		this.mapper = mapper;
	}

	@Override
	public void onNext(UserModel response) {
		super.onNext(response);
		if (getView() != null)
			getView().onResponseUserInfo(mapper.transform(response));
	}
}

