package me.whaless.app.data.repository;

import me.whaless.app.data.store.DataStoreFactoryImpl;
import me.whaless.app.data.store.IDataStore;
import me.whaless.app.domain.executor.ThreadExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * User: JiYu
 * DateModel: 2016-09-05
 * Time: 10-43
 */
@Singleton
class RepositoryImpl<D extends IDataStore, F extends DataStoreFactoryImpl<D>> {

	private F dataStoreFactory;
	private ThreadExecutor threadExecutor;

	@Inject
	RepositoryImpl(F dataStoreFactory) {
		this.dataStoreFactory = dataStoreFactory;
	}

	@Inject
	public void provideThreadExecutor(ThreadExecutor threadExecutor) {
		this.threadExecutor = threadExecutor;
	}

	F getDataStoryFactory() {
		return dataStoreFactory;
	}

	D getNetDataStore() {
		return dataStoreFactory.getNetDataStore();
	}

	D getCacheDataStore() {
		return dataStoreFactory.getCacheDataStore();
	}

	D getDbDataStore(){
		return dataStoreFactory.getDbDataStore();
	}

	private <T> void executeApi(Subscriber<? super T> sb, Observable<T> api) {
		api.subscribeOn(Schedulers.from(threadExecutor))
				.subscribe(sb::onNext, sb::onError, sb::onCompleted);
	}

	/**
	 * 先从缓存中获取，若不存在再加载网络中数据
	 * @param cache 缓存加载项
	 * @param api   网络加载项
	 * @param <T>   泛型
	 */
	<T> Observable<T> getCacheOrApi(Observable<T> cache, Observable<T> api) {
		return cache.onErrorResumeNext(api);
	}

//	/**
//	 * 列表类型的存取策略，如果是首次加载该列表，则先从缓存中获取
//	 * @param cache    缓存
//	 * @param api      网络
//	 * @param listable list
//	 * @param <T>      泛型
//	 */
//	<T> Observable<T> getListableCacheThenApi(Observable<T> cache, Observable<T> api, Listable listable) {
//		if (listable.isInitialLoad()) {
//			return getCacheThenApi(cache, api);
//		}
//		return api;
//	}
//
//	<T> Observable<T> getPageListCacheThenApi(Observable<T> cache, Observable<T> api, Listable listable){
//		if (listable.isInitialLoad()) {
//			return getCacheThenApi(cache, api);
//		}
//		return api;
//	}
}
