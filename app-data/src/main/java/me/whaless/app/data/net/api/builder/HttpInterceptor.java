package me.whaless.app.data.net.api.builder;

import androidx.annotation.NonNull;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whaless.app.data.BuildConfig;
import me.whaless.app.common.utils.DeviceHelper;
import me.whaless.app.common.utils.FieldUtils;
import me.whaless.app.data.Constants;
import me.whaless.app.data.exception.HttpResponseException;
import me.whaless.app.data.utils.UserControl;
import okhttp3.*;
import org.json.JSONException;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * User: Joy
 * Date: 2016/10/9
 * Time: 9:31
 */

public class HttpInterceptor implements Interceptor {

    public static HttpInterceptor create() {
        return new HttpInterceptor();
    }

    private static final String KEY_MSG = "message";
    private static final String KEY_CODE = "code";

    private HttpInterceptor() {

    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        // 请求体
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String url = request.url().toString();
        StringBuilder sb = new StringBuilder();
        sb.append("------------请求网络------------");
        sb.append("\nurl-----------\n").append(url);
        // 重新生成RequestBody，并添加共用参数 userId、deviceId
        if (request.method().equals("POST")) {
            RequestBody finalBody = null;
            if (request.body() instanceof MultipartBody) {
                url += "?userid=" + UserControl.getInstance().getUserId();
            } else {
                /* 普通请求方式 */
                FormBody.Builder fb = new FormBody.Builder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        if (!FieldUtils.isEmpty(body.value(i)))
                            fb.add(body.name(i), body.value(i));
                    }
                }
                FormBody body = fb.build();
                sb.append("\nparams-----------");
                for (int i = 0; i < body.size(); i++) {
                    sb.append("\n").append(body.name(i)).append(" : ").append(body.value(i));
                }
                finalBody = body;
            }
            if (finalBody != null)
                builder.post(finalBody);
        }
        // 添加DeviceID,DeviceType：1 android、2 ios
        try {
            builder.addHeader(Constants.HttpParams.DEVICE_ID, DeviceHelper.getDeviceId());
            builder.addHeader(Constants.HttpParams.DEVICE_NAME, URLEncoder.encode(DeviceHelper.getDeviceName(), "UTF-8"));
            builder.addHeader(Constants.HttpParams.DEVICE_TYPE, "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.url(url);

        // 响应体
        Response response = chain.proceed(builder.build());
        String bodyString = response.body().string();
        sb.append("\nresponse-----------\n");
        if (bodyString.startsWith("{")) {
            try {
                org.json.JSONObject jsonObject = new org.json.JSONObject(bodyString);
                sb.append(jsonObject.toString(2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (bodyString.startsWith("[")) {
            try {
                org.json.JSONArray jsonObject = new org.json.JSONArray(bodyString);
                sb.append(jsonObject.toString(2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            sb.append(bodyString);
        }
        sb.append("\n------------请求网络Over------------");

        // JsonObject
        if (bodyString.startsWith("{")) {
            JSONObject obj = JSON.parseObject(bodyString);
            if (obj == null) {
                throw new NullPointerException("");
            }
            // 获取数据出错类型的message
            if (obj.containsKey(KEY_MSG) && obj.containsKey(KEY_CODE)) {
                String code = obj.getString(KEY_CODE);
                String msg = obj.getString(KEY_MSG);
                if (!HttpErrorCodeManager.isSuccess(code)) {
                    // 错误
                    throw new HttpResponseException(FieldUtils.parseInt(code), msg);
                }
            }
        }
        // JsonArray
        else if (bodyString.startsWith("[")) {
            // do nothing
            if (BuildConfig.DEBUG) {

            }
        } else {
            // 若返回类型为String类型
            bodyString = createMessageData("");
        }

        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), bodyString)).build();
    }

    /**
     * 对那些没有返回data的统一将msg返回，以便做提示用
     *
     * @param message message
     */
    private static String createMessageData(String message) {
        return String.format("{'message':'%s'}", message);
    }
}
