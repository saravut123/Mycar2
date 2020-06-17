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
                    day = c.getString(c.getColumnIndex(B_DAY))
                   time = c.getString(c.getColumnIndex(B_TIME))
                    seat = c.getString(c.getColumnIndex(B_SEAT))
                    gradesArrayList.add(id + " " + day + " " + time + " " + seat)
                } while (c.moveToNext())
                Log.d("array", gradesArrayList.toString())
            }
            return gradesArrayList
        }

    init {

        Log.d("table", CREATE_TABLE_GRADES)
    }

    /*val allStudentsList: ArrayList<String>
        get() {
            val studentsArrayList = ArrayList<String>()
            var name = ""
            var lastname = ""
            val selectQuery = "SELECT  * FROM $TABLE_STUDENTS"
            val db = this.readableDatabase
            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {
                    name = c.getString(c.getColumnIndex(KEY_FIRSTNAME))
                    lastname = c.getString(c.getColumnIndex(KEY_LASTNAME))
                    studentsArrayList.add(name + " " + lastname)
                } while (c.moveToNext())
                Log.d("array", studentsArrayList.toString())
            }
            return studentsArrayList
        }

    init {

        Log.d("table", CREATE_TABLE_STUDENTS)
    } */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GRADES)
    }
    /*override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_STUDENTS)
    }*/

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GRADES'")
        onCreate(db)
    }

    /*override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_STUDENTS'")
        onCreate(db)
    }*/

    fun resetDatabase() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GRADES'")
        db.execSQL(CREATE_TABLE_GRADES)
    }

    fun addGradeDetail(id: String, day: String, time: String, seat: String): Long {
        val db = this.writableDatabase
        //val insertSQL = "INSERT INTO " + TABLE_GRADES + "(" + SUBJECT_ID + "," + SUBJECT_NAME +
        //        ", " + SEMESTER + "," + EDU_YEAR + "," + GRADE + ") VALUES (" +
        //        sid + "," + sname + "," + sem + "," + eduyear + "," + grade + ");"
        //db.execSQL(insertSQL)
        // Creating content values

        val values = ContentValues()
        values.put(B_ID, id)
        values.put(B_TIME, time)
        values.put(B_DAY, day)
        values.put(B_SEAT, seat)
        // insert row in students table

        return db.insert(TABLE_GRADES, null, values)
    }
    /*fun addStudentDetail(student: String, lastname: String): Long {
        val db = this.writableDatabase
        // Creating content values
        val values = ContentValues()
        values.put(KEY_FIRSTNAME, student)
        values.put(KEY_LASTNAME, lastname)
        // insert row in students table

        return db.insert(TABLE_STUDENTS, null, values)
    }*/

    companion object {
        var DATABASE_NAME = "grade_database"
        private val DATABASE_VERSION = 1
        private val TABLE_GRADES = "grades"
        private val B_ID = "BUSID"
        private val B_TIME = "TIME"
        private val B_DAY = "DAY"
        private val B_SEAT = "SEAT"

        private val CREATE_TABLE_GRADES = ("CREATE TABLE " + TABLE_GRADES +
                "(" + B_ID + " TEXT PRIMARY KEY," + B_TIME + " TEXT, " + B_DAY + " TEXT, " + B_SEAT + " INTEGER);")
    }

    /*companion object {

        var DATABASE_NAME = "student_database"
        private val DATABASE_VERSION = 1
        private val TABLE_STUDENTS = "students"
        private val KEY_ID = "id"
        private val KEY_FIRSTNAME = "name"
        private val KEY_LASTNAME = "lastname"

        /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/
        private val CREATE_TABLE_STUDENTS = ("CREATE TABLE "
                + TABLE_STUDENTS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT, " + KEY_LASTNAME + " TEXT );")
    } */
}