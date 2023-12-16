package com.example.noteapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.noteapp.BookNoteDetailsActivity;
import com.example.noteapp.NoteDetailsActivity;
import com.example.noteapp.base.BaseAdapter;
import com.example.noteapp.base.BaseViewHolder;
import com.example.noteapp.databinding.ItemBookNoteBinding;
import com.example.noteapp.databinding.ItemBookNoteBinding;
import com.example.noteapp.model.BookNote;

import java.util.ArrayList;

public class BookNoteListAdapter extends BaseAdapter {

    private BookNote bookNote;

    private  ArrayList<BookNote> bookNoteArrayList;

    public BookNoteListAdapter(ArrayList<BookNote> bookNoteArrayList) {
        this.bookNoteArrayList = bookNoteArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookNoteBinding itemBookNoteBinding = ItemBookNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteViewHolder(itemBookNoteBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookNote booknote = bookNoteArrayList.get(holder.getAdapterPosition());
                Intent intent = new Intent(holder.itemView.getContext(), BookNoteDetailsActivity.class);
               /* intent.putExtra("id", noteArrayList.get(holder.getAdapterPosition()).getId());
               */

                intent.putExtra("book_note", bookNote);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookNoteArrayList.size();
    }

    class NoteViewHolder extends BaseViewHolder {
        ItemBookNoteBinding binding;

        public NoteViewHolder(@NonNull ItemBookNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            BookNote bookNote = bookNoteArrayList.get(position);
            binding.title.setText(bookNote.getTitle());
            binding.content.setText(bookNote.getContent());
        }
    }
}
