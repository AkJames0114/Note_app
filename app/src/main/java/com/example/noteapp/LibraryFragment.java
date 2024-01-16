package com.example.noteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.noteapp.adapter.BookNoteListAdapter;
import com.example.noteapp.adapter.NoteListAdapter;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentLibraryBinding;
import com.example.noteapp.model.BookNote;
import com.example.noteapp.model.Note;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryFragment extends BaseFragment<FragmentLibraryBinding> {

    private final ArrayList<BookNote> bookNoteArrayList = new ArrayList<>();
    private BookNoteListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new BookNoteListAdapter(bookNoteArrayList);

    }

    @Override
    protected FragmentLibraryBinding inflaterViewBinding(LayoutInflater layoutInflater, ViewGroup container, boolean toAttachParent) {
        return FragmentLibraryBinding.inflate(layoutInflater, container, toAttachParent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.bookNoteRecyclerView.setLayoutManager(gridLayoutManager);
        binding.bookNoteRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        bookNoteArrayList.clear();
        String access_token = (String) baseActivity.preferenceManager.getValue(String.class,"access_token", "");
        String bearer_token = "Bearer " + access_token;
        Call<ArrayList<BookNote>> call = baseActivity.mainApi.getBookNotes(bearer_token);

        call.enqueue(new Callback<ArrayList<BookNote>>() {
            @Override
            public void onResponse(Call<ArrayList<BookNote>> call, Response<ArrayList<BookNote>> response) {
                Log.d("Note", response.raw().toString());
                ArrayList<BookNote> bookNotes = response.body();
                bookNoteArrayList.addAll(bookNotes);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<BookNote>> call, Throwable t) {
                Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
