package news.zhuoxin.com.news.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import news.zhuoxin.com.news.inter.OnLoadNewsListListener;

/**
 * Created by Administrator on 2016/11/1.
 */

public class NewsAsynTask extends AsyncTask<String,Void,String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    HttpURLConnection urlConnection;
    InputStream inputStream;
    @Override
    protected String doInBackground(String... params) {
        Log.e("11111","----");
        String param = params[0];
        try {
            //获取URL对象
            URL url=new URL(param);
            Log.e("112222","----"+url);
            //打开一个链接
           urlConnection = (HttpURLConnection) url.openConnection();
            //获取响应码
            int responseCode = urlConnection.getResponseCode();
            Log.e("-------","responseCode=="+responseCode);
            if (responseCode==200) {
                //获取一个输入流 读取数据
                inputStream = urlConnection.getInputStream();

                byte [] bytes=new byte[2*1024];
                int len=0;
                //字符缓冲区  用于读取流中的数据
                StringBuffer buffer=new StringBuffer();
                while ((len=inputStream.read(bytes))!=-1){
                    buffer.append(new String(bytes,0,len));
                }
                return buffer.toString();
               // Log.e("-----",buffer.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //断开链接
            if (urlConnection!=null) {
                urlConnection.disconnect();
            }
            //关流
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String string) {
        if (string!=null&onLoadNewsListListener!=null) {
            onLoadNewsListListener.OnLoadNewsList(string);
            Log.e("----","==="+string);
        }
        super.onPostExecute(string);
    }
    OnLoadNewsListListener onLoadNewsListListener;
    public void setOnLoadNewsListListener(OnLoadNewsListListener onLoadNewsListListener){
        this.onLoadNewsListListener=onLoadNewsListListener;
    }
}
