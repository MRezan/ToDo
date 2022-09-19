package com.example.madproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.madproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private EditText firstNumber, secondNumber;
    private TextView resultTextview;
    private Button addButton, subButton, multiplyButton,divButton,equalButton;
    private float number1, number2,result;
    private String operation;
    private ToggleButton toggle;

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


//        binding.BottomNavigation.setOnItemSelectedListener(item->{
//            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();



//            switch (item.getItemId())
//            {
//                case R.id.listid:
//                    replaceFregment(new ListFragment());
//                    break;
//                case R.id.addtaskid:
//                    replaceFregment(new AddTaskFragment());
//                    break;
//                case R.id.settingsid:
//                    replaceFregment(new SetttingsFragment());
//                    break;
//            }
//
//            return true;
//        });








    }

    private void replaceFregment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId,fragment);
        fragmentTransaction.commit();

    }
}