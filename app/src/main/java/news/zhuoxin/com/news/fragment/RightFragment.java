package news.zhuoxin.com.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.activity.MainActivity;

/**
 * Created by Administrator on 2016/10/28.
 */

public class RightFragment extends Fragment implements View.OnClickListener {

    TextView mTxt_enter;
    EnterFragment mEnterFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.right_fragment_show, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       mTxt_enter= (TextView) view.findViewById(R.id.txt_enter);
        mTxt_enter.setOnClickListener(this);
        //关闭slidingmenu
        SlidingMenu slidingMenu = new SlidingMenu(getActivity());
        mEnterFragment = new EnterFragment();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_enter:
                ((MainActivity)getActivity()).closeSecondaryMenu();
                Log.e("----", "");
                //获取FragmentManager 管理器
                FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                //获取业务
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                //执行业务
                fragmentTransaction.replace(R.id.center_main, mEnterFragment);
                fragmentTransaction.commit();
                break;
        }
    }
}
