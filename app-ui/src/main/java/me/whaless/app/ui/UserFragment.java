package me.whaless.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.whaless.app.R;
import me.whaless.app.data.utils.UserControl;
import me.whaless.app.ui.user.SignInActivity;

/**
 * Author: Ji
 * Date:   2019/5/5
 */
public class UserFragment extends ParentFragment {

	static UserFragment newInstance() {
		return new UserFragment();
	}

	@BindView(R.id.imageAvatar)
	ImageView mImageAvatar;
	@BindView(R.id.textName)
	TextView mTextName;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View parent = inflater.inflate(R.layout.fragment_user, container, false);
		ButterKnife.bind(this, parent);
		return parent;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		updateUi();
	}

	@Override
	public void onResume() {
		super.onResume();
		updateUi();
	}

	private void updateUi() {
		if (UserControl.getInstance().isLogin()) {
			RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_loading_avatar).error(R.drawable.ic_loading_avatar);
			Glide.with(Objects.requireNonNull(getContext())).applyDefaultRequestOptions(options).load(mImageAvatar).into(mImageAvatar);
			mTextName.setText(UserControl.getInstance().getNickname());
		} else {
			mImageAvatar.setImageResource(R.drawable.ic_loading_avatar);
			mTextName.setText(R.string.user_sign_in);
		}
	}

	@OnClick({R.id.imageAvatar, R.id.textName})
	public void onViewClicked(View view){
		if(!UserControl.getInstance().isLogin()){
			startActivity(new Intent(getContext(), SignInActivity.class));
		}
	}
}
