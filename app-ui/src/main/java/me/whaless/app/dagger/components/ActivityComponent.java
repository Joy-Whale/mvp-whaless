package me.whaless.app.dagger.components;

import android.app.Activity;

import me.whaless.app.presentation.dagger.modules.ActivityModule;
import me.whaless.app.presentation.dagger.scope.PerActivity;
import dagger.Component;

/**
 * User: JiYu
 * DateModel: 2016-08-09
 * Time: 12-10
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent{

	Activity activity();
}
