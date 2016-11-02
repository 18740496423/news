package news.zhuoxin.com.news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/27.
 */

public class LogoAcyivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String IS_FIRST = "first";
    ImageView mImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences shar = this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean flag = shar.getBoolean("IS_FIRST", true);
        if (flag) {
            //第一次进入
            int position = 0;
            Intent intent=new Intent(LogoAcyivity.this,GuilActivity.class);
            intent.putExtra("position",position);
            startActivity(intent);
            //获取编辑器对象
            SharedPreferences.Editor edit = shar.edit();
            edit.putBoolean("IS_FIRST",false);
            //提交数据
            edit.commit();
            this.finish();
        }else{
            //不是第一次进入
            setContentView(R.layout.activity_logo);
            mImg= (ImageView) findViewById(R.id.img_logo);
            AlphaAnimation animation=new AlphaAnimation(0,1);
            animation.setDuration(3000);
            mImg.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                  Intent intent=new Intent(LogoAcyivity.this,MainActivity.class);
                    startActivity(intent);
                    LogoAcyivity.this.finish();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }
}
