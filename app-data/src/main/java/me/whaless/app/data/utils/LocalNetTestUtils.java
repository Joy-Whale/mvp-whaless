package me.whaless.app.data.utils;

import me.whaless.app.common.AppKeeper;
import me.whaless.app.common.utils.JsonParser;

import java.io.IOException;
import java.util.List;

import rx.Observable;

/**
 * User: Joy
 * Date: 2017-01-15
 * Time: 11:55
 */

public class LocalNetTestUtils {

	public static <T> Observable<T> testObj(String assetsName, Class<T> itemClass){
		try {
			return Observable.just(JsonParser.parseObject(AppKeeper.getInstance().getContext().getAssets().open(assetsName), itemClass));
		} catch (IOException e) {
			e.printStackTrace();
			return Observable.empty();
		}
	}

	public static <T> Observable<List<T>> testArray(String assetsName, Class<T> itemClass){
		try {
			return Observable.just(JsonParser.parseArray(AppKeeper.getInstance().getContext().getAssets().open(assetsName), itemClass));
		} catch (IOException e) {
			e.printStackTrace();
			return Observable.empty();
		}
	}
}
