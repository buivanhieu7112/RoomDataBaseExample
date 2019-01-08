package com.example.framgiabuivanhieu.roomdatabaseexample.data;

import com.example.framgiabuivanhieu.roomdatabaseexample.data.model.User;
import io.reactivex.Flowable;
import java.util.List;

public interface UserDataSource {
    Flowable<User> getUserByUserId(int userId);

    Flowable<List<User>> getUserByName(String userName);

    Flowable<List<User>> getALlUser();

    void insertUser(User... users);

    void deleteUser(User user);

    void deleteAllUser();

    void updateUser(User... users);
}
