package me.whaless.app.presentation.presenter.user;

import androidx.annotation.NonNull;
import com.whaless.app.presentation.BuildConfig;
import com.whaless.app.presentation.R;
import me.whaless.app.common.utils.FieldUtils;
import me.whaless.app.domain.interactor.UseCase;
import me.whaless.app.domain.model.ResponseModel;
import me.whaless.app.domain.model.user.SignEditor;
import me.whaless.app.presentation.Constants;
import me.whaless.app.presentation.bean.Response;
import me.whaless.app.presentation.bm.user.UserMapper;
import me.whaless.app.presentation.presenter.SimpleLoadingPresenterImpl;
import me.whaless.app.presentation.utils.EditorUtils;
import me.whaless.app.presentation.view.user.ISignUpView;

import javax.inject.Inject;
import javax.inject.Named;

public class UserSignUpPresenterImpl extends SimpleLoadingPresenterImpl<SignEditor, ResponseModel, Response, ISignUpView> {

	private UserMapper mapper;

	@Inject
	UserSignUpPresenterImpl(@Named(Constants.NAMED_USER_SIGN_UP) @NonNull UseCase<SignEditor, ResponseModel> useCase, UserMapper mapper) {
		super(useCase);
		this.mapper = mapper;
	}

	@Override
	public void initialize(SignEditor... qs) {
		SignEditor editor = qs[0];
		if (FieldUtils.isEmpty(editor.getUsername())) {
			showMessage(R.string.error_input_account);
			return;
		}
		if (FieldUtils.isEmpty(editor.getPassword())) {
			showMessage(R.string.error_input_password);
			return;
		}
		if (editor.getPassword().length() < BuildConfig.PASSWORD_MIN_LENGTH) {
			showMessage(R.string.error_input_password_length);
			return;
		}
		if (!EditorUtils.matcherPassword(editor.getPassword())) {
			showMessage(R.string.error_input_password_format);
			return;
		}
		super.initialize(qs);
	}

	@Override
	public void onNext(ResponseModel response) {
		super.onNext(response);
		getView().onSignUpSuccess();
	}
}

