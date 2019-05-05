package me.whaless.app.presentation.view.user;

import me.whaless.app.presentation.view.ILoadingView;
import me.whaless.app.presentation.bean.user.User;

public interface ISignInView extends ILoadingView {
    void onSignInSuccess(User user);
}

