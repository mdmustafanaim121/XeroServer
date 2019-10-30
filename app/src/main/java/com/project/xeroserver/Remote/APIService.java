package com.project.xeroserver.Remote;

import com.project.xeroserver.Model.MyResponse;
import com.project.xeroserver.Model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAwUAvpfg:APA91bGwNaFoCTCyWE_ffEDD5mTvmtvSWt99E7-6ftT1TXLtphs9KPK2L-CIIPlaTqngiDf5o0i7pUik6c-TEJ-a7xf6hWP_wMh4Pe8aUXkIZBD0REC1_06KPkhwqcP_bjBWm8vJ2uh-"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
