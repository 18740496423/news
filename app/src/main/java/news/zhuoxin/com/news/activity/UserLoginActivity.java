package news.zhuoxin.com.news.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.adapter.EnterAdapter;
import news.zhuoxin.com.news.entity.EnterInfo;
import news.zhuoxin.com.news.entity.HttpInfo;
import news.zhuoxin.com.news.inter.OnLoadResponseListener;
import news.zhuoxin.com.news.utils.HttpUtil;

/**
 * Created by Administrator on 2016/11/10.
 */

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener, OnLoadResponseListener {
    ImageView mImg_portrait;
    TextView mTxt_account;
    String mName;
    TextView mTxt_carema;
    TextView mTxt_picket;
    ListView mList;
    Button mBtn_back_enter;
    String path;
    File file;
    ImageView mImg_back;
    PopupWindow popuWindow;
    SharedPreferences sharedPreferences;
    public static final String PASE_NAME = "file";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        //加载组件
        initView();
        //获取Intent对象
        Intent intent = this.getIntent();
        mName = intent.getStringExtra("name");
        Log.e("====", "-=-=-" + mName);
        mTxt_account.setText(mName);
        //设置PopupWidow
        setPopu();
        //获取请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //保存数据，使用sharpreference
         sharedPreferences = this.getSharedPreferences(PASE_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        //获取登录日志
        new HttpUtil().volleyGET(HttpInfo.BASE_URL + HttpInfo.USER_HOME + "ver=1&imei=0&token=" + token, this, requestQueue);
        Log.e("ssssssss", "+=+=+=" + HttpInfo.BASE_URL + HttpInfo.USER_HOME + "ver=1&imei=0&token =" + token);
    }

    private void setPopu() {
        popuWindow = new PopupWindow();
        //设置View
        View view = this.getLayoutInflater().inflate(R.layout.user_portrait, null);
        mTxt_carema = (TextView) view.findViewById(R.id.txt_carema);
        mTxt_picket = (TextView) view.findViewById(R.id.txt_picket);
        mTxt_carema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统相机
                //从图库中获取照片
                /*分析：1：系统相机是系统的东西，调用的时候需要权限:android.permission.CAMERA
                * */
                //2：使用意图来调用相机:MediaStore媒体中心，ACTION_IMAGE_CAPTURE：照相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //3：照相的时候，照的照片需要保存下来，所以需要提供一个路径，用于保存照片
                //判断内存卡是否挂载
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    //获取地址
                    path = Environment.getExternalStorageDirectory().getPath();
                }
                //获取文件
                file = new File(path + File.separator + System.currentTimeMillis());
                //设置保存路径 MediaStore.EXTRA_OUTPUT，指的是媒体向出输出
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                //启动回调 requestCode:请求码，>=0
                startActivityForResult(intent, 1);
            }
        });
        mTxt_picket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从图库中获取数据:Intent.ACTION_PICK：进入图库获取照片的意图
                Intent intent = new Intent(Intent.ACTION_PICK);
                //设置类型
                intent.setType("image/*");
                intent.putExtra("return-data", 2);
                startActivityForResult(intent, 2);
            }
        });
        popuWindow.setContentView(view);
        //设置焦点
        popuWindow.setFocusable(true);
        //设置取消焦点
        popuWindow.setOutsideTouchable(true);
        //最重要的一步  需要加一个背景图片，没有背景图片，点击取消不了
        popuWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置宽高
        popuWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popuWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    private void initView() {
        mBtn_back_enter= (Button) findViewById(R.id.btn_back_enter);
        mList = (ListView) findViewById(R.id.lst_user_login);
        mImg_portrait = (ImageView) findViewById(R.id.img_user_portrait);
        mTxt_account = (TextView) findViewById(R.id.txt_use_account);
        mImg_back = (ImageView) findViewById(R.id.img_user_back);
        mBtn_back_enter.setOnClickListener(this);
        mImg_back.setOnClickListener(this);
        mImg_portrait.setOnClickListener(this);
    }

    // 获取数据回传后得到的数据
//    requestCode请求码
//    resultCode：结果码
//    data 数据回传后封装的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    //Parcelable序列化
//                    Bitmap bitmap = data.getParcelableExtra("data");
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                    mImg_portrait.setImageBitmap(bitmap);

                    break;
                case 2:
                    //通过内容提供者获取数据
                    ContentResolver contentResolver = getContentResolver();
                    Log.e("地址值", "" + data.getData());
                    //根据地址值，拿数据
                    Uri uri = data.getData();
                    String[] array = {MediaStore.Images.Media.DATA};
                    //获取游标
                    Cursor cursor = contentResolver.query(uri, array, null, null, null);
                    //将游标放到第一位
                    cursor.moveToFirst();
                    //获取路径
                    String path = cursor.getString(cursor.getColumnIndex(array[0]));
                    //关游标
                    cursor.close();
                    Bitmap bitmaps = BitmapFactory.decodeFile(path);
                    mImg_portrait.setImageBitmap(bitmaps);
                    break;
            }
        }
    }

    //显示PopupWindow
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_user_portrait:
                popuWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_userlogin, null), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.img_user_back:
                Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.btn_back_enter:
                //获取编辑器对象
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.putInt("result",-1);
                //提交数据
                edit.commit();
                Toast.makeText(this,"退出成功",Toast.LENGTH_SHORT).show();
                Intent back=new Intent(UserLoginActivity.this,MainActivity.class);
                startActivity(back);
                this.finish();
                break;
        }

    }

    @Override
    public void getResponse(String response) {
        Log.e("登陆日志", "登陆日志==" + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray loginlog = data.getJSONArray("loginlog");
            ArrayList<EnterInfo> list = new ArrayList<>();
            for (int i = 0; i < loginlog.length(); i++) {
                JSONObject object = loginlog.getJSONObject(i);
                String time = object.getString("time");
                String substring = time.substring(0, 10);
                String address = object.getString("address");
                String device = object.getString("device");
                list.add(new EnterInfo(substring, address, device));
                //适配器
                EnterAdapter adapter = new EnterAdapter(this);
                adapter.setData(list);
                mList.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
