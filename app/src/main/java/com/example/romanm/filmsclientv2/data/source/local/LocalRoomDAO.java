package com.example.romanm.filmsclientv2.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.romanm.filmsclientv2.data.Item;
import com.example.romanm.filmsclientv2.pojo.filmDetail.FilmDetail;

import io.reactivex.Maybe;
import io.reactivex.Single;


@Dao
public interface LocalRoomDAO  {

    @Insert
    void saveFilmDeatail(FilmDetail film);

    @Insert
    void saveItem(Item item);

    @Query("SELECT * FROM Item WHERE id LIKE 1")
    Single<Item> checkDB();

    @Query("SELECT * FROM FilmDetail WHERE id = :id")
    Maybe<FilmDetail> getFilmInfo(int id);
}
//