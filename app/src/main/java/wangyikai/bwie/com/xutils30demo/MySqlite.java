package wangyikai.bwie.com.xutils30demo;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * date: 2017/4/16.
 * author: 王艺凯 (lenovo )
 * function:创建表
 */
//表名
@Table(name = "user")
public class MySqlite {
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "title")
    private String title; // 姓名
    @Column(name = "image")
    private int image; // 图片路径


    public MySqlite(int id, String title, int image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public MySqlite() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
