package com.example.noteapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.noteapp.model.BookNote;
import com.example.noteapp.model.Note;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "Note_AP";
    public static Integer DB_VERSION = 6;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //It will be triggered when DB is not existed
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Note.NoteEntry.createTableQuery());
        db.execSQL(BookNote.BookNoteEntry.createTableQuery());
    }

    //It will be triggered when DB's version is different
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Note.NoteEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BookNote.BookNoteEntry.TABLE_NAME);
        onCreate(db);
    }

    public void addNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();

        //INSERT INTO TABLE(COLUMNS1, COLUMNS2) VALUES(VALUE1, VALUE2);

        String insert_sql = "INSERT INTO " + Note.NoteEntry.TABLE_NAME +
                "(" +
                Note.NoteEntry.TITLE + ", " +
                Note.NoteEntry.CONTENT +
                ") VALUES " +
                "( '" +
                note.getTitle() + "', '" +
                note.getContent() +
                "');";
        Log.d("INSERT", insert_sql);

        db.execSQL(insert_sql);
        db.close();
    }
    public void addBookNote(BookNote bookNote) {
        SQLiteDatabase db = getWritableDatabase();

        //INSERT INTO TABLE(COLUMNS1, COLUMNS2) VALUES(VALUE1, VALUE2);

        String insert_sql = "INSERT INTO " + BookNote.BookNoteEntry.TABLE_NAME +
                "(" +
                BookNote.BookNoteEntry.TITLE + ", " +
                BookNote.BookNoteEntry.CONTENT +
                ") VALUES " +
                "( '" +
                bookNote.getTitle() + "', '" +
                bookNote.getDescription() +
                "');";
        Log.d("INSERT", insert_sql);

        db.execSQL(insert_sql);
        db.close();
    }



    public void updateNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE " + Note.NoteEntry.TABLE_NAME + " SET " + Note.NoteEntry.TITLE + "='" + note.getTitle()+
                "', "+Note.NoteEntry.CONTENT+"= '"+note.getContent()+"' WHERE ID= "+note.getId();
        Log.d("SQL:", sql);
        db.execSQL(sql);
        db.close();
    }
    public void updateBookNote(BookNote bookNote) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE " + BookNote.BookNoteEntry.TABLE_NAME + " SET " + BookNote.BookNoteEntry.TITLE + "='" + bookNote.getTitle()+
                "', "+BookNote.BookNoteEntry.CONTENT+"= '"+bookNote.getDescription()+"' WHERE ID= "+bookNote.getId();
        Log.d("SQL:", sql);
        db.execSQL(sql);
        db.close();
    }


    public ArrayList<Note> getNotes() {
        ArrayList<Note> noteArrayList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        //SELECT = FROM TABLE_NAME;
        String selectQuery = "SELECT * FROM " + Note.NoteEntry.TABLE_NAME + " ORDER BY CREATED_AT DESC";


        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {

            Note note = new Note();
            note.setId(cursor.getInt(0));
            note.setTitle(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setCreatedAt(cursor.getString(3));
            noteArrayList.add(note);
        }
        cursor.close();
        db.close();

        return noteArrayList;

    }
    public ArrayList<BookNote> getBookNote() {
        ArrayList<BookNote> bookNoteArrayList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        //SELECT = FROM TABLE_NAME;
        String selectQuery = "SELECT * FROM " + BookNote.BookNoteEntry.TABLE_NAME + " ORDER BY CREATED_AT DESC";


        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {

            BookNote bookNote = new BookNote();
            bookNote.setId(cursor.getInt(0));
            bookNote.setTitle(cursor.getString(1));
            bookNote.setDescription(cursor.getString(2));
            bookNote.setCreatedAt(cursor.getString(3));
            bookNoteArrayList.add(bookNote);
        }
        cursor.close();
        db.close();

        return bookNoteArrayList;

    }

    public Note getNote(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + Note.NoteEntry.TABLE_NAME + " WHERE id=" + id + ";";
        Cursor cursor = db.rawQuery(sql , null);

        if (cursor.moveToFirst()) {
            Note note = new Note();
            note.setId(cursor.getInt(0));
            note.setTitle(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setCreatedAt(cursor.getString(3));
            return note;
        }
        return null;

    }
    public BookNote getBookNote(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + BookNote.BookNoteEntry.TABLE_NAME + " WHERE id=" + id + ";";
        Cursor cursor = db.rawQuery(sql , null);

        if (cursor.moveToFirst()) {
            BookNote bookNote = new BookNote();
            bookNote.setId(cursor.getInt(0));
            bookNote.setTitle(cursor.getString(1));
            bookNote.setDescription(cursor.getString(2));
            bookNote.setCreatedAt(cursor.getString(3));
            return bookNote;
        }
        return null;

    }

}
