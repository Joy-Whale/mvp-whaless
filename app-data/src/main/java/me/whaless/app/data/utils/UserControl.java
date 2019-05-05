package me.whaless.app.data.utils;

import me.whaless.app.common.sharedpreference.SharePreferencePlus;
import me.whaless.app.data.entity.user.UserEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017-01-21
 * Time: 20:58
 */
@Singleton
public class UserControl extends SharePreferencePlus {

	private static final String NAME = "user_info";

	private static final String ITEM_USER_LOGINED = "user_login";
	// token
	private static final String ITEM_USER_TOKEN = "user_token";
	// id
	private static final String ITEM_USER_ID = "user_id";
	// 账户
	private static final String ITEM_USER_NAME = "user_name";
	// 昵称
	private static final String ITEM_USER_NICK_NAME = "user_nick_name";
	// 头像
	private static final String ITEM_USER_AVATAR = "user_avatar";

	// 性别
	private static final String ITEM_USER_SEX = "user_sex";

	private static final String ITEM_EMAIL = "user_email";

	private static final String ITEM_PASSWORD = "user_password";

	private static final String ITEM_USER_BIRTHDAY = "user_birthday";

	private static final String ITEM_USER_CREATE_TIME = "user_create_time";

	private static final String ITEM_USER_ENABLE = "user_enable";

	private static UserControl uc;

	// The value of user if login at the application lifecycle
	private static boolean session = false;
	// 是否签到
	private static boolean sign = false;

	public static UserControl getInstance() {
		return uc == null ? uc = new UserControl() : uc;
	}

	@Inject
	UserControl() {
		super(NAME);
		uc = this;
	}

	public void onSignIn(UserEntity user) {
		logged();
		updateUser(user);
	}

	public void updateUser(UserEntity user) {
		session = true;
		setUserId(user.getId());
		setUsername(user.getUsername());
		setAvatar(user.getAvatar());
		setNickname(user.getNickname());
		setEmail(user.getEmail());
	}

	public void setToken(String token) {
		editStringValue(ITEM_USER_TOKEN, token);
	}

	public String getToken() {
		return getStringValue(ITEM_USER_TOKEN, "");
	}

	/**
	 * 获取当前用户id
	 */
	public String getUserId() {
		return getStringValue(ITEM_USER_ID, "");
	}

	/**
	 * 设置当前用户id
	 * @param id id
	 */
	private void setUserId(String id) {
		editStringValue(ITEM_USER_ID, id);
	}

	public String getUsername() {
		return getStringValue(ITEM_USER_NAME, "");
	}

	private void setUsername(String name) {
		editStringValue(ITEM_USER_NAME, name);
	}

	public void setPassword(String password) {
		editStringValue(ITEM_PASSWORD, password);
	}

	public String getPassword() {
		return getStringValue(ITEM_PASSWORD, "");
	}

	public String getEmail() {
		return getStringValue(ITEM_EMAIL, "");
	}

	public void setEmail(String email) {
		editStringValue(ITEM_EMAIL, email);
	}

	public void setBirthday(String birthday) {
		editStringValue(ITEM_USER_BIRTHDAY, birthday);
	}

	public String getBirthday() {
		return getStringValue(ITEM_USER_BIRTHDAY, "");
	}

	public String getAvatar() {
		return getStringValue(ITEM_USER_AVATAR, "");
	}

	private void setAvatar(String avatar) {
		editStringValue(ITEM_USER_AVATAR, avatar);
	}

	private void setNickname(String name) {
		editStringValue(ITEM_USER_NICK_NAME, name);
	}

	public String getNickname() {
		return getStringValue(ITEM_USER_NICK_NAME, "");
	}

	private void setSex(int department) {
		editIntValue(ITEM_USER_SEX, department);
	}

	public int getSex() {
		return Math.min(2, getIntValue(ITEM_USER_SEX, 1));
	}

	public void setCreateTime(String createTime) {
		editStringValue(ITEM_USER_CREATE_TIME, createTime);
	}

	public String getCreateTime() {
		return getStringValue(ITEM_USER_CREATE_TIME, "");
	}

	public boolean isEnabled() {
		return getBooleanValue(ITEM_USER_ENABLE, true);
	}

	public void setEnabled(boolean enabled) {
		editBooleanValue(ITEM_USER_ENABLE, enabled);
	}

	public boolean isSession() {
		return session;
	}

	public void setSession() {
		session = true;
	}

	/**
	 * 用户是否登录
	 */
	public boolean isLogin() {
		return getBooleanValue(ITEM_USER_LOGINED, false);
	}

	/**
	 * 设置用户已经登录
	 */
	private void logged() {
		editBooleanValue(ITEM_USER_LOGINED, true);
	}

	public void logout() {
		clear();
		session = false;
	}
}
