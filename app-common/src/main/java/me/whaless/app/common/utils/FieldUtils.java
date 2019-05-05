package me.whaless.app.common.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * User: Joy
 * Date: 2016/10/19
 * Time: 20:03
 */

public class FieldUtils {

	public static boolean isEmpty(String[] map){
		return map == null || map.length == 0;
	}

	public static boolean isEmpty(Map map){
		return map == null || map.isEmpty();
	}

	public static boolean isEmpty(Collection collection) {
		return collection == null || collection.size() == 0;
	}

	public static boolean isEmpty(CharSequence sequence) {
		return TextUtils.isEmpty(sequence) || TextUtils.isEmpty(sequence.toString().replace(" ", "").replace("\n", ""));
	}

	public static int getSize(Collection collection){
		if(isEmpty(collection))
			return 0;
		return collection.size();
	}

	public static String noEmpty(String... ss) {
		for (String s : ss) {
			if (!isEmpty(s))
				return s;
		}
		return "";
	}

	public static <T> List<T> noEmpty(List<T>... lists){
		for (List<T> list : lists){
			if(!isEmpty(list))
				return list;
		}
		return null;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (TextUtils.isEmpty(s))
			return s;
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");//去掉多余的0
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		return s;
	}

	public static void copyToClipBoard(Context context, String str) {
		ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		cm.setPrimaryClip(ClipData.newPlainText(null, str));
	}

	private static NumberFormat numberFormat = NumberFormat.getInstance();

	private static final String RULE_SPECIAL_SYMBOL = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

	private static final String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

	private static final String RULE_MOBILE = "^[1][0-9][0-9]{9}$";

	public static boolean isCollectEmpty(Collection c) {
		return c == null || c.size() == 0;
	}

	/**
	 * 正则匹配邮箱
	 * @param s email
	 */
	public static boolean matchEmail(String s) {
		return match(RULE_EMAIL, s);
	}

	/**
	 * 正则匹配手机
	 * @param s email
	 */
	public static boolean matchNumber(String s) {
		return match(RULE_MOBILE, s.replace(" ", ""));
	}

	/**
	 * 正则匹配特殊符号
	 * @param s email
	 */
	public static boolean matchSpecialSymbol(String s) {
		return match(RULE_SPECIAL_SYMBOL, s);
	}

	private static boolean match(String rule, String str) {
		return !TextUtils.isEmpty(str) && Pattern.compile(rule).matcher(str).matches();
	}

	public static int parseInt(Object o, int def){
		try {
			return parseInt(o.toString(), def);
		}catch (Exception e){
			return def;
		}
	}

	public static int parseInt(String s) {
		return parseInt(s, 0);
	}

	public static int parseInt(String s, int def){
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return def;
		}
	}

	public static long parseLong(String s){
		return parseLong(s, 0);
	}

	public static long parseLong(Object o, long def){
		if(o == null)
			return def;
		return parseLong(o.toString(), def);
	}

	public static long parseLong(String s, long def){
		try {
			return Long.parseLong(s);
		} catch (Exception e) {
			return def;
		}
	}

	public static float parseFloat(String s){
		try {
			return Float.parseFloat(s);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int parseColor(String color){
		if(color.startsWith("#") && color.length() == 7){
			return Color.parseColor(color);
		}
		if(color.length() == 6 || color.length() == 8){
			return Color.parseColor("#" + color);
		}
		return Color.TRANSPARENT;
	}

	/**
	 * 根据字符串的HashCode得出一个范围内int值
	 * @param key 字符串
	 * @param max 最大int
	 */
	public static int getHashInt(String key, int max) {
		return FieldUtils.isEmpty(key) ? 0 : Math.abs(key.hashCode()) % max;
	}
}
