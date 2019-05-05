package me.whaless.app.dagger.components;

import me.whaless.app.presentation.dagger.modules.CommonModule;
import me.whaless.app.presentation.dagger.modules.UserModule;
import me.whaless.app.presentation.dagger.scope.PerActivity;

import dagger.Component;

/**
 * User: Joy
 * Date: 2017-01-15
 * Time: 11:23
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {UserModule.class, CommonModule.class})
public interface UserComponent {

}
