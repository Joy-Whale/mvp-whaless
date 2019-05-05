package me.whaless.app.data.exception;

import me.whaless.app.common.utils.FieldUtils;

import java.io.IOException;

/**
 * UserEntity: JiYu
 * DateModel: 2016-09-01
 * Time: 16-29
 */

public class HttpResponseException extends IOException {

	private String message;
	private int code;

	public HttpResponseException() {
	}


	public HttpResponseException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public void setErrorCode(int code){
		this.code = code;
	}

	public int getErrorCode() {
		return code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return FieldUtils.noEmpty(message, super.getMessage());
	}
}
