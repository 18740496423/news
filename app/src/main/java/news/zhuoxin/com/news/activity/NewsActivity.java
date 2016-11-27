package news.zhuoxin.com.news.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.entity.CenterInfo;
import news.zhuoxin.com.news.entity.TitleInfo;
import news.zhuoxin.com.news.inter.OnLoadNewsListListener;
import news.zhuoxin.com.news.utils.NewsAsynTask;

/**
 * Created by Administrator on 2016/11/3.
 */

public class NewsActivity extends AppCompatActivity implements OnLoadNewsListListener, View.OnClickListener {
    public static final String PATH = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    TextView mTxt;
    WebView mWeb;
    ImageView mImg_back;
    ImageView mImg_popu;
    TextView mTxt_collect;
    ArrayList<CenterInfo> mData;
    int mPosition;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        mPosition = intent.getIntExtra("position", -1);
        setContentView(R.layout.activity_news);


    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        //加载组建
        initView();
        NewsAsynTask task = new NewsAsynTask();
        task.setOnLoadNewsListListener(this);
        //启动异步任务
        task.execute(PATH);
        //设置PopuWindow
        setPopuWindow();

    }

    private void setPopuWindow() {
         popupWindow=new PopupWindow();
        //设置view
        View view=getLayoutInflater().inflate(R.layout.activity_popu_web,null);
        mTxt_collect= (TextView) view.findViewById(R.id.popu_collect);
        popupWindow.setContentView(view);
        //设置长和宽
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置焦点
        popupWindow.setFocusable(true);
        //设置点击取消
        popupWindow.setOutsideTouchable(true);
        //设置背景图片  不然无法点击
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    private void initView() {
        mImg_popu= (ImageView) findViewById(R.id.img_popu);
        mImg_back= (ImageView) findViewById(R.id.img_news_back);
        mWeb = (WebView) findViewById(R.id.web_news_activity);
        mImg_back.setOnClickListener(this);
        mImg_popu.setOnClickListener(this);
    }

    @Override
    public void OnLoadNewsList(String string) {
        Gson gson = new Gson();
        TitleInfo titleInfo = gson.fromJson(string, new TypeToken<TitleInfo>() {
        }.getType());
        mData = titleInfo.getData();
        mWeb.loadUrl(mData.get(mPosition - 1).getLink());
        //设置手机客户端显示样式
        WebSettings settings = mWeb.getSettings();
        //自动识别是手机端还是网页端
        settings.setJavaScriptEnabled(true);
        //设置自动适应屏幕大小       尽可能的在一行中显示
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//设置推荐使用的窗口
        settings.setUseWideViewPort(true);
        //自适应大小，并且可以任意放大缩小
        settings.setLoadWithOverviewMode(true);
        //设置放大缩小按钮
        settings.setBuiltInZoomControls(true);
        //设置自己的客户端
        //mWeb.setWebChromeClient(new );
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_news_back:
                NewsActivity.this.finish();
                break;
            case R.id.img_popu:
                popupWindow.showAsDropDown(mImg_popu);
                break;
        }
    }
}
