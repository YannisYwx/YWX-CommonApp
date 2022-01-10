package com.ywx.common.net.api;

import com.ywx.common.net.model.OnlineDialFace;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Author: YWX
 * Date: 2021/12/16 16:28
 * Description: Veepoo网络接口
 */
public interface VeepooApi {

    @GET("system/getthemes")
    Observable<Response<List<OnlineDialFace>>> getUiTheme(@QueryMap Map<String, String> queryParma);
}
