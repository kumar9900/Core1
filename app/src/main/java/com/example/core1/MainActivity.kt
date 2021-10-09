package com.example.core1

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {
    var total: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LifeCycle", "onCreate")
        val textTotal = findViewById<TextView>(R.id.total)
        val increment = 1//increment and decrement operator
        val reset = findViewById<Button>(R.id.Reset)//reset button set to val reset
        val score = findViewById<Button>(R.id.score)//score button set to val score
        val steal = findViewById<Button>(R.id.steal)//steal button set to val steal
        savedInstanceState?.let {
            total = it.getInt("Total")
            textTotal.text = total.toString()
        }
        reset.setOnClickListener {
            total = 0
            textTotal.setTextColor(updateColor(total)) // get return value from
            textTotal.text = total.toString()//send total variable to text view
            }

        score.setOnClickListener {

            total += increment//increment the total by 1
            total = clamp(total, 0, 15)
            textTotal.setTextColor(updateColor(total))
            textTotal.text = total.toString()//send total variable to text view
            if (total==15)
            {
                winningSound()
            }
        }
        steal.setOnClickListener {
            total -= increment//total decrements by one for evey click
            total = clamp(total, 0, 15)//use clamp value function to set min and max values
            textTotal.setTextColor(updateColor(total))//use update color function to change color based on value
            textTotal.text = total.toString()//send total variable to the text view
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Total", total)//save the total variable for next instance of the activity
        Log.i("LifeCycle", "SavedInstanceState $total")//show message in log cat with lifecycle tag and with msg savedInstanceState and value of total
    }
//using the function to keep the max and min values as specified above
    private fun clamp(value: Int, min: Int, max: Int): Int {
        var newValue:Int = value
        if (newValue < min)
        {
            newValue = min
        }//clamp the value for the total so that it wont go into negatives
        if (newValue > max)
        {
            newValue = max
        }//clamp value
        return newValue
    }
    private fun updateColor(value: Int): Int {
        var newColor: Int = Color.BLACK // default value

        if (value >= 10)
            newColor = Color.GREEN//if value is greater than or equal to 10 the color is changed to green
        else if (value >= 5)//else if the value is greater than or equal to 5 the color is changed to blue
            newColor = Color.BLUE
        return newColor//return the value of new color based on the if statements
    }
    private fun winningSound(){
        val mediaPlayer = MediaPlayer.create(this, R.raw.dinner_bell_triangle)
        mediaPlayer.start() // no need to call prepare(); create() does that for you
        return

    }
}