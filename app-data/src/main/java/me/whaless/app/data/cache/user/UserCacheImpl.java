package me.whaless.app.data.cache.user;

import me.whaless.app.data.cache.DiskCacheImpl;

import javax.inject.Inject;

/**
 * Author: Acer
 * Date:   2019/4/22
 */
public class UserCacheImpl implements IUserCache {

	private static final String KEY_VIP = "user_vip";

	private DiskCacheImpl cache;

	@Inject
	UserCacheImpl(DiskCacheImpl cache) {
		this.cache = cache;
	}

}
