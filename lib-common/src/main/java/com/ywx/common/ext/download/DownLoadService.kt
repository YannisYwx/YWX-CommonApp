package com.ywx.common.ext.download

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * Author: YWX
 * Date: 2021/9/15 18:02
 * Description:
 */
interface DownLoadService {
    @Streaming
    @GET
    suspend fun downloadFile(
            @Header("RANGE") start: String,
            @Url url: String
    ): Response<ResponseBody>
}