package com.monese.rocket.model.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.monese.rocket.model.models.Launch;
import com.monese.rocket.vo.Resource;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface DaoAccess {



    @Query("SELECt * from Launch")
    Flowable<List<Launch>> getAllLaunches();



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLaunches(List<Launch> launches);



}

