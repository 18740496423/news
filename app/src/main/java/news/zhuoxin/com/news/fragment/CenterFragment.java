package news.zhuoxin.com.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.maxwin.view.XListView;
import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.activity.NewsActivity;
import news.zhuoxin.com.news.adapter.CenterAdapter;
import news.zhuoxin.com.news.entity.CenterInfo;
import news.zhuoxin.com.news.entity.TitleInfo;
import news.zhuoxin.com.news.inter.OnLoadNewsListListener;
import news.zhuoxin.com.news.utils.NewsAsynTask;

/**
 * Created by Administrator on 2016/10/28.
 */

public class CenterFragment extends Fragment implements XListView.IXListViewListener, OnLoadNewsListListener, AdapterView.OnItemClickListener {
    XListView mXlst_center;
    Handler mHandler;
    CenterAdapter adapter;

    public static final String LIST_PATH = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.center_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mXlst_center = (XListView) view.findViewById(R.id.xlst_center_fragment);
        mXlst_center.setOnItemClickListener(this);
        //上拉加载
        mXlst_center.setPullLoadEnable(true);
        //下拉刷新
        mXlst_center.setPullRefreshEnable(true);
        //下拉刷新 上拉加载 需要设置监听事件 否则 停不下来或无法加载更多
        mXlst_center.setXListViewListener(this);

        mHandler = new Handler();
        adapter = new CenterAdapter(getActivity());

        NewsAsynTask task = new NewsAsynTask();
        task.setOnLoadNewsListListener(this);
        Log.e("----", "------=-=-=-=");
        //启动异步任务
        task.execute(LIST_PATH);


    }

    @Override
    public void OnLoadNewsList(String string) {
        Log.e("-----", "---" + string);
        //使用GSON解析，获取数据
        Gson gson = new Gson();
        TitleInfo titleInfo = gson.fromJson(string, new TypeToken<TitleInfo>() {
        }.getType());
        ArrayList<CenterInfo> data = titleInfo.getData();
        Log.e("xxxx", "ddddd" + data);

        adapter.setData(data);
        mXlst_center.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //必须设置两个方法
        Log.e("---","--------------");


    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 2000);
    }

    public void stop() {
        //停止刷新和加载
        mXlst_center.stopLoadMore();
        mXlst_center.stopRefresh();
        //设置时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mXlst_center.setRefreshTime(format.format(new Date(System.currentTimeMillis())));

    }

    //SlidingMenu slidingMenu=new SlidingMenu();


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), NewsActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
