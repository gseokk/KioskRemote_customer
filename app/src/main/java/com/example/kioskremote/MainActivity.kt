package com.example.kioskremote

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //checkin 화면으로
        checkin_button.setOnClickListener{
            val intent=Intent(this, CheckinActivity::class.java)
            startActivity(intent)
        }
        //점주와 소통화면으로
        communication_button.setOnClickListener{
            val intent=Intent(this, CmActivity::class.java)
            startActivity(intent)
        }
    }
}
