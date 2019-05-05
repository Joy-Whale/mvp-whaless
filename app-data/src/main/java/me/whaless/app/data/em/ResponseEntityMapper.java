package me.whaless.app.data.em;

import me.whaless.app.common.utils.MapperImpl;
import me.whaless.app.data.entity.ResponseEntity;
import me.whaless.app.domain.model.ResponseModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Joy
 * Date: 2017/5/22
 * Time: 11:04
 */
@Singleton
public class ResponseEntityMapper extends MapperImpl<ResponseEntity, ResponseModel> {

	@Inject
	ResponseEntityMapper() {

	}

	@Override
	public ResponseModel transform(ResponseEntity r2) {
		if (r2 == null)
			return null;
		ResponseModel r = new ResponseModel();
		r.setResultCode(r2.getCode());
		r.setResultMessage(r2.getMessage());
		r.setResult(r2.getResult());
		return r;
	}
}
