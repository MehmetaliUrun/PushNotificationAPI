package com.example.pushnotificaitonwithapi

import com.example.pushnotificaitonwithapi.Constants.Companion.CONTENT_TYPE
import com.example.pushnotificaitonwithapi.Constants.Companion.SERVER_KEY
 //import com.google.android.gms.common.api.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.Response

interface NotificationApi {
    @Headers("Authorization: key=$SERVER_KEY","Content-Type:$CONTENT_TYPE")

    @POST("fcm/send")
    suspend fun postNotification(


        @Body notification: PushNotification
    ): Response<ResponseBody>

}