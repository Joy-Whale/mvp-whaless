package me.whaless.app.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import me.whaless.app.data.net.api.builder.HttpErrorCodeManager;

/**
 * User: Joy
 * Date: 2017/5/22
 * Time: 11:03
 */

public class ResponseEntity {

	public static ResponseEntity success(){
		ResponseEntity response = new ResponseEntity();
		response.setCode(HttpErrorCodeManager.CODE_SUCCESS_RESULT);
		return response;
	}

	@JSONField(name = "code")
	private String code;
	private String message;
	@JSONField(name = "result")
	private String result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
