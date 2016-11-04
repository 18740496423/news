package news.zhuoxin.com.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.activity.MainActivity;

/**
 * Created by Administrator on 2016/10/28.
 */

public class LeftFragment extends Fragment implements View.OnClickListener{
    LinearLayout mLyt_news;
    CenterFragment centerFragment;
    FragmentManager supportFragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.left_fragment_show,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLyt_news= (LinearLayout) view.findViewById(R.id.lyt_left_news);
        mLyt_news.setOnClickListener(this);
         supportFragmentManager = getActivity().getSupportFragmentManager();
         centerFragment=new CenterFragment();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lyt_left_news:
                ((MainActivity)getActivity()).closeSlidingMenu();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center_main,centerFragment);
                fragmentTransaction.commit();
                break;
        }
    }
}
