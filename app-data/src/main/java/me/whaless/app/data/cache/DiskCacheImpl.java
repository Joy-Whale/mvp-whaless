package me.whaless.app.data.cache;

import android.content.Context;


import me.whaless.app.common.cache.DiskCache;
import me.whaless.app.common.cache.RxDiskCache;
import me.whaless.app.data.exception.CacheNotFoundException;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;


/**
 * User: JiYu
 * Date: 2016-09-05
 * Time: 17-48
 */

@Singleton
public class DiskCacheImpl extends RxDiskCache {

	@Inject
	DiskCacheImpl(Context context) {
		super(context);
	}

	DiskCacheImpl(String cachePath) {
		super(cachePath);
	}

	@Override
	public <T> Observable<T> getObject(String key, Class<T> clazz, DiskCache.OverTime overTime) {
		return super.getObject(key, clazz, overTime).onErrorResumeNext(Observable.error(new CacheNotFoundException()));
	}

	@Override
	public <T> Observable<List<T>> getArray(String key, Class<T> clazz, DiskCache.OverTime overTime) {
		return super.getArray(key, clazz, overTime).onErrorResumeNext(Observable.error(new CacheNotFoundException()));
	}
}
