package com.example.androidmessanger.bottomnav.chats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidmessanger.chats.Chat;
import com.example.androidmessanger.chats.ChatsAdapter;
import com.example.androidmessanger.databinding.FragmentChatsBinding;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class ChatsFragment extends Fragment {
    private FragmentChatsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatsBinding.inflate(inflater,container,false);
        loadChats();
        return binding.getRoot();
    }

    private void  loadChats(){
        ArrayList<Chat> chats = new ArrayList<Chat>();
        chats.add(new Chat("1233","tetqwe1","12312312313","12312312313"));
        chats.add(new Chat("1232","tetqwe2","123123123132","123123123132"));
        chats.add(new Chat("1231","tetqwe3","123123123133","123123123133"));
        binding.chatsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.chatsRv.setAdapter(new ChatsAdapter(chats));
    }
}
