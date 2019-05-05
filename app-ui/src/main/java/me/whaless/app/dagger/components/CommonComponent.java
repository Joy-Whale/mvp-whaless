package me.whaless.app.dagger.components;

import me.whaless.app.presentation.dagger.modules.CommonModule;
import me.whaless.app.presentation.dagger.scope.PerActivity;
import me.whaless.app.ui.MainActivity;

import dagger.Component;

/**
 * User: Joy
 * Date: 2017-01-15
 * Time: 11:23
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {CommonModule.class})
public interface CommonComponent {

	void inject(MainActivity activity);
}
