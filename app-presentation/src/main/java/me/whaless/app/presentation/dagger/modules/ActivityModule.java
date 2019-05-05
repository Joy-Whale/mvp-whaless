package me.whaless.app.presentation.dagger.modules;

import android.app.Activity;

import me.whaless.app.presentation.dagger.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * User: JiYu
 * DateModel: 2016-08-09
 * Time: 12-10
 */

@Module
public class ActivityModule {

	private final Activity activity;

	public ActivityModule(Activity activity) {
		this.activity = activity;
	}

	@Provides
	@PerActivity
	Activity provideActivity() {
		return activity;
	}
}
