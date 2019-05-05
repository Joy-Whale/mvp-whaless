package me.whaless.app.common.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * User: JiYu
 * Date: 2016-09-04
 * Time: 18-18
 */

public class MapperImpl<T, Q> implements Mapper<T, Q> {

	protected MapperImpl() {

	}

	@Override
	public Q transform(T o2) {
		return null;
	}

	@Override
	public T transform2(Q q) {
		return null;
	}

	/**
	 * 去重处理
	 * @param qs 需要去重的元数据
	 */
	public List<Q> transform(List<T> qs) {
		if (qs == null)
			return null;
		List<Q> ts = new ArrayList<>();
		for (T q : qs) {
			if (q != null)
				ts.add(transform(q));
		}
		return ts;
	}

	public List<T> transform2(List<Q> qs) {
		if (qs == null)
			return null;
		List<T> ts = new ArrayList<>();
		for (Q q : qs) {
			if (q != null)
				ts.add(transform2(q));
		}
		return ts;
	}

	protected <E> List<E> filterNull(List<E> list) {
		if (list == null)
			return null;
		list.removeAll(Collections.singleton(null));
		return list;
	}

	protected <R> R parse(String js, Class<R> clz) {
		return JsonParser.parseObject(js, clz);
	}

	protected <R> List<R> parseArray(String js, Class<R> clz) {
		try {
			return JsonParser.parseArray(js, clz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected int parseInteger(String str) {
		if (TextUtils.isEmpty(str) || "null".equals(str)) {
			return 0;
		}
		try {
			return parseInt(str);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	protected String parseString(int i) {
		return String.valueOf(i);
	}

	protected String parseString(boolean b) {
		return b ? "1" : "0";
	}

	protected boolean parseBoolean(String s) {
		return !TextUtils.isEmpty(s) && s.equals("1");
	}

	protected String noEmpty(String ... values){
		return FieldUtils.noEmpty(values);
	}
}
