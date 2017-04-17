package wangyikai.bwie.com.xutils30demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.google.gson.Gson;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private DbManager mDb;
    private MySqlite mMs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 注入绑定事件
        x.view().inject(this);


        mLv = (ListView) findViewById(R.id.lv);
        //xutils3.0获取网络信息
        wljx();
        //  获取数据库实例
        DbManager.DaoConfig daoConfig = MyAppLication.getDaoConfig();
        mDb = x.getDb(daoConfig);
        //对数据库的增删改查
        //增
        zeng();
        //删
        shan();
        //改
        gai();
        //查
        cha();
    }

    private void cha() {
        try {
            //1
            //通过主键的值来进行查找表里面的数据
            mDb.findById(MySqlite.class, 1);
            //2
            //返回当前表里面的第一条数据
            mDb.findFirst(MySqlite.class);
            //3
            //返回当前表里面的所有数据
            List<MySqlite> findAll = mDb.findAll(MySqlite.class);
            //4
            //model相当于游标
            DbModel model = mDb.findDbModelFirst(new SqlInfo("select * from user where id>1"));
            model.getString("age");
            //5
            //主要是用来进行一些特定条件的查找
            List<MySqlite> findAll2 = mDb.selector(MySqlite.class).expr("id <9").findAll();

        } catch (DbException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void gai() {
        try {
            List<MySqlite> findAll = mDb.findAll(MySqlite.class);
            //改变集合中的所有数据
            for (MySqlite sqlite : findAll) {
                sqlite.setTitle("王艺凯");
                sqlite.setImage(2222222);
            }
            //最后确定改变的值
            mDb.update(findAll, "image");//可以使对象、集合
        } catch (DbException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void shan() {
        try {
            //1
            //该方法是删除表中的全部数据
            mDb.delete(MySqlite.class);
            //2
            //该方法主要是根据表的主键(id)进行单条记录的删除
            mDb.deleteById(MySqlite.class, 5);
            //3
            //根据where语句的条件进行删除操作
            mDb.delete(MySqlite.class, WhereBuilder.b("id", ">", "3"));
            //4
            //根据实体bean进行对表里面的一条或多条数据进行删除
            List<MySqlite> findAll = mDb.selector(MySqlite.class).expr("id > 5").findAll();
            mDb.delete(findAll);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void zeng() {
        try {
            List<MySqlite> list = new ArrayList<MySqlite>();
            for (int i = 0; i < 10; i++) {
                mMs = new MySqlite();
                mMs.setTitle("大凯王" + i);
                mMs.setImage(R.mipmap.ic_launcher + i + i + i + i);

                list.add(mMs);
            }
            mDb.save(list);//保存实体类或实体类的List到数据库
        } catch (DbException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void wljx() {
        RequestParams params = new RequestParams(MyUrls.URLS);
        params.addQueryStringParameter("sa_event", "eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3ODI4ODU4NjAwLCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjIuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6InNhbXN1bmciLCJGcm9tSG9tZXBhZ2VVcGRhdGVEYXRlIjowLCIkc2NyZWVuX2hlaWdodCI6NTc2LCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6NDYsIiRzY3JlZW5fd2lkdGgiOjEwMjQsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IkhvbWVQYWdlIiwiJGNhcnJpZXIiOiJDTUNDIiwiJG1vZGVsIjoiR1QtUDUyMTAiLCIkYXBwX3ZlcnNpb24iOiIzLjguMSJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OTA1MTA0Mjc2Mzc1NTEwOSIsIm9yaWdpbmFsX2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5IiwiZXZlbnQiOiJSZWFkSG9tZVBhZ2UifQ%3D%3D");
        x.http().get(params, new Callback.CommonCallback<String>() {

            //成功
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                MyBean bean = gson.fromJson(result, MyBean.class);
                mLv.setAdapter(new MyAdapter(bean, MainActivity.this));

            }
            //失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
            //取消
            @Override
            public void onCancelled(CancelledException cex) {

            }
            //结束
            @Override
            public void onFinished() {

            }
        });

    }


}


