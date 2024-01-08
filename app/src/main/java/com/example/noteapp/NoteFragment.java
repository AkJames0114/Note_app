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

import com.example.noteapp.adapter.NoteListAdapter;
import com.example.noteapp.base.BaseFragment;
import com.example.noteapp.databinding.FragmentNotesBinding;
import com.example.noteapp.model.Note;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteFragment extends BaseFragment<FragmentNotesBinding> {

    private final ArrayList<Note> noteArrayList = new ArrayList<>();
    private NoteListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new NoteListAdapter(noteArrayList);

    }

    @Override
    protected FragmentNotesBinding inflaterViewBinding(LayoutInflater layoutInflater, ViewGroup container, boolean toAttachParent) {
        return FragmentNotesBinding.inflate(layoutInflater, container, toAttachParent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        binding.noteRecyclerView.setLayoutManager(gridLayoutManager);
        binding.noteRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        noteArrayList.clear();
        //noteArrayList.addAll(baseActivity.dataBaseHelper.getNotes());
/*        Call<ArrayList<JsonObject>> call = baseActivity.mainApi.getNotes();

        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                Log.d("Note", response.raw().toString());
                ArrayList<JsonObject> jsonObjectArrayList = response.body();
                for (JsonObject j : jsonObjectArrayList) {
                    Note note = new Note();
                    note.setId(j.get("id").getAsInt());
                    note.setTitle(j.get("title").getAsString());
                    note.setContent(j.get("content").getAsString());
                    note.setCreatedAt(j.get("created_at").getAsString());
                    noteArrayList.add(note);

                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {
                Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });*/


        adapter.notifyDataSetChanged();
    }
}

