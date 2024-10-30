package com.example.cur_exchange

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult1: TextView
    lateinit var textResult2: TextView
    var state: Int = 1
    var op: Int = 0
    var op1: Double = 0.0
    var op2: Double = 0.0
    var unit1: Double = 1.0
    var unit2: Double = 1.0
    val curs =  arrayOf(25305.0, 1.0, 0.92, 153.0, 1377.0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult1 = findViewById<TextView>(R.id.text_result1)
        textResult2 = findViewById<TextView>(R.id.text_result2)

        val items: Array<String> = arrayOf("VND", "USD", "EUR", "YEN", "WON")
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            items)
        findViewById<Button>(R.id.button0).setOnClickListener(this)
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.buttonCE).setOnClickListener(this)
        findViewById<Button>(R.id.buttonAdd).setOnClickListener(this)
        findViewById<Button>(R.id.buttonSub).setOnClickListener(this)
        findViewById<Button>(R.id.buttonMulti).setOnClickListener(this)
        findViewById<Button>(R.id.buttonDiv).setOnClickListener(this)
        findViewById<Button>(R.id.buttonEqual).setOnClickListener(this)
        findViewById<TextView>(R.id.text_result1).setOnClickListener(this)
        findViewById<TextView>(R.id.text_result2).setOnClickListener(this)
        findViewById<Spinner>(R.id.spinner1).run {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    unit1 = curs[p2]
                    op1 = op2 * unit1 / unit2
                    textResult1.text = "$op1"
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        findViewById<Spinner>(R.id.spinner2).run {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    unit2 = curs[p2]
                    op2 = op1 * unit2 / unit1
                    textResult2.text = "$op2"
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }

    override fun onClick(p4: View?) {
        val id = p4?.id
        if (id == R.id.text_result1){
            state = 1
        }
        else if (id == R.id.button0) {
            addDigit(0)
        } else if (id == R.id.button1) {
            addDigit(1)
        } else if (id == R.id.button2) {
            addDigit(2)
        } else if (id == R.id.button3) {
            addDigit(3)
        } else if (id == R.id.button4) {
            addDigit(4)
        } else if (id == R.id.button5) {
            addDigit(5)
        } else if (id == R.id.button6) {
            addDigit(6)
        } else if (id == R.id.button7) {
            addDigit(7)
        } else if (id == R.id.button8) {
            addDigit(8)
        } else if (id == R.id.button9) {
            addDigit(9)
        }

        else if (id == R.id.text_result2){
            state = 2
        }
        else if (id == R.id.button0) {
            addDigit(0)
        } else if (id == R.id.button1) {
            addDigit(1)
        } else if (id == R.id.button2) {
            addDigit(2)
        } else if (id == R.id.button3) {
            addDigit(3)
        } else if (id == R.id.button4) {
            addDigit(4)
        } else if (id == R.id.button5) {
            addDigit(5)
        } else if (id == R.id.button6) {
            addDigit(6)
        } else if (id == R.id.button7) {
            addDigit(7)
        } else if (id == R.id.button8) {
            addDigit(8)
        } else if (id == R.id.button9) {
            addDigit(9)
        }
        else if (id ==R.id.buttonCE){
            textResult1.text = "0"
            textResult2.text = "0"
            op1 = 0.0
            op2 = 0.0
        }
    }

    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            op2 = op1 * unit2 / unit1
            textResult1.text = "$op1"
            textResult2.text = "$op2"
        } else {
            op2 = op2 * 10 + c
            op1 = op2 *unit1 / unit2
            textResult2.text = "$op2"
            textResult1.text = "$op1"
        }
    }
}