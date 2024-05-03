package com.example.androidmessanger;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androidmessanger.bottomnav.chats.ChatsFragment;
import com.example.androidmessanger.bottomnav.new_chat.NewChatFragment;
import com.example.androidmessanger.bottomnav.profile.ProfileFragment;
import com.example.androidmessanger.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), new ChatsFragment()).commit();
        binding.bottomNav.setSelectedItemId(R.id.chats);

        Map<Integer,Fragment> fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.chats,new ChatsFragment());
        fragmentMap.put(R.id.new_chat,new NewChatFragment());
        fragmentMap.put(R.id.profile,new ProfileFragment());

        binding.bottomNav.setOnItemSelectedListener(item->{
            Fragment fragment = fragmentMap.get(item.getItemId());
            getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), fragment).commit();
            return true;
        });
    }
}
