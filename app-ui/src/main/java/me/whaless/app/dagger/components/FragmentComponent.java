package me.whaless.app.dagger.components;

import androidx.fragment.app.Fragment;
import me.whaless.app.presentation.dagger.modules.FragmentModule;
import me.whaless.app.presentation.dagger.scope.PerFragment;
import dagger.Component;

/**
 * User: JiYu
 * Date: 2016-09-20
 * Time: 12-09
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
interface FragmentComponent {

	Fragment fragment();
}
