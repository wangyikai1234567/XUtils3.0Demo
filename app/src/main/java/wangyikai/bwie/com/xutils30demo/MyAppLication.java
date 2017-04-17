package wangyikai.bwie.com.xutils30demo;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;


/**
 * date: 2017/4/16.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class MyAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    //这个配置最好写在工具类中，方便调用：
    //创建数据库
    private static DbManager.DaoConfig daoConfig;

    public static DbManager.DaoConfig getDaoConfig() {
        if (daoConfig == null) {
            daoConfig = new DbManager.DaoConfig()
                    .setAllowTransaction(true)//设置允许开启事务
                    .setDbName("mysqlite.db")//创建数据库的名称
                    // 不设置dbDir时, 默认存储在app的私有目录.
                    .setDbVersion(1);//数据库版本号
            //不写也会创建数据库
//                    .setDbOpenListener(new DbManager.DbOpenListener() {
//                        @Override
//                        public void onDbOpened(DbManager db) {
//                            // 开启WAL, 对写入加速提升巨大
//                            db.getDatabase().enableWriteAheadLogging();
//                        }
//                    })
//                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
//                        @Override
//                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
//                        }
//                    });
        }
        return daoConfig;
    }
}
