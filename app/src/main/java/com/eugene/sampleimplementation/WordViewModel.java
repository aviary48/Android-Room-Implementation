package com.eugene.sampleimplementation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository; // reppo
    private LiveData<List<Word>> mAllWords;


    public WordViewModel(@NonNull Application application) {
        super(application);

        mRepository = new WordRepository(application);
        mAllWords = mRepository.getmAllWords();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }

//WRAPPER to insert data to db
    public void insert(Word word) { mRepository.insert(word); }


}
