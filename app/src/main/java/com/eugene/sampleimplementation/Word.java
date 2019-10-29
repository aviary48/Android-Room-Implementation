package com.eugene.sampleimplementation;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    /*
    if you want to have unique primary keys you can annotate as below
    @PrimaryKey(autoGenerate = true)
    private int id;
    you can get more info about defining entities in room database
     */

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    public String mWord;

    public Word(@NonNull  String word) { this.mWord = word;}

    public String getmWord(){ return this.mWord;}
}
