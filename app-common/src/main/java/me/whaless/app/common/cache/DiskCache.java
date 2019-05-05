package me.whaless.app.common.cache;

import android.content.Context;
import android.text.TextUtils;
import com.jakewharton.disklrucache.DiskLruCache;
import com.whaless.app.common.BuildConfig;
import me.whaless.app.common.AppKeeper;
import me.whaless.app.common.encode.MD5;
import me.whaless.app.common.utils.JsonParser;

import java.io.*;
import java.util.List;

/**
 * User: JiYu
 * Date: 2016-09-05
 * Time: 16-45
 * 自动加密key
 */

public class DiskCache {

	private static final int MAX_SIZE = 30 * 1024 * 1024;

	public enum OverTime {
		Never(-1),
		TenMinute(600000),
		Hour(3600000),
		Day(86400000),
		Week(604800000),
		Month(18144000000L);

		long overTime;

		OverTime(long overTime) {
			this.overTime = overTime;
		}

		public boolean isOverTime(long saveTime) {
			return this != Never && getOverTime() + saveTime - System.currentTimeMillis() < 0;
		}

		public long getOverTime() {
			return overTime;
		}
	}

	private String cachePath;
	private int versionCode;

	private DiskLruCache cache;
	// 是否进行key加密包装
	private boolean isWrapKey = false;

	public DiskCache() {
		this(AppKeeper.getInstance().getCachePath());
	}

	public DiskCache(Context context) {
		this(AppKeeper.newInstance(context).getCachePath());
	}

	public DiskCache(String cachePath) {
		this(cachePath, BuildConfig.VERSION_CODE);
	}

	public DiskCache(String cachePath, int versionCode) {
		this.cachePath = cachePath;
		this.versionCode = versionCode;
	}

	public void setWrapKey(boolean wrapKey) {
		this.isWrapKey = wrapKey;
	}

	public boolean isWrapKey() {
		return isWrapKey;
	}

	/**
	 * 设置缓存目录
	 * @param cachePath 缓存目录
	 */
	public void setCachePath(String cachePath) {
		this.cachePath = cachePath;
	}

	/**
	 * 保存对象到disk
	 * @param key key
	 * @param t   需要保存的对象
	 * @param <T> 泛型
	 * @return 保存的路径
	 */
	public synchronized <T> String put(String key, T t) {
		final DiskLruCache cache = getCache();
		if (cache == null)
			return null;
		final String path = wrapKey(key);
		DiskLruCache.Editor editor;
		try {
			editor = cache.edit(path);
			DataOutputStream dataOutputStream = new DataOutputStream(editor.newOutputStream(0));
			dataOutputStream.writeLong(System.currentTimeMillis());
			JsonParser.writeJson(dataOutputStream, t);
			editor.commit();
			cache.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return cache.getDirectory() + "/" + path;
	}

	public synchronized <T> T getObject(String key, Class<T> clazz) {
		return getObject(key, clazz, OverTime.Never);
	}

	public synchronized <T> T getObject(String key, Class<T> clazz, OverTime overTime) {
		String str = getString(wrapKey(key), overTime);
		if (TextUtils.isEmpty(str)) {
			return null;
		}
		return JsonParser.parseObject(str, clazz);
	}

	public synchronized <T> List<T> getArray(String key, Class<T> clazz) {
		return getArray(key, clazz, OverTime.Never);
	}

	public synchronized <T> List<T> getArray(String key, Class<T> clazz, OverTime overTime) {
		String str = getString(wrapKey(key), overTime);
		if (TextUtils.isEmpty(str)) {
			return null;
		}
		return JsonParser.parseArray(str, clazz);
	}

	private synchronized String getString(String key, OverTime overTime) {
		InputStream input = getInputStream(key, overTime);
		if (input == null)
			return null;
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		StringBuilder buffer = new StringBuilder();
		String line;
		try {
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}

	private synchronized InputStream getInputStream(String key, OverTime overTime) {
		final DiskLruCache cache = getCache();
		if (cache == null)
			return null;
		DiskLruCache.Snapshot snapshot;
		DataInputStream dataInputStream;
		try {
			snapshot = cache.get(wrapKey(key));
			if (snapshot == null) {
				return null;
			}
			dataInputStream = new DataInputStream(snapshot.getInputStream(0));
			long saveTime = dataInputStream.readLong();
			if (overTime.isOverTime(saveTime)) {
				remove(key);
				return null;
			}
			return dataInputStream;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 包装key
	 * @param key key
	 */
	private String wrapKey(String key) {
		if (isWrapKey)
			return MD5.encode(key);
		else
			return key;
	}

	//	public <T> Observable put(final String key, final T t) {
	//		return Observable.create(new Observable.OnSubscribe<String>() {
	//			@Override
	//			public void call(Subscriber<? super String> sb) {
	//				if (put2(key, t)) {
	//					sb.onNext("");
	//					sb.onCompleted();
	//				} else {
	//					sb.onError(new NullPointerException());
	//				}
	//			}
	//		}).subscribeOn(Schedulers.newThread()).onErrorReturn(new Func1<Throwable, String>() {
	//			@Override
	//			public String call(Throwable throwable) {
	//				return "";
	//			}
	//		});
	//	}

	public synchronized boolean remove(String key) {
		final DiskLruCache cache = getCache();
		if (cache == null)
			return false;
		try {
			return cache.remove(wrapKey(key));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private synchronized boolean tryOpen() {
		if (cache == null || cache.isClosed()) {
			File f = new File(cachePath);
			if (!f.exists() && f.mkdirs()) {
			}
			try {
				cache = DiskLruCache.open(f, versionCode, 1, MAX_SIZE);
			} catch (IOException e) {
				e.printStackTrace();
				clear();
			}
		}
		return true;
	}

	public synchronized void clear() {
		final DiskLruCache cache = getCache();
		if (cache == null)
			return;
		try {
			cache.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private DiskLruCache getCache() {
		if (tryOpen())
			return cache;
		return null;
	}
}
