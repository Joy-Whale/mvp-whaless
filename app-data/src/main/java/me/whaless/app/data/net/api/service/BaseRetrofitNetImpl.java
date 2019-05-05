package me.whaless.app.data.net.api.service;

import com.alibaba.fastjson.parser.Feature;
import com.whaless.app.data.BuildConfig;
import me.whaless.app.data.net.api.builder.HttpInterceptor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * User: Joy
 * Date: 2016/11/3
 * Time: 17:26
 */

class BaseRetrofitNetImpl<T> {

	/**
	 * 默认的解析参数
	 */
	private static Feature[] parseFeature = {Feature.UseObjectArray};

	private T mServiceImpl;
	private Class<T> mServiceClass;

	BaseRetrofitNetImpl() {
		Type sType = getClass().getGenericSuperclass();
		Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
		mServiceClass = (Class<T>) (generics[0]);
	}

	/**
	 * 普通的Retrofit service实现
	 */
	protected T getService() {
		return mServiceImpl == null ? mServiceImpl = createRetrofit().create(mServiceClass) : mServiceImpl;
	}

	private Retrofit createRetrofit() {
		if (BuildConfig.DEBUG) {
			OkHttpClient client = null;
			try {
				TrustManager[] trustManagers = new TrustManager[]{
						new X509TrustManager() {
							@Override
							public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {

							}

							@Override
							public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {

							}

							@Override
							public java.security.cert.X509Certificate[] getAcceptedIssuers() {
								return new java.security.cert.X509Certificate[]{};
							}
						}
				};

				X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
				SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

				client = createDefaultOkHttpBuild() //
						.sslSocketFactory(sslSocketFactory, trustManager)    //
						.hostnameVerifier((s, sslSession) -> true)    //
						.build();
				return createDefaultBuilder().client(client).build();
			} catch (NoSuchAlgorithmException | KeyManagementException ae) {
				ae.printStackTrace();
			}
		}
		return createDefaultBuilder().client(createDefaultOkHttpBuild().build()).build();
	}

	private OkHttpClient.Builder createDefaultOkHttpBuild() {
		return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).addInterceptor(HttpInterceptor.create());
	}

	private Retrofit.Builder createDefaultBuilder() {
		return new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(FastJsonConverterFactory.create().setParserFeatures(parseFeature))
				.baseUrl(BuildConfig.HTTPS_HOST);
	}
}
