package com.example.framgiabuivanhieu.roomdatabaseexample.data.local;

import com.example.framgiabuivanhieu.roomdatabaseexample.data.UserDataSource;
import com.example.framgiabuivanhieu.roomdatabaseexample.data.model.User;
import io.reactivex.Flowable;
import java.util.List;

public class UserLocalDataSource implements UserDataSource {
    private static UserLocalDataSource sInstance;
    private UserDAO mUserDAO;

    public static UserLocalDataSource getInstance(UserDAO userDAO) {
        if (sInstance == null) {
            sInstance = new UserLocalDataSource(userDAO);
        }
        return sInstance;
    }

    private UserLocalDataSource(UserDAO userDAO) {
        mUserDAO = userDAO;
    }

    @Override
    public Flowable<User> getUserByUserId(int userId) {
        return mUserDAO.getUserByUserId(userId);
    }

    @Override
    public Flowable<List<User>> getUserByName(String userName) {
        return mUserDAO.getUserByName(userName);
    }

    @Override
    public Flowable<List<User>> getALlUser() {
        return mUserDAO.getALlUser();
    }

    @Override
    public void insertUser(User... users) {
        mUserDAO.insertUser(users);
    }

    @Override
    public void deleteUser(User user) {
        mUserDAO.deleteUser(user);
    }

    @Override
    public void deleteAllUser() {
        mUserDAO.deleteAllUser();
    }

    @Override
    public void updateUser(User... users) {
        mUserDAO.updateUser(users);
    }
}
