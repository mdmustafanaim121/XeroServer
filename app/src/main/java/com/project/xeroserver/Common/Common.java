package com.project.xeroserver.Common;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.project.xeroserver.Model.Request;
import com.project.xeroserver.Model.User;
import com.project.xeroserver.Remote.APIService;
import com.project.xeroserver.Remote.FCMRetrofitClient;
import com.project.xeroserver.Remote.GeoCoordinates;
import com.project.xeroserver.Remote.RetrofitClient;

import retrofit2.Retrofit;

public class Common {

    public static User currUser;
    public static Request currentRequest;


    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static final String baseUrl = "https://maps.googleapis.com/";

    public static final String fcmUrl = "https://fcm.googleapis.com/";


    public static String convertCodeToStatus(String code)
    {
        if(code.equals("0"))
            return "Food order has been Placed";
        else if (code.equals("1"))
            return "Your food is on the way";
        else
            return "Food has been delivered";
    }

    public static GeoCoordinates getGeoCodeService(){
        return RetrofitClient.getClient(baseUrl).create(GeoCoordinates.class);
    }

    public static APIService getFCMClient(){
        return FCMRetrofitClient.getClient(fcmUrl).create(APIService.class);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight)
    {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth,newHeight, Config.ARGB_8888);

        float scaleX = newWidth/(float)bitmap.getWidth();
        float scaleY = newHeight/(float)bitmap.getHeight();
        float pivotX=0,pivotY=0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX,scaleY,pivotX,pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap,0,0,new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }
}
