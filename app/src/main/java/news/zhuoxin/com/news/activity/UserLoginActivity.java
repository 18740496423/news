package news.zhuoxin.com.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import news.zhuoxin.com.news.R;

/**
 * Created by Administrator on 2016/11/10.
 */

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mImg_portrait;
    TextView mTxt_account;
    String mName;
    TextView mTxt_carema;
    TextView mTxt_picket;

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
        Intent intent=this.getIntent();
         mName = intent.getStringExtra("name");
        mTxt_account.setText(mName);
        //设置PopupWidow
        setPopu();
    }

    private void setPopu() {
        PopupWindow popuWindow=new PopupWindow();
        //设置View
        View view=this.getLayoutInflater().inflate(R.layout.user_portrait,null);
        mTxt_carema= (TextView) view.findViewById(R.id.txt_carema);
        mTxt_picket= (TextView) view.findViewById(R.id.txt_picket);
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
            }
        });
    }

    private void initView() {
        mImg_portrait = (ImageView) findViewById(R.id.img_user_portrait);
        mTxt_account = (TextView) findViewById(R.id.txt_use_account);
        mImg_portrait.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
