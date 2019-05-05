package me.whaless.app.presentation.bm;

import me.whaless.app.common.utils.MapperImpl;
import me.whaless.app.domain.model.ResponseModel;
import me.whaless.app.presentation.bean.Response;

import javax.inject.Inject;

/**
 * User: Joy
 * Date: 2017/5/22
 * Time: 13:47
 */

public class ResponseMapper extends MapperImpl<ResponseModel, Response> {

	@Inject
	ResponseMapper() {
	}

	@Override
	public Response transform(ResponseModel r2) {
		if (r2 == null)
			return null;
		Response r = new Response();
		r.setCode(r2.getResultCode());
		r.setMessage(r2.getResultMessage());
		r.setResult(r2.getResult());
		return r;
	}
}
