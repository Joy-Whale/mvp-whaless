package me.whaless.app.presentation.dagger.modules;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

/**
 * User: JiYu
 * Date: 2016-09-20
 * Time: 12-10
 */

@Module
public class FragmentModule {

	protected final Fragment fragment;

	public FragmentModule(Fragment fragment) {
		this.fragment = fragment;
	}

	@Provides
	Fragment provideFragment() {
		return fragment;
	}
}
