package cn.openiotlab.wxithelper.Beans;

/**
 * Created by Leon on 2015/5/26.
 */
public class GongGaoDataBean {
    private String stu_id;
    private int gong_gao_id;
    private String gong_gao_title;
    private String gong_gao_context;
    private String gong_gao_describe;
    private String creat_time;


    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public int getGong_gao_id() {
        return gong_gao_id;
    }

    public void setGong_gao_id(int gong_gao_id) {
        this.gong_gao_id = gong_gao_id;
    }

    public String getGong_gao_title() {
        return gong_gao_title;
    }

    public void setGong_gao_title(String gong_gao_title) {
        this.gong_gao_title = gong_gao_title;
    }

    public String getGong_gao_context() {
        return gong_gao_context;
    }

    public void setGong_gao_context(String gong_gao_context) {
        this.gong_gao_context = gong_gao_context;
    }

    public String getGong_gao_describe() {
        return gong_gao_describe;
    }

    public void setGong_gao_describe(String gong_gao_describe) {
        this.gong_gao_describe = gong_gao_describe;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    private String comments_count;

}