package news.zhuoxin.com.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import news.zhuoxin.com.news.R;

/**
 * Created by Administrator on 2016/11/3.
 */

public class EnterFragment extends Fragment implements View.OnClickListener{
    Button mBtn;
    Button mPassword;
    FragmentManager manager;
    RegisterFragment registerFragment;
    ForgetPasswordFragment passwordFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.enter,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtn= (Button) view.findViewById(R.id.btn_fragment_enter);
        mPassword= (Button) view.findViewById(R.id.btn_password);
        mBtn.setOnClickListener(this);
        mPassword.setOnClickListener(this);
         manager = getActivity().getSupportFragmentManager();
         registerFragment=new RegisterFragment();
         passwordFragment=new ForgetPasswordFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fragment_enter:
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.center_main,registerFragment);
                fragmentTransaction.commit();
                break;
            case R.id.btn_password:
                FragmentTransaction beginTransaction = manager.beginTransaction();
                beginTransaction.replace(R.id.center_main,passwordFragment);
                beginTransaction.commit();
                break;
        }
    }
}
