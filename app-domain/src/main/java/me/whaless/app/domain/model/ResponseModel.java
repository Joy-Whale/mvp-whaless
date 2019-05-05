package me.whaless.app.domain.model;

/**
 * User: Joy
 * DateModel: 2016-11-19
 * Time: 20:14
 */

public class ResponseModel {

	private String resultCode = "0";
	private String resultMessage;
	private String result;

	public ResponseModel() {
		
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
