package me.whaless.app.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * UserEntity: JiYu
 * DateModel: 2016-09-01
 * Time: 16-32
 */

public class HttpResponseErrorResult implements Parcelable {

	private String errorCode;

	private String message;

	public HttpResponseErrorResult(String errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public HttpResponseErrorResult() {
	}


	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.errorCode);
		dest.writeString(this.message);
	}

	protected HttpResponseErrorResult(Parcel in) {
		this.errorCode = in.readString();
		this.message = in.readString();
	}

	public static final Creator<HttpResponseErrorResult> CREATOR = new Creator<HttpResponseErrorResult>() {
		@Override
		public HttpResponseErrorResult createFromParcel(Parcel source) {
			return new HttpResponseErrorResult(source);
		}

		@Override
		public HttpResponseErrorResult[] newArray(int size) {
			return new HttpResponseErrorResult[size];
		}
	};
}
