package me.whaless.app.data;

/**
 * User: Joy
 * DateModel: 2016-11-20
 * Time: 22:06
 */

public class Constants {

	private static final String PATH = "";

	private static final String PATH_USER = PATH + "/users/";

	public interface HttpHost {

		String TEST = "/test";
		String TEST_LIST = "/test/list";

		String SIGN_IN = PATH_USER + "signIn";
		String SIGN_UP = PATH_USER + "signUp";
		String INFO = PATH_USER + "info";
	}


	public interface HttpParams {
		String ID = "id";
		String PAGE = "page";
		String PAGE_SIZE = "size";
		String DEVICE_ID = "deviceId";
		String DEVICE_NAME = "deviceName";
		String DEVICE_TYPE = "deviceType";
	}
}
