package com.example.noteapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentProfileBinding;

import java.io.File;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    @Override
    protected FragmentProfileBinding inflaterViewBinding(LayoutInflater layoutInflater, ViewGroup container, boolean attachToParent) {
        return FragmentProfileBinding.inflate(layoutInflater, container, attachToParent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String firstname = (String) baseActivity.preferenceManager.getValue(String.class, "firstname", "Default text");
        binding.title.setText(firstname);
        String lastname = (String) baseActivity.preferenceManager.getValue(String.class, "lastname", "Default text");
        binding.title1.setText(lastname);
        String email = (String) baseActivity.preferenceManager.getValue(String.class, "email", "Default text");
        binding.title2.setText(email);

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseActivity.preferenceManager.setValue("isLogin", false);
                String filename = "my_access_token.txt";

                Log.d("ProfileFragment", getContext().getFilesDir()+"/" + filename);

                File file = new File(getContext().getFilesDir(), filename);
                if (file.delete()){
                    Log.d("ProfileFragment ", "Token file is deleted");
                }

                Intent intent=new Intent(getContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}