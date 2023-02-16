package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDatabase database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "Roomdatabase")
                .fallbackToDestructiveMigration() //스키마(database) 버전 변경 가능
                .allowMainThreadQueries() //Main Thread에서 DB에 IO(입출력)를 가능하게 함
                .build();

        mUserDao = database.userDao(); //인터페이스 객체 할당

        //데이터 삽입
//        User user = new User(); //객체 인스턴스 생성
//        user.setName("ping");
//        user.setAge("24");
//        user.setPhoneNumber("01012345678");
//        mUserDao.setInsertUser(user);

        List<User> userList = mUserDao.getUserAll();
        //데이터 조회
        for (int i = 0; i < userList.size(); i++) {
            Log.d("TEST", userList.get(i).getName() + "\n"
                    + userList.get(i).getAge() + "\n"
                    + userList.get(i).getPhoneNumber() + "\n");
        }

        //데이터 수정
        User user2 = new User(); //객체 인스턴스 생성
        user2.setId(1); //ID값은 나만의 고유의 값이므로 다른것과 구분되어진다 -- 1번 데이터를 수정해달라고 요청한 상태
        user2.setName("pong");
        user2.setAge("22");
        user2.setPhoneNumber("01012121212");
        mUserDao.setUpdateUser(user2);

        //데이터 삭제
        User user3 = new User();
        user3.setId(2);
        mUserDao.stDeleteUser(user3);
    }
}