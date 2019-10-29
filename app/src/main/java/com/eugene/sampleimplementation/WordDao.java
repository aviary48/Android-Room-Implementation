package com.eugene.sampleimplementation;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    //method to insert one word
    @Insert
    void insert(Word word);

    //method to delete all words
    @Query("DELETE FROM word_table")
    void deleteAll();

    //method to get all words ordered alphabetically

    @Query("SELECT * from word_table ORDER BY word ASC")
    List<Word> getAlphabetizedWords();


}
