package news.zhuoxin.com.news.entity;

/**
 * Created by Administrator on 2016/11/1.
 */

public class CenterInfo {
    String summary;
    String icon;
    String stamp;
    String title;
    String nid;
    String link;
    String type;

    @Override
    public String toString() {
        return "CenterInfo{" +
                "summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", stamp='" + stamp + '\'' +
                ", title='" + title + '\'' +
                ", nid='" + nid + '\'' +
                ", link='" + link + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public CenterInfo() {

    }

    public CenterInfo(String summary, String icon, String stamp, String title, String nid, String link, String type) {
        this.summary = summary;
        this.icon = icon;
        this.stamp = stamp;
        this.title = title;
        this.nid = nid;
        this.link = link;
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
