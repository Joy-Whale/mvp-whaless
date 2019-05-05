package me.whaless.app.data.exception;

/**
 * User: Joy
 * Date: 2017/4/11
 * Time: 15:40
 */

public class UserLogoutException extends IllegalArgumentException {

	public UserLogoutException() {
	}

	public UserLogoutException(String detailMessage) {
		super(detailMessage);
	}
}
