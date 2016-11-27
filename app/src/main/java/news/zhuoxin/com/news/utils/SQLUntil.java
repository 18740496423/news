package news.zhuoxin.com.news.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import news.zhuoxin.com.news.db.DBInfo;
import news.zhuoxin.com.news.entity.CenterInfo;

/**
 * Created by Administrator on 2016/11/21.
 * 创建数据库的工具类
 */

public class SQLUntil {
    Context mContext;
    SQLHelper mSqlHelper;
    SQLiteDatabase database;

    public SQLUntil(Context context) {
        this.mContext = context;
        mSqlHelper = new SQLHelper(context);
    }


    public ArrayList<CenterInfo>query(){
        //定义一个集合，用于储存查询到的数据
        ArrayList<CenterInfo> list=new ArrayList<>();
        //打开一个可写的数据库
        SQLiteDatabase readableDatabase = mSqlHelper.getReadableDatabase();
        //得到游标
        Cursor cursor = readableDatabase.query(DBInfo.TABLE_NAME, null, null, null, null, null, null);
        // 通过游标获取数据 ColumnIndex代表数据库的列
        while (cursor.moveToNext()){
            String summary=cursor.getString(cursor.getColumnIndex(DBInfo.SUMMARY));
            String icon=cursor.getString(cursor.getColumnIndex(DBInfo.ICON));
            String stamp=cursor.getString(cursor.getColumnIndex(DBInfo.STAMP));
            String title=cursor.getString(cursor.getColumnIndex(DBInfo.TITLE));
            String nid=cursor.getString(cursor.getColumnIndex(DBInfo.NID));
            String link=cursor.getString(cursor.getColumnIndex(DBInfo.LINK));
            String type=cursor.getString(cursor.getColumnIndex(DBInfo.TYPE));
            list.add(new CenterInfo(summary,icon,stamp,title,nid,link,type));
        }
        return null;
    }
    //插入内容
    public void insert(String summaary, String icon, String stamp, String title, String nid, String link, String type) {
        //先判断数据库中的这条新闻是否存在
        //查询数据库，得到集合 遍历集合
        for (CenterInfo centerInfo:query()){
            //判断数据库中的数据是否存在，如果存在，结束操作
            while (centerInfo.getNid().equals(nid)){
                Toast.makeText(mContext,"该新闻已收藏",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //打开一个可写的数据库
        database=mSqlHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DBInfo.SUMMARY,summaary);
        values.put(DBInfo.ICON,icon);
        values.put(DBInfo.STAMP,stamp);
        values.put(DBInfo.TITLE,title);
        values.put(DBInfo.NID,nid);
        values.put(DBInfo.LINK,link);
        values.put(DBInfo.TYPE,type);
        database.insert(DBInfo.TABLE_NAME,null,values);
    }
    //删除内容
    public void delete(String nid){
        SQLiteDatabase database=mSqlHelper.getWritableDatabase();
        database.delete(DBInfo.TABLE_NAME,DBInfo.NID +" = ?",new String[]{nid});
    }

}