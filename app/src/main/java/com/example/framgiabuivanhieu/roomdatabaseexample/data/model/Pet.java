package com.example.framgiabuivanhieu.roomdatabaseexample.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import com.example.framgiabuivanhieu.roomdatabaseexample.data.model.User;

@Entity(foreignKeys = @ForeignKey(entity = User.class,parentColumns = "id",childColumns = "user_id"))
public class Pet {
    @PrimaryKey
    public int mPetId;

    public String mName;

    @ColumnInfo(name = "user_id")
    public int mUserId;
}
