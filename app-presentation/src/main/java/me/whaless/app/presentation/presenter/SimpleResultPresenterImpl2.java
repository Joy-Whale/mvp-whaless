package me.whaless.app.presentation.presenter;

import androidx.annotation.NonNull;

import me.whaless.app.domain.interactor.UseCase;
import me.whaless.app.presentation.view.IView;

/**
 * User: Joy
 * Date: 2017-01-15
 * Time: 9:54
 */

public abstract class SimpleResultPresenterImpl2<RQ, RPM, RP, V extends IView> extends ResultPresenterImpl<RQ, RQ, RPM, RP, V> {


	protected SimpleResultPresenterImpl2(@NonNull UseCase<RQ, RPM> useCase) {
		super(useCase);
	}

	@Override
	public void initialize(RQ... qs) {
		execute(qs);
	}

}
