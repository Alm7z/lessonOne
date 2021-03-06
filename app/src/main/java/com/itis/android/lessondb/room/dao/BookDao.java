package com.itis.android.lessondb.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.itis.android.lessondb.room.entity.GenreRoom;
import com.itis.android.lessondb.room.entity.RoomBook;

import java.util.Date;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Nail Shaykhraziev on 12.02.2018.
 */
@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    Maybe<List<RoomBook>> getAllBooks();

    @Query("SELECT * FROM book WHERE releaseDate > :date")
    Maybe<List<RoomBook>> getFilteredBooks(Date date);

    @Query("SELECT * FROM book where id = :bookId LIMIT 1")
    RoomBook getBookById(long bookId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBook(RoomBook roomBook);

    @Query("SELECT * from book where id = :bookId LIMIT 1")
    Single<RoomBook> loadBookById(long bookId); // room supports Single, Maybe, Flowable

    @Query("UPDATE book SET genre = :genre WHERE id = :bookId") //update example
    void updateGenreById(long bookId, GenreRoom genre);

    @Query("DELETE FROM book")
    void clearBookTable();
}
