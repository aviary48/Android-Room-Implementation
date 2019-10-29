package com.eugene.sampleimplementation;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    //making this class a singleton so that we only have one instance of the application running at any given time in the whole application

    private static volatile WordRoomDatabase INSTANCE;

    static WordRoomDatabase getDatabase(final Context context){

        if (INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if (INSTANCE == null){
                    // create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return  INSTANCE;
    }

}
