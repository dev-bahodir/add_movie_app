package dev.bahodir.lesson26thtask1thmovieapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.bahodir.lesson26thtask1thmovieapp.user.User

class DBHelper(var context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), DBInterface {

    companion object {
        const val DATABASE_NAME = "helper.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "users"
        const val ID = "id"
        const val MOVIE_NAME = "movie_name"
        const val MOVIE_AUTHORS = "movie_authors"
        const val ABOUT_MOVIE = "about_movie"
        const val MOVIE_DATE = "movie_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "create table $TABLE_NAME ($ID integer not null primary key autoincrement unique, $MOVIE_NAME text not null, $MOVIE_AUTHORS text not null, $ABOUT_MOVIE text not null, $MOVIE_DATE text not null)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    override fun addUser(user: User) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MOVIE_NAME, user.movieName)
        contentValues.put(MOVIE_AUTHORS, user.movieAuthors)
        contentValues.put(ABOUT_MOVIE, user.aboutMovie)
        contentValues.put(MOVIE_DATE, user.movieDate)
        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }

    override fun editUser(user: User) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, user.id)
        contentValues.put(MOVIE_NAME, user.movieName)
        contentValues.put(MOVIE_AUTHORS, user.movieAuthors)
        contentValues.put(ABOUT_MOVIE, user.aboutMovie)
        contentValues.put(MOVIE_DATE, user.movieDate)
        db.update(TABLE_NAME, contentValues, "$ID = ?", arrayOf(user.id.toString()))
        db.close()
    }

    override fun deleteUser(user: User) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$ID = ?", arrayOf(user.id.toString()))
        db.close()
    }

    override fun getUserList(): MutableList<User> {
        var list = mutableListOf<User>()
        var cursor = this.readableDatabase.rawQuery("select*from $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val users = User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
                list.add(users)
            } while (cursor.moveToNext())
        }

        return list
    }


}