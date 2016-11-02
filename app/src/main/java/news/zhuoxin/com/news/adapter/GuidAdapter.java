package news.zhuoxin.com.news.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/27.
 */

public class GuidAdapter extends PagerAdapter {
    ImageView[] mRes;
    public GuidAdapter(ImageView[] mRes){
        this.mRes=mRes;
    }
    @Override
    public int getCount() {
        //获取滑动次数
        return mRes==null?0:mRes.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //判断加载的view是否来自obj
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //向container中添加数据，用于在viewpager中显示
        ImageView imageView = mRes[position];
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mRes[position]);
    }
}
