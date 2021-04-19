package com.example.roomwordsample1;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

//RECOMEND THIS CLASS TO BE SEPARATE OF THE ARCHICTECTURE
public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }
    LiveData<List<Word>>getAllWords(){
        return mAllWords;
    }

    void insert(Word word){
        WordRoomDatabase.databaseWriteExecutor.execute(()->{
            mWordDao.insert(word);
        });
    }
}
