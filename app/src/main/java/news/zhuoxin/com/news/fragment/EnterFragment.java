package news.zhuoxin.com.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.activity.MainActivity;
import news.zhuoxin.com.news.activity.UserLoginActivity;
import news.zhuoxin.com.news.entity.HttpInfo;
import news.zhuoxin.com.news.inter.OnLoadResponseListener;
import news.zhuoxin.com.news.utils.HttpUtil;

import static android.R.attr.name;

/**
 * Created by Administrator on 2016/11/3.
 */

public class EnterFragment extends Fragment implements View.OnClickListener,OnLoadResponseListener{
    EditText mEdit_name;
    EditText mEdit_password;
    Button mBtn;
    Button mBtn_enter;
    Button mPassword;
    FragmentManager manager;
    RegisterFragment registerFragment;
    ForgetPasswordFragment passwordFragment;
    RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.enter,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEdit_name= (EditText) view.findViewById(R.id.edit_use_name);
        mEdit_password= (EditText) view.findViewById(R.id.edit_use_password);
        mBtn_enter= (Button) view.findViewById(R.id.btn_enter);
        mBtn= (Button) view.findViewById(R.id.btn_fragment_enter);
        mPassword= (Button) view.findViewById(R.id.btn_password);
        mBtn.setOnClickListener(this);
        mBtn_enter.setOnClickListener(this);
        mPassword.setOnClickListener(this);
        registerFragment=new RegisterFragment();
        passwordFragment=new ForgetPasswordFragment();
        //获取Fragment的管理器
         manager = getActivity().getSupportFragmentManager();
        ((MainActivity)getActivity()).setTitle("用户登录");
        //获取请求对列
         requestQueue= Volley.newRequestQueue(getActivity());
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
            case R.id.btn_enter:
                String name = mEdit_name.getText().toString();
                String password = mEdit_password.getText().toString();
                 new HttpUtil().volleyPOST(HttpInfo.BASE_URL + HttpInfo.USER_LOGIN, name, password,this, requestQueue);
                break;
        }
    }

    @Override
    public void getResponse(String response) {
        Log.e("登录信息","*_*"+response);
        //JSON解析
        try {
            JSONObject jsonObject=new JSONObject(response);
            String message = jsonObject.getString("message");
            int status = jsonObject.getInt("status");
            JSONObject data = jsonObject.getJSONObject("data");
            int result = data.getInt("result");
            String token = data.getString("token");
            String explain = data.getString("explain");
            if (result==0) {
                Intent intent=new Intent(getActivity(), UserLoginActivity.class);
                intent.putExtra("name",name);
                Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }else {
                Toast.makeText(getActivity(),"登录失败",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
