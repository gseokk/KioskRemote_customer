package com.example.kioskremote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_checkin.*
import kotlinx.android.synthetic.main.activity_main.*

class CheckinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)

        //NFC read 화면으로
        nfc_button.setOnClickListener{
            val intent= Intent(this, NfcActivity::class.java)
            startActivity(intent)
        }
        //QR 코드 read 화면으로
        qr_button.setOnClickListener{
            val intent= Intent(this, QrActivity::class.java)
            startActivity(intent)
        }
    }
}
