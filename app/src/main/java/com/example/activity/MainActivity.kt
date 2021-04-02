package com.example.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    companion object {
        val KEY_TIMER_VALUE = "KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startTimer = findViewById(R.id.start_timer) as Button
        val textTimer = findViewById(R.id.text_main) as TextView
        val exitButton = findViewById(R.id.second_activity) as Button

        var countDownTimer: CountDownTimer? = null
        startTimer.setOnClickListener {
             countDownTimer = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    textTimer.text = (millisUntilFinished / 1000).toString()
                }
                override fun onFinish() {
                    textTimer.text = "Finished!"
                }
            }
            countDownTimer?.start()
        }

        fun openSecondActivity() {
            val seconds = textTimer.text.toString()
            val intent = Intent(this, MainActivitySecond::class.java)
            intent.putExtra(KEY_TIMER_VALUE, seconds)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            countDownTimer?.cancel()
            openSecondActivity()

        }
    }
}



