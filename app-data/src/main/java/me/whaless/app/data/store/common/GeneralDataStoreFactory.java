package me.whaless.app.data.store.common;

import android.content.Context;

import me.whaless.app.data.store.DataStoreFactoryImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017/1/3
 * Time: 15:52
 */
@Singleton
public class GeneralDataStoreFactory extends DataStoreFactoryImpl<IGeneralDataStore> {

	/**
	 * 构造参数
	 * @param context        context
	 * @param apiDataStore   需要生成的ApiDataStore
	 */
	@Inject
	GeneralDataStoreFactory(Context context, NetGeneralDataStoreImpl apiDataStore) {
		super(context, apiDataStore, null, null);
	}
}
