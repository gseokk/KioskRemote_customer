package com.example.kioskremote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nfc.*
import kotlinx.android.synthetic.main.activity_qr.*

class QrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        // 임시로 만든거임. QR 코드 읽으면 화면 넘어갈 수 있게 해야 됨.
        qr.setOnClickListener{
            val intent= Intent(this, MenuActivity::class.java)
            startActivity(intent)

        }
    }
}
