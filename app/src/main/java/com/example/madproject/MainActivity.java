package com.example.madproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;


import com.example.madproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {



    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        replaceFregment(new SetttingsFragment());

        setContentView(binding.getRoot());


        binding.BottomNavigation.setOnItemSelectedListener(item -> {

            if(item.getItemId()==R.id.listid)
            {
                replaceFregment(new ListFragment());
            }
            if(item.getItemId()==R.id. addtaskid)
            {
                replaceFregment(new AddFragment());
            }
            if(item.getItemId()==R.id.settingsid)
            {
                replaceFregment(new SetttingsFragment());
            }


            return true;

        });










    }

    private void replaceFregment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId,fragment);
        fragmentTransaction.commit();

    }
}