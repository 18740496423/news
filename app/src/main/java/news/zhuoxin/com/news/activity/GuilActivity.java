package news.zhuoxin.com.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import news.zhuoxin.com.news.adapter.GuidAdapter;
import news.zhuoxin.com.news.R;

/**
 * Created by Administrator on 2016/10/27.
 */

public class GuilActivity extends AppCompatActivity {
    int position;
    //ViewPager 图片滑动
    ViewPager mVp;
    // 滑动的点
    ImageView[] mImv;
    //滑动的图片
    ImageView[] mRes;
    //滑动图片的ID
    int[] mResId = {R.mipmap.welcome, R.mipmap.wy, R.mipmap.bd, R.mipmap.small};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        position = this.getIntent().getIntExtra("position", -1);
        //初始化设置隐藏
        mVp = (ViewPager) findViewById(R.id.mvp_guid);
        //数据源 四个点的图片
        mImv = new ImageView[4];
        mImv[0] = (ImageView) findViewById(R.id.img_guid0);
        mImv[1] = (ImageView) findViewById(R.id.img_guid1);
        mImv[2] = (ImageView) findViewById(R.id.img_guid2);
        mImv[3] = (ImageView) findViewById(R.id.img_guid3);
        //给初始时候下标为0设置颜色
        mImv[0].setImageResource(R.mipmap.a4);
        //四个滑动的图片
        mRes = new ImageView[4];
        //for循环创建对象
        for (int i = 0; i < 4; i++) {
            mRes[i] = new ImageView(this);
            mRes[i].setImageResource(mResId[i]);
        }
        //适配器
        GuidAdapter adapter = new GuidAdapter(mRes);
        //启动适配器
        mVp.setAdapter(adapter);
        //刷新适配器
        adapter.notifyDataSetChanged();
        mVp.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position==3) {
                    Intent intent=new Intent(GuilActivity.this,LogoAcyivity.class);
                    startActivity(intent);}
                for (int i = 0; i < mImv.length; i++) {
                    //设置图片为小黑点
                    mImv[i].setImageResource(R.mipmap.adware_style_default);
                }
                mImv[position].setImageResource(R.mipmap.a4);

                }


            //滑动中
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //滑动状态改变的时候
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
