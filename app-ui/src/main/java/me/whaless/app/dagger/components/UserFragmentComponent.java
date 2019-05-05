package me.whaless.app.dagger.components;

import me.whaless.app.presentation.dagger.modules.UserModule;
import me.whaless.app.presentation.dagger.scope.PerFragment;

import dagger.Component;

/**
 * User: Joy
 * Date: 2017-01-15
 * Time: 11:23
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {UserModule.class})
public interface UserFragmentComponent {

}
