package com.example.mycar2
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val allSubjectList: ArrayList<String>
        get() {
            val gradesArrayList = ArrayList<String>()
            var sum = 0
            var id = ""
            var day = ""
            var time = ""
            var seat = ""
            val selectQuery = "SELECT * FROM $TABLE_GRADES"
            val db = this.readableDatabase
            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {
                    id = c.getString(c.getColumnIndex(B_ID))
                    time = c.getString(c.getColumnIndex(B_TIME))
                   day = c.getString(c.getColumnIndex(B_DAY))
                    seat = c.getString(c.getColumnIndex(B_SEAT))
                    gradesArrayList.add("รหัส"+id + "เวลา" + time + "วันที่" + day + "จำนวนที่นั่ง" + seat)

                } while (c.moveToNext())
                val selectsum = "SELECT time,SUM(seat) as sum_seat  FROM $TABLE_GRADES"
                val s = db.rawQuery(selectsum, null)
                if (s.moveToFirst()) {
                    do {
                        sum = s.getInt(s.getColumnIndex(B_sumseat))
                    } while (c.moveToNext())
                }
                Log.d("array", gradesArrayList.toString())
            }
            gradesArrayList.add("ที่นั่งรวม"+sum.toString())
            return gradesArrayList
        }

    init {

        Log.d("table", CREATE_TABLE_GRADES)
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GRADES)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GRADES'")
        onCreate(db)
    }



    fun resetDatabase() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GRADES'")/*55555*/
        db.execSQL(CREATE_TABLE_GRADES)
    }

    fun addGradeDetail(id: String, time: String, day: String, seat: String): Long {
        val db = this.writableDatabase


        val values = ContentValues()
        values.put(B_ID, id)
        values.put(B_TIME, time)
        values.put(B_DAY, day)
        values.put(B_SEAT, seat)


        return db.insert(TABLE_GRADES, null, values)
    }

    companion object {
        var DATABASE_NAME = "grade_database"
        private val DATABASE_VERSION = 1
        private val TABLE_GRADES = "grades"
        private val B_ID = "BUSID"
        private val B_TIME = "TIME"
        private val B_DAY = "DAY"
        private val B_SEAT = "SEAT"
        private val B_sumseat =  "sum_seat"/*5655*/
        private val CREATE_TABLE_GRADES = ("CREATE TABLE " + TABLE_GRADES +
                "(" + B_ID + " TEXT PRIMARY KEY," + B_TIME + " TEXT, " + B_DAY + " TEXT, " + B_SEAT + " INTEGER);")
    }

}