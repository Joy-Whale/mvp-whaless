package me.whaless.app.presentation.dagger;

import me.whaless.app.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * User: JiYu
 * DateModel: 2016-08-09
 * Time: 21-02
 */
@Singleton
public class UIThread implements PostExecutionThread {

	@Inject
	public UIThread(){

	}

	@Override
	public Scheduler getScheduler() {
		return AndroidSchedulers.mainThread();
	}
}
