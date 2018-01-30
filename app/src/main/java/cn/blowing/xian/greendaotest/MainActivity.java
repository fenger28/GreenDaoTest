package cn.blowing.xian.greendaotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * @author fenger7
 * @date 2018/1/30
 * @description
 */

public class MainActivity extends AppCompatActivity {

    private final static String dbName = "test_db";
    private Button insert;
    private Button delete;
    private Button update;
    private Button search;
    private User user;
    private User user1;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initListener();
    }

    private void initData() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, dbName, null);
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
        user = new User();
        user.setId(1L);
        user.setAge(15);
        user.setName("fei");
        user1 = new User();
        user1.setId(1L);
        user1.setAge(15);
        user1.setName("xiao");

    }

    private void initListener() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDao.insert(user);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDao.delete(user);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDao.update(user1);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = userDao.queryBuilder().build().list();
                System.out.println("name:"+users.get(0).getName());
                Log.d("name",users.get(0).getName());
            }
        });
    }

    private void initView() {
        insert = findViewById(R.id.insert);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        search = findViewById(R.id.search);
    }
}
