package me.whaless.app.presentation.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User: Joy
 * Date: 2017/5/22
 * Time: 13:47
 */

public class Response implements Parcelable {

	private String code;
	private String message;
	private String result;

	public Response(String code, String message) {
		this.code = code;
		this.message = message;
	}

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.code);
		dest.writeString(this.message);
	}

	public Response() {
	}

	protected Response(Parcel in) {
		this.code = in.readString();
		this.message = in.readString();
	}

	public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
		@Override
		public Response createFromParcel(Parcel source) {
			return new Response(source);
		}

		@Override
		public Response[] newArray(int size) {
			return new Response[size];
		}
	};

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
