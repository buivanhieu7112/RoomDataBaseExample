package com.example.framgiabuivanhieu.roomdatabaseexample.Screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.framgiabuivanhieu.roomdatabaseexample.R;
import com.example.framgiabuivanhieu.roomdatabaseexample.data.UserRepository;
import com.example.framgiabuivanhieu.roomdatabaseexample.data.local.UserDatabase;
import com.example.framgiabuivanhieu.roomdatabaseexample.data.local.UserLocalDataSource;
import com.example.framgiabuivanhieu.roomdatabaseexample.data.model.Place;
import com.example.framgiabuivanhieu.roomdatabaseexample.data.model.User;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CompositeDisposable mCompositeDisposable;
    private UserAdapter mAdapter;
    private UserRepository mUserRepository;
    private List<User> mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    private void initData() {
        mCompositeDisposable = new CompositeDisposable();
        RecyclerView recyclerView = findViewById(R.id.rv_user);
        Button buttonAdd = findViewById(R.id.buttonAddUser);
        Button buttonDelete = findViewById(R.id.buttonDeleteAllUser);
        buttonAdd.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        mUserList = new ArrayList<>();
        mAdapter = new UserAdapter(this);
        recyclerView.setAdapter(mAdapter);
        UserDatabase userDatabase = UserDatabase.getInstance(this);
        mUserRepository =
                UserRepository.getInstance(UserLocalDataSource.getInstance(userDatabase.userDAO()));
    }

    private void getData() {
        Disposable disposable = mUserRepository.getALlUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        onGetAllUserSuccess(users);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onGetAllUserFailure(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void onGetAllUserFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void onGetAllUserSuccess(List<User> users) {
        mUserList.clear();
        mUserList.addAll(users);
        mAdapter.notifyDataSetChanged();
        mAdapter.updateAdapter(mUserList);
    }

    private void setData() {
        Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                User user = new User(" Van ", " Hieu " + new Random().nextInt());
                Place place = new Place("DN");
                user.setPlace(place);
                mUserRepository.insertUser(user);
                e.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {
                        // no method
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onGetAllUserFailure(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        getData();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void deleteAllData() {
        Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                mUserRepository.deleteAllUser();
                e.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {
                        // no method
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onGetAllUserFailure(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        getData();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonAddUser) {
            setData();
        }
        if (v.getId() == R.id.buttonDeleteAllUser) {
            deleteAllData();
        }
    }
}
