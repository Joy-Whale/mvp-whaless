package me.whaless.app.data.net.api.builder;

import androidx.annotation.NonNull;

/**
 * User: Joy
 * Date: 2017/1/11
 * Time: 16:34
 */

public class HttpErrorCodeManager {

	public static final String CODE_SUCCESS_RESULT = "0";
	public static final String CODE_SUCCESS_RESULT2 = "200";

	public static boolean isSuccess(@NonNull String code) {
		return code.equals(CODE_SUCCESS_RESULT2);
	}
}
