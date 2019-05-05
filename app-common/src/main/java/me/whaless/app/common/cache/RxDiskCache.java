package me.whaless.app.common.cache;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * User: JiYu
 * Date: 2016-09-09
 * Time: 18-37
 */

public class RxDiskCache {

	private DiskCache diskCache;

	public RxDiskCache() {
		diskCache = new DiskCache();
	}

	public RxDiskCache(Context context) {
		diskCache = new DiskCache(context);
	}

	public RxDiskCache(String cachePath) {
		diskCache = new DiskCache(cachePath);
	}

	public RxDiskCache(String cachePath, int versionCode) {
		diskCache = new DiskCache(cachePath, versionCode);
	}

	public Observable<String> put(final String key, final Object object) {
		return Observable.create((Observable.OnSubscribe<String>) sb -> {
			String path = putSync(key, object);
			if (!TextUtils.isEmpty(path)) {
				sb.onNext(path);
				sb.onCompleted();
			} else {
				sb.onError(new NullPointerException());
			}
		}).subscribeOn(Schedulers.newThread()).onErrorReturn(throwable -> "");
	}

	public String putSync(String key, Object object) {
		return diskCache.put(key, object);
	}

	public <T> Observable<T> getObject(String key, final Class<T> clazz) {
		return getObject(key, clazz, DiskCache.OverTime.Never);
	}

	public <T> Observable<T> getObject(final String key, final Class<T> clazz, final DiskCache.OverTime overTime) {
		Observable<T> ob = Observable.create(sb -> {
			T t = getObjectSync(key, clazz, overTime);
			if (t != null) {
				sb.onNext(t);
				sb.onCompleted();
			} else {
				sb.onError(new NullPointerException());
			}
		});
		return ob.subscribeOn(Schedulers.newThread());
	}

	public <T> Observable<List<T>> getArray(final String key, final Class<T> clazz) {
		return getArray(key, clazz, DiskCache.OverTime.Never);
	}

	public <T> Observable<List<T>> getArray(final String key, final Class<T> clazz, final DiskCache.OverTime overTime) {
		Observable<List<T>> ob = Observable.create(sb -> {
			List<T> t = getArraySync(key, clazz, overTime);
			if (t != null) {
				sb.onNext(t);
				sb.onCompleted();
			} else {
				sb.onError(new NullPointerException());
			}
		});
		return ob.subscribeOn(Schedulers.newThread());
	}

	public <T> T getObjectSync(String key, Class<T> clazz) {
		return diskCache.getObject(key, clazz);
	}

	public <T> T getObjectSync(String key, Class<T> clazz, DiskCache.OverTime overTime) {
		return diskCache.getObject(key, clazz, overTime);
	}

	public <T> List<T> getArraySync(String key, Class<T> clazz) {
		return diskCache.getArray(key, clazz);
	}

	public <T> List<T> getArraySync(String key, Class<T> clazz, DiskCache.OverTime overTime) {
		return diskCache.getArray(key, clazz, overTime);
	}
}
