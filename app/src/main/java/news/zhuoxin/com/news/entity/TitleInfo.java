package news.zhuoxin.com.news.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */

public class TitleInfo {
    String message;
    String status;
    ArrayList<CenterInfo> data;

    @Override
    public String toString() {
        return "TitleInfo{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public TitleInfo() {

    }

    public TitleInfo(String message, String status, ArrayList<CenterInfo> data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<CenterInfo> getData() {
        return data;
    }

    public void setData(ArrayList<CenterInfo> data) {
        this.data = data;
    }
}
