package com.example.noteapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    @Override
    protected FragmentProfileBinding inflaterViewBinding(LayoutInflater layoutInflater, ViewGroup container, boolean toAttachParent) {
        return FragmentProfileBinding.inflate(layoutInflater,container, toAttachParent);
    }
}
