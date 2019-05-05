package me.whaless.app.presentation.presenter.user;

import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.annotation.NonNull;
import me.whaless.app.common.utils.FieldUtils;
import me.whaless.app.domain.interactor.UseCase;
import me.whaless.app.domain.model.user.SignEditor;
import me.whaless.app.domain.model.user.UserModel;
import com.whaless.app.presentation.BuildConfig;
import me.whaless.app.presentation.Constants;
import com.whaless.app.presentation.R;
import me.whaless.app.presentation.bean.user.User;
import me.whaless.app.presentation.bm.user.UserMapper;
import me.whaless.app.presentation.presenter.SimpleLoadingPresenterImpl;
import me.whaless.app.presentation.view.user.ISignInView;

public class UserSignInPresenterImpl extends SimpleLoadingPresenterImpl<SignEditor, UserModel, User, ISignInView> {

    private UserMapper mapper;
    
    @Inject
    UserSignInPresenterImpl(@Named(Constants.NAMED_USER_SIGN_IN) @NonNull UseCase<SignEditor, UserModel> useCase, UserMapper mapper) {
        super(useCase);
        this.mapper = mapper;
    }

	@Override
	public void initialize(SignEditor... qs) {
		SignEditor editor = qs[0];
		if(FieldUtils.isEmpty(editor.getUsername())){
			showMessage(R.string.error_input_account);
			return;
		}
		if(editor.getPassword().length() < BuildConfig.PASSWORD_MIN_LENGTH){
			showMessage(R.string.error_input_password_length);
			return;
		}
		super.initialize(qs);
	}

	@Override
    public void onNext(UserModel response) {
        super.onNext(response);
        getView().onSignInSuccess(mapper.transform(response));
		RxBus.get().post(Constants.RxTag.LOGIN_STATE_CHANGED, Boolean.valueOf(true));
    }
}

