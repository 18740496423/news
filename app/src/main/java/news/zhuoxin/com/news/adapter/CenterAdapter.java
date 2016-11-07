package news.zhuoxin.com.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import news.zhuoxin.com.news.R;
import news.zhuoxin.com.news.entity.CenterInfo;

/**
 * Created by Administrator on 2016/11/1.
 */

public class CenterAdapter extends MyAdapter<CenterInfo> {
    int[] groud = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f, R.mipmap.g,
            R.mipmap.h, R.mipmap.i, R.mipmap.j, R.mipmap.k, R.mipmap.l, R.mipmap.m, R.mipmap.n, R.mipmap.o,
            R.mipmap.p, R.mipmap.q, R.mipmap.r, R.mipmap.s, R.mipmap.t};
    Context context;

    public CenterAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View setView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.center_adapter, parent, false);
            holder.mImg_Icon = (ImageView) convertView.findViewById(R.id.img_adapter_icon);
            holder.mTxt_Title = (TextView) convertView.findViewById(R.id.txt_adapter_title);
            holder.mTxt_Summary = (TextView) convertView.findViewById(R.id.txt_adapter_summary);
            holder.mTxt_Stamp = (TextView) convertView.findViewById(R.id.txt_adapter_stamp);
            holder.mLyt_groud = (LinearLayout) convertView.findViewById(R.id.lyt_center_adapter_backgroud);
           // holder.mCard= (CardView) convertView.findViewById(R.id.card_backgroud);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
       // holder.mCard.setCardBackgroundColor(groud[position%groud.length]);
        //holder.mCard.setBackgroundResource(groud[position%groud.length]);
       holder.mLyt_groud.setBackgroundResource(groud[position%groud.length]);
        Picasso.with(convertView.getContext()).load(mList.get(position).getIcon()).into(holder.mImg_Icon);
        holder.mTxt_Title.setText(mList.get(position).getTitle());
        holder.mTxt_Summary.setText(mList.get(position).getSummary());
        holder.mTxt_Stamp.setText(mList.get(position).getStamp());
        return convertView;
    }

    static class Holder {
        ImageView mImg_Icon;
        TextView mTxt_Title;
        TextView mTxt_Summary;
        TextView mTxt_Stamp;
        LinearLayout mLyt_groud;
       // CardView mCard;
    }
}
