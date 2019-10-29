package com.eugene.sampleimplementation;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/*
The repository is not part of the library but is considered best practice to have one implemented
It is used to implement the logic for deciding whether to fetch data from a network  or use results
cached in a local db
 */
public class WordRepository {

    // member variables for the Data Access Object (DAO)

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    //constructor to get a handle of the database and initializes member variables

    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();

    }

    //wrapper to get all words
    LiveData<List<Word>> getmAllWords(){
        return  mAllWords;
    }

    //wrapper to insert words to the db
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void ,Void>{

        private WordDao mAsyncTaskDao;
        insertAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }
}
