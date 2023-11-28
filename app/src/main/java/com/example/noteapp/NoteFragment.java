package com.example.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentNotesBinding;

public class NoteFragment extends BaseFragment<FragmentNotesBinding> {

    @Override
    protected FragmentNotesBinding inflaterViewBinding(LayoutInflater layoutInflater, ViewGroup container, boolean toAttachParent) {
        return FragmentNotesBinding.inflate(layoutInflater, container, toAttachParent);
    }
}

