package me.whaless.app.common.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * User: JiYu
 * Date: 2016-08-09
 * Time: 15-18
 */

public class Logs {

	public static boolean DEBUG = false;
	private static final String TAG = "joy";

	public static void init() {
		Logger.init(TAG).methodCount(2).hideThreadInfo();
	}

	public static void _d(String tag, String msg) {
		if (DEBUG) {
			if (tag == null || tag.length() == 0 || msg == null || msg.length() == 0)
				return;
			int segmentSize = 3 * 1024;
			long length = msg.length();
			if (length <= segmentSize) {// 长度小于等于限制直接打印
				Log.d(tag, msg);
			} else {
				while (msg.length() > segmentSize) {// 循环分段打印日志
					String logContent = msg.substring(0, segmentSize);
					msg = msg.replace(logContent, "");
					Log.d(tag, logContent);
				}
				Log.d(tag, msg);// 打印剩余日志
			}
		}
	}

	public static void _d(String msg) {
		_d(TAG, msg);
	}

	public static void _e(String tag, String msg) {
		if (DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void _e(String msg) {
		_e(TAG, msg);
	}

	public static void d(Object obj) {
		if (DEBUG) {
			try {
				Logger.d(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void d(String msg, Object... objs) {
		if (DEBUG) {
			try {
				Logger.d(msg, objs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void e(String msg, Object... objs) {
		if (DEBUG) {
			try {
				Logger.e(msg, objs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void i(String msg, Object... objs) {
		if (DEBUG) {
			try {
				Logger.i(msg, objs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void v(String msg, Object... objs) {
		if (DEBUG) {
			try {
				Logger.v(msg, objs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void j(String json) {
		if (DEBUG) {
			try {
				Logger.json(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
