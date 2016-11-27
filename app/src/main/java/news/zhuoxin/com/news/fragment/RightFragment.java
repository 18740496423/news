package news.zhuoxin.com.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.activity.MainActivity;
import news.zhuoxin.com.news.activity.UserLoginActivity;

/**
 * Created by Administrator on 2016/10/28.
 */

public class RightFragment extends Fragment implements View.OnClickListener {
    LinearLayout mLyt;
    TextView mTxt_right;
    ImageView mImg_right;
    EnterFragment mEnterFragment;
    int result;
    String name;
    public static final String PASE_NAME = "file";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.right_fragment_show, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //加载组件
        mLyt = (LinearLayout) view.findViewById(R.id.lyt_right);
        mTxt_right = (TextView) view.findViewById(R.id.txt_fragment_right);
        mImg_right = (ImageView) view.findViewById(R.id.img_fragment_right);
        mLyt.setOnClickListener(this);

        //关闭slidingmenu
        SlidingMenu slidingMenu = new SlidingMenu(getActivity());
        mEnterFragment = new EnterFragment();
        //使用SharedPreferences获取保存的数
        SharedPreferences preferences = getActivity().getSharedPreferences(PASE_NAME, Context.MODE_PRIVATE);
         result = preferences.getInt("result", -1);
         name = preferences.getString("name", null);
        if (result == 0 && name != null) {
            mImg_right.setImageResource(R.mipmap.xuan);
            mTxt_right.setText(name);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lyt_right:
                if (result==0&&name!=null) {
                    ((MainActivity)getActivity()).closeSecondaryMenu();
                    Intent intent=new Intent(getActivity(), UserLoginActivity.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }else {
                    ((MainActivity) getActivity()).closeSecondaryMenu();
                    Log.e("----", "");
                    //获取FragmentManager 管理器
                    FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                    //获取业务
                    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                    //执行业务
                    fragmentTransaction.replace(R.id.center_main, mEnterFragment);
                    fragmentTransaction.commit();
                }
                break;
        }
    }
}
