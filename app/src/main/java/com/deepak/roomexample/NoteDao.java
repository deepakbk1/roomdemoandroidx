package com.deepak.roomexample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by deepakpurohit on 25,August,2019
 */
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE id=:noteId")
    LiveData<Note> getNote(String noteId);

    @Update
    void update(Note note);

    @Delete
    int delete(Note note);
}
