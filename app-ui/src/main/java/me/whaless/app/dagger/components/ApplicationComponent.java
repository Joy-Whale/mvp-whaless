package me.whaless.app.dagger.components;

import android.content.Context;

import javax.inject.Singleton;

import me.whaless.app.domain.executor.PostExecutionThread;
import me.whaless.app.domain.executor.ThreadExecutor;
import me.whaless.app.domain.repository.ICommonRepository;
import me.whaless.app.domain.repository.IUserRepository;
import me.whaless.app.presentation.dagger.modules.ApplicationModule;
import dagger.Component;
import me.whaless.app.ui.ParentActivity;

/**
 * User: JiYu
 * DateModel: 2016-08-09
 * Time: 14-57
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

	void inject(ParentActivity activity);

	Context context();

	ThreadExecutor threadScheduler();

	PostExecutionThread postScheduler();

	ICommonRepository commonRepository();

	IUserRepository userRepository();
}
