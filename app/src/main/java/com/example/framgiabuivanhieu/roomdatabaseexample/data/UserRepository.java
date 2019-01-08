package com.example.framgiabuivanhieu.roomdatabaseexample.data;

import com.example.framgiabuivanhieu.roomdatabaseexample.data.local.UserLocalDataSource;
import com.example.framgiabuivanhieu.roomdatabaseexample.data.model.User;
import io.reactivex.Flowable;
import java.util.List;

public class UserRepository implements UserDataSource {
    private static UserRepository sInstance;
    private UserLocalDataSource mUserLocalDataSource;

    public static UserRepository getInstance(UserLocalDataSource localDataSource) {
        if (sInstance == null) {
            sInstance = new UserRepository(localDataSource);
        }
        return sInstance;
    }

    private UserRepository(UserLocalDataSource userDataSource) {
        mUserLocalDataSource = userDataSource;
    }

    @Override
    public Flowable<User> getUserByUserId(int userId) {
        return mUserLocalDataSource.getUserByUserId(userId);
    }

    @Override
    public Flowable<List<User>> getUserByName(String userName) {
        return mUserLocalDataSource.getUserByName(userName);
    }

    @Override
    public Flowable<List<User>> getALlUser() {
        return mUserLocalDataSource.getALlUser();
    }

    @Override
    public void insertUser(User... users) {
        mUserLocalDataSource.insertUser(users);
    }

    @Override
    public void deleteUser(User user) {
        mUserLocalDataSource.deleteUser(user);
    }

    @Override
    public void deleteAllUser() {
        mUserLocalDataSource.deleteAllUser();
    }

    @Override
    public void updateUser(User... users) {
        mUserLocalDataSource.updateUser(users);
    }
}
