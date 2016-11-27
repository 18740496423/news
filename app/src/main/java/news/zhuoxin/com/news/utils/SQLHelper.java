package news.zhuoxin.com.news.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import news.zhuoxin.com.news.db.DBInfo;

/**
 * Created by Administrator on 2016/11/21.
 * 创建收藏新闻的数据库
 */

public class SQLHelper extends SQLiteOpenHelper {
    public SQLHelper(Context context) {
        super(context, DBInfo.DB_NAME,null,DBInfo.DB_VERSION);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //用SQL语句，创建数据库表
        String sql="create table %1$s(%2$s text,%3$s text,%4$s text,%5$s text,%6$s text,%7$s text,%8$s text)";
        String format = String.format(sql, DBInfo.TABLE_NAME, DBInfo.SUMMARY, DBInfo.ICON, DBInfo.STAMP, DBInfo.TITLE, DBInfo.NID, DBInfo.LINK, DBInfo.TYPE);
        //执行SQL语句，创建一张表
       db.execSQL(format);
        Log.e("数据库","-=-="+format);
    }
//更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
