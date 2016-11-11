package news.zhuoxin.com.news.fragment;

import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.activity.MainActivity;
import news.zhuoxin.com.news.entity.HttpInfo;
import news.zhuoxin.com.news.inter.OnLoadResponseListener;
import news.zhuoxin.com.news.utils.HttpUtil;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener, OnLoadResponseListener {
    EditText mEdit_email;
    EditText mEdit_name;
    EditText mEdit_password;
    Button mBtn_register;
    RequestQueue requestQueue;
    String name;
    String email;
    String password;
    public static final String PASE_NAME = "file";
    SharedPreferences sharedPreferences;
    EnterFragment enterFragment;
    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEdit_email = (EditText) view.findViewById(R.id.edit_eamil);
        mEdit_name = (EditText) view.findViewById(R.id.edit_name);
        mEdit_password = (EditText) view.findViewById(R.id.edit_password);
        mBtn_register = (Button) view.findViewById(R.id.btn_register);
        mBtn_register.setOnClickListener(this);
        //获取请求列队
        requestQueue = Volley.newRequestQueue(getActivity());
        //创建Fragment的对象
        enterFragment = new EnterFragment();
        //获取Fragment的管理器
        manager = getActivity().getSupportFragmentManager();
//创建Fragment的对象
        ((MainActivity) getActivity()).setTitle("用户注册");
    }

    @Override
    public void onClick(View v) {
        Log.e("....", "//////");
        getEdit();
        new HttpUtil().volleyGET(HttpInfo.BASE_URL + HttpInfo.USER_REGISTER + "ver=1&uid=" + name + "&email=" + email + "&pwd=" + password, this,requestQueue);
        //new HttpUtil().volleyPOST(HttpInfo.BASE_URL + HttpInfo.USER_REGISTER, name, password, this, requestQueue);
    }

    private void getEdit() {
        email = mEdit_email.getText().toString();
        //        String regex = "  /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$/";
        //        if (email.matches(regex)) {
        name = mEdit_name.getText().toString();
        password = mEdit_password.getText().toString();
//        } else {
//            Toast.makeText(getActivity(), "您输入的邮箱格式不正确，请重新输入", Toast.LENGTH_SHORT).show();
//
//        }

    }


    @Override
    public void getResponse(String response) {
        Log.e("===", response);
        //JSON解析，对象型
        try {
            JSONObject jsonObject = new JSONObject(response);
            String message = jsonObject.getString("message");
            int status = jsonObject.getInt("status");
            JSONObject data = jsonObject.getJSONObject("data");
            int result = data.getInt("result");
            String token = data.getString("token");
            String explain = data.getString("explain");
            //保存数据，使用sharpreference
            sharedPreferences = getActivity().getSharedPreferences(PASE_NAME, Context.MODE_PRIVATE);
            //获取编辑器的对象
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //向里面添加数据
            editor.putInt("result", result);
            editor.putString("token", token);
            editor.putString("explain", explain);
            //提交数据
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int result = sharedPreferences.getInt("result", 4);
        String explain = sharedPreferences.getString("explain", "未知错误");
        switch (result) {
            case 0:
                //获取业务对象
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                //执行业务
                fragmentTransaction.replace(R.id.btn_register, enterFragment);
                //提交业务
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), explain, Toast.LENGTH_SHORT).show();
                break;
            case -1:
                Toast.makeText(getActivity(), explain, Toast.LENGTH_SHORT).show();
                break;
            case -2:
                Toast.makeText(getActivity(), explain, Toast.LENGTH_SHORT).show();
                break;
            case -3:
                Toast.makeText(getActivity(), explain, Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getActivity(), explain, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
