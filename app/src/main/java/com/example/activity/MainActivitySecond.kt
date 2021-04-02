package com.example.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivitySecond : AppCompatActivity() {

    var currentSecond: Long = 0
    var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_second)

        val intent = intent
        val text = intent.getStringExtra(MainActivity.KEY_TIMER_VALUE)
        val secondTextTimer = findViewById(R.id.text_main_second) as TextView

        val stopButton = findViewById(R.id.stop_button) as Button
        val startButton = findViewById(R.id.start_button) as Button

        countDownTimer = object : CountDownTimer(text.toString().toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondTextTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                secondTextTimer.text = "Finished!"
            }
        }
        countDownTimer?.start()
        secondTextTimer.setText(text)

        stopButton.setOnClickListener {
            countDownTimer?.cancel()
            currentSecond = secondTextTimer.text.toString().toLong()
            secondTextTimer.setText(currentSecond.toString())
        }

        startButton.setOnClickListener {
            countDownTimer = object : CountDownTimer(currentSecond * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    secondTextTimer.text = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    secondTextTimer.text = "Finished!"
                }
            }
            countDownTimer?.start()
        }
    }
}