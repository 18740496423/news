package news.zhuoxin.com.news.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.fragment.CenterFragment;
import news.zhuoxin.com.news.fragment.LeftFragment;
import news.zhuoxin.com.news.fragment.RightFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView mTxt_title;
    CenterFragment centerFragment;
    RightFragment rightFragment;
    LeftFragment leftFragment;
    FragmentManager manager;
    SlidingMenu slidingMenu;
    ImageView mLeft;
    ImageView mRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initView();

        //获取V4包的Fragment  管理器
        manager = getSupportFragmentManager();
        Log.e("---", "manager=" + manager);
        centerFragment = new CenterFragment();
        rightFragment = new RightFragment();
        leftFragment = new LeftFragment();
        setSlidingMenu();

        //获取业务对象
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //执行业务
        fragmentTransaction.replace(R.id.center_main, centerFragment);
        Log.e("qqqqq", "wwwwww");
        //提交业务
        fragmentTransaction.commit();


    }

    private void setSlidingMenu() {
        slidingMenu = new SlidingMenu(this);
        //设置滑动模式  全屏滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置渐变 效果 偏移度
        slidingMenu.setFadeDegree(0.5f);
        //设置菜单移动速度
        slidingMenu.setBehindScrollScale(1);
        //设置菜单的宽度
        //滑动完  距离另一侧的屏幕的距离
        slidingMenu.setBehindOffset(150);
        //设置左右都有菜单
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        //第一菜单
        slidingMenu.setMenu(R.layout.left_fragment);
        FragmentTransaction beginTransaction = manager.beginTransaction();
        beginTransaction.replace(R.id.lyt_left, leftFragment);
        beginTransaction.commit();
        //第二菜单
        slidingMenu.setSecondaryMenu(R.layout.right_fragment);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frament_right, rightFragment);
        transaction.commit();

        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);


    }

    private void initView() {
        mLeft = (ImageView) findViewById(R.id.img_main_left);
        mRight = (ImageView) findViewById(R.id.img_main_right);
        mTxt_title= (TextView) findViewById(R.id.txt_main);
        mLeft.setOnClickListener(this);
        mRight.setOnClickListener(this);
    }

    public void closeSlidingMenu() {
        if (slidingMenu.isMenuShowing()) {
            slidingMenu.toggle();
        } else {
            slidingMenu.showMenu();
        }
    }

    public void closeSecondaryMenu() {
        if (slidingMenu.isSecondaryMenuShowing()) {
            slidingMenu.toggle();
        } else {
            slidingMenu.showSecondaryMenu();
        }
    }

    //设置title的方法
    public void setTitle(String title){
        mTxt_title.setText(title);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_main_left:
                closeSlidingMenu();
                break;
            case R.id.img_main_right:
                closeSecondaryMenu();
                break;
        }
    }
}
