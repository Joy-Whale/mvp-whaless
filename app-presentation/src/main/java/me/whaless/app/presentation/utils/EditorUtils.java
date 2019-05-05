package me.whaless.app.presentation.utils;

import com.whaless.app.presentation.BuildConfig;

import java.util.regex.Pattern;

/**
 * Author: Acer
 * Date:   2019/4/9
 */
public class EditorUtils {

	/**
	 * 判断密码格式是否通过
	 * @param password 密码
	 */
	public static boolean matcherPassword(String password) {
		String regex = BuildConfig.PASSWORD_REGEX;
		return Pattern.compile(regex).matcher(password).find();
	}
}
