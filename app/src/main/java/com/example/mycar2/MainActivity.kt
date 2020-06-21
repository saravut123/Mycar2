package com.example.mycar2

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    private var Check: Button? = null
    private var Book: Button? = null
    private var time: EditText? = null
    private var Reset: Button? = null
    private var Report: Button? = null
    private var day: EditText? = null
    private var id: EditText? = null
    private var seat: EditText? = null
    private var databaseHelper: DatabaseHelper? = null
    private var Show: TextView? = null
    private var arrayList: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this)
        time = findViewById(R.id.time) as EditText
        day  = findViewById(R.id.day) as EditText
        id  = findViewById(R.id.ID) as EditText
       seat = findViewById(R.id.seat) as EditText
        Check = findViewById(R.id.check) as Button
        Book = findViewById(R.id.book) as Button
        Report = findViewById(R.id.report) as Button
        Reset = findViewById(R.id.RESET) as Button
        Show = findViewById(R.id.show) as TextView


        Book!!.setOnClickListener {

            databaseHelper!!.addGradeDetail( id !!.text.toString(), time!!.text.toString(), day!!.text.toString(), seat!!.text.toString())
            time !!.setText("")
            day!!.setText("")
            id!!.setText("")
            seat!!.setText("")


            Toast.makeText(this@MainActivity, "Stored Successfully!", Toast.LENGTH_SHORT).show()
        }


        Report!!.setOnClickListener {
            arrayList = databaseHelper!!.allSubjectList
            Show!!.text = ""
            var ord = 1
            for (i in arrayList!!.indices) {
                Show!!.text = Show!!.text.toString() + (ord++) + ". " + arrayList!![i] + "\n"
            }
        }

        Reset!!.setOnClickListener {
            databaseHelper!!.resetDatabase() /*hi0000000*/
        }
    }

}