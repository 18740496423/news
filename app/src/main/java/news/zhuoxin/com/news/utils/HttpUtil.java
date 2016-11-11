package news.zhuoxin.com.news.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import news.zhuoxin.com.news.inter.OnLoadResponseListener;

/**
 * Created by Administrator on 2016/11/9.
 */

public class HttpUtil {
    public void volleyGET(String url, final OnLoadResponseListener listener, RequestQueue requestQueue) {

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            //请求成功的方法
            @Override
            public void onResponse(String response) {
                listener.getResponse(response);
            }
        }, new Response.ErrorListener() {
            //请求失败的方法
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("----", "==请求失败==" + error.getMessage());
            }
        });
        requestQueue.add(request);
    }

    public void volleyPOST(String url, final String name, final String password, final OnLoadResponseListener listener, RequestQueue requestQueue) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //请求成功的方法
            @Override
            public void onResponse(String response) {
                listener.getResponse(response);
            }
        }, new Response.ErrorListener() {
            //请求失败的方法
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("----", "=/=请求失败=/=" + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("ver", "1");
                map.put("uid", name);
                map.put("pwd", password);
                map.put("device", "0");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
