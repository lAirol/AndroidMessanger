package com.example.androidmessanger.chats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidmessanger.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private ArrayList<Chat> chats;

    public ChatsAdapter(ArrayList<Chat> chats){
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_rv,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.chat_name_tv.setText(chats.get(position).getChat_name());
        String userId;

        if(!chats.get(position).getUserId1().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
            userId = chats.get(position).getUserId1();
        }else{
            userId = chats.get(position).getUserId2();
        }

        FirebaseDatabase.getInstance().getReference().child("Users").child(userId)
                .child("profileImage").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        try{
                            String profileImageUrl = task.getResult().getValue().toString();
                            if(profileImageUrl.isEmpty())
                                Glide.with(holder.itemView.getContext()).load(profileImageUrl).into(holder.chat_iv);
                        }catch (Exception e){
                            Toast.makeText(holder.itemView.getContext(),"Чёт пошло не так:",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
}
