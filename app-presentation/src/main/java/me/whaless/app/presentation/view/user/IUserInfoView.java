package me.whaless.app.presentation.view.user;

import me.whaless.app.presentation.view.ILoadingView;
import me.whaless.app.presentation.bean.user.User;

public interface IUserInfoView extends ILoadingView {
    void onResponseUserInfo(User user);
}

