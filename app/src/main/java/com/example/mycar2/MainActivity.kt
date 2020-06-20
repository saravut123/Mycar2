package com.example.mycar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var Radio1: RadioButton? = null
    private var Radio2: RadioButton? = null
    private var Radio3: RadioButton? = null
    private var Radio4: RadioButton? = null
    private var Check: Button? = null
    private var Book: Button? = null
    private var Report: Button? = null
    private var Reset: Button? = null
    private var databaseHelper: DatabaseHelper? = null
    private var Show: TextView? = null
    private var num: String? = null
    private var arrayList: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this)
        Radio1 = findViewById(R.id.r1) as RadioButton
        Radio2 = findViewById(R.id.r2) as RadioButton
        Radio3 = findViewById(R.id.r3) as RadioButton
        Radio4 = findViewById(R.id.r4) as RadioButton
        Check = findViewById(R.id.check) as Button
        Book = findViewById(R.id.book) as Button
        Report = findViewById(R.id.report) as Button
        Reset = findViewById(R.id.RESET) as Button
        Show = findViewById(R.id.show) as TextView


        /*Book!!.setOnClickListener {
            if ()
            databaseHelper!!.addGradeDetail( id !!.text.toString(), DAY!!.text.toString(), MONTH!!.text.toString(),
                YEAR!!.text.toString(), TIME!!.text.toString(), MONEYINTO!!.text.toString(), MONEYOUT!!.text.toString(), DETAILS!!.text.toString())
            Radio1 !!.setText("")
            DAY!!.setText("")
            MONTH!!.setText("")
            YEAR!!.setText("")
            TIME!!.setText("")
            MONEYINTO!!.setText("")
            MONEYOUT !!.setText("")
            DETAILS!!.setText("")

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

        resetBtn!!.setOnClickListener {
            databaseHelper!!.resetDatabase()
        }*/
    }
}