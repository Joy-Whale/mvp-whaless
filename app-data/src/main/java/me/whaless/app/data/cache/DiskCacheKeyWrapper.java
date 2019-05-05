package me.whaless.app.data.cache;


import me.whaless.app.common.encode.MD5;
import me.whaless.app.domain.model.Listable;

/**
 * User: JiYu
 * Date: 2016-09-14
 * Time: 15-35
 * 用来包装key
 */

public class DiskCacheKeyWrapper {

	public static String create(String key) {
		return MD5.encode(key);
	}

	public static String create(String key, String id) {
		return MD5.encode(key + id);
	}

	public static String create(String key, Listable listable) {
		return MD5.encode(key + listable.toString());
	}
}
