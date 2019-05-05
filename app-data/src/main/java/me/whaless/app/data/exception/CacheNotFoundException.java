package me.whaless.app.data.exception;

/**
 * User: JiYu
 * DateModel: 2016-09-12
 * Time: 09-57
 */

public class CacheNotFoundException extends NullPointerException {

	public CacheNotFoundException() {
		super("cache not found!!!");
	}
}
