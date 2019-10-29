package com.eugene.sampleimplementation;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return  INSTANCE;
    }


    //call back to delete all content from the database

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        public void onOpen(SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();

        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }

}
