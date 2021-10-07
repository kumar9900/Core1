package com.example.core1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var total: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LifeCycle", "onCreate")
        val count = 0//initial counter value after reset
        val increment = 1//increment and decrement operator
        val reset = findViewById<Button>(R.id.Reset)//reset button set to val reset
        val score = findViewById<Button>(R.id.score)//score button set to val score
        val steal = findViewById<Button>(R.id.steal)//steal button set to val steal
        savedInstanceState?.let {
            total = it.getInt("Total")
            findViewById<TextView>(R.id.total).text = total.toString()
        }
        reset.setOnClickListener {
            findViewById<TextView>(R.id.total).text = count.toString()//reset text view string to 0
            total = 0
            if (total == 0) {
                findViewById<TextView>(R.id.total).setTextColor(Color.BLACK)
            } //if the total value is equal to 0 then set the text view id to black colour
        }
        score.setOnClickListener {
            if (total < 0) {
                total = 0
            }//clamp the value for the total so that it wont go into negatives
            if (total > 14) {
                total = 14
            }//clamp value
            total += increment//increment the total by 1
            if (total > 4) {
                findViewById<TextView>(R.id.total).setTextColor(Color.BLUE) //if the total value is greater than 4 then set the text view id to blue colour
            }
            if (total > 9) {
                findViewById<TextView>(R.id.total).setTextColor(Color.GREEN) //if the total value is greater than 9 then set the text view id to black colour
            }
            findViewById<TextView>(R.id.total).text =
                total.toString()//send total variable to text view
        }
        steal.setOnClickListener {
            total -= increment//total decrements by one for evey click
            if (total < 0) {
                total = 0// clamp the value for total so that it will not be a negative
            }
            if (total < 11) {
                findViewById<TextView>(R.id.total).setTextColor(Color.BLUE) //if the total value is greater than 4 then set the text view id to blue colour
            }
            if (total == 0 || total < 5) {
                findViewById<TextView>(R.id.total).setTextColor(Color.BLACK)//if the total value is equal to 0 or less than 4 then set the text view id to black colour
            }
            findViewById<TextView>(R.id.total).text =
                total.toString()//send total variable to the text view
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Total", total)
        Log.i("LifeCycle", "SavedInstanceState $total")
    }
}