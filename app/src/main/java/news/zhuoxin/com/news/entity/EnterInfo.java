package news.zhuoxin.com.news.entity;

/**
 * Created by Administrator on 2016/11/22.
 */

public class EnterInfo {
    String time;
    String address;
    String device;

    public EnterInfo(String time, String address, String device) {
        this.time = time;
        this.address = address;
        this.device = device;
    }

    @Override
    public String toString() {
        return "EnterInfo{" +
                "time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", device='" + device + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
