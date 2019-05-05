package me.whaless.app.presentation.dagger.modules;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import me.whaless.app.data.cache.user.IUserCache;
import me.whaless.app.data.cache.user.UserCacheImpl;
import me.whaless.app.data.executor.JobExecutor;
import me.whaless.app.data.net.api.service.CommonRetrofitNetImpl;
import me.whaless.app.data.net.api.service.UserRetrofitNetImpl;
import me.whaless.app.data.net.common.ICommonNet;
import me.whaless.app.data.net.user.IUserNet;
import me.whaless.app.data.repository.CommonRepositoryImpl;
import me.whaless.app.data.repository.UserRepositoryImpl;
import me.whaless.app.domain.executor.PostExecutionThread;
import me.whaless.app.domain.executor.ThreadExecutor;
import me.whaless.app.domain.repository.ICommonRepository;
import me.whaless.app.domain.repository.IUserRepository;
import me.whaless.app.presentation.dagger.UIThread;

import javax.inject.Singleton;

/**
 * User: JiYu
 * DateModel: 2016-08-09
 * Time: 12-12
 */

@Module
public class ApplicationModule {

	private final Application application;

	public ApplicationModule(Application application) {
		this.application = application;
	}

	@Provides
	@Singleton
	Context provideApplication() {
		return application;
	}

	@Provides
	@Singleton
	ThreadExecutor provideThreadExecutor(JobExecutor executor) {
		return executor;
	}

	@Provides
	@Singleton
	PostExecutionThread providePostExecutor(UIThread uiThread) {
		return uiThread;
	}

	/***********************************************************************/
	/***********************      Repository       *************************/
	/***********************************************************************/

	@Provides
	@Singleton
    ICommonRepository provideCommonRepository(CommonRepositoryImpl repository) {
		return repository;
	}

	@Provides
	@Singleton
	IUserRepository provideUserRepository(UserRepositoryImpl repository) {
		return repository;
	}


	/***********************************************************************/
	/**************************      cache       ***************************/
	/***********************************************************************/

	@Provides
	@Singleton
	IUserCache provideUserCache(UserCacheImpl cache){
		return cache;
	}

	/***********************************************************************/
	/**************************      db       ******************************/
	/***********************************************************************/

//	@Provides
//	@Singleton
//	IGeneralDb provideGeneralDb(GeneralDbImpl db) {
//		return db;
//	}

	/***********************************************************************/
	/**************************      net       *****************************/
	/***********************************************************************/

	@Provides
	@Singleton
	ICommonNet provideGeneralNet(CommonRetrofitNetImpl net) {
		return net;
	}

	@Provides
	@Singleton
	IUserNet provideUserNet(UserRetrofitNetImpl net) {
		return net;
	}

	//	@Provides
	//	@Singleton
	//	IMap provideMap(BaiduMapImpl map) {
	//		return map;
	//	}
}


