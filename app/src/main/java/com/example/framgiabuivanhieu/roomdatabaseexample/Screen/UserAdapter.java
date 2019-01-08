package com.example.framgiabuivanhieu.roomdatabaseexample.Screen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.framgiabuivanhieu.roomdatabaseexample.R;
import com.example.framgiabuivanhieu.roomdatabaseexample.data.model.User;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mUserList;

    public UserAdapter(Context context) {
        mContext = context;
    }

    public void updateAdapter(List<User> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mUserList.get(i));
    }

    @Override
    public int getItemCount() {
        return mUserList != null ? mUserList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewFistName;
        private TextView mTextViewLastName;
        private TextView mTextViewPlace;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewFistName = itemView.findViewById(R.id.textViewFistName);
            mTextViewLastName = itemView.findViewById(R.id.textViewLastName);
            mTextViewPlace = itemView.findViewById(R.id.textViewPlace);
        }

        private void bindData(User user) {
            mTextViewFistName.setText(user.getFirstName());
            mTextViewLastName.setText(user.getLastName());
            mTextViewPlace.setText(user.getPlace().getName());
        }
    }
}
