package com.example.kioskremote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        // 장바구니로 go
        bag.setOnClickListener{
            val intent= Intent(this, BagActivity::class.java)
            startActivity(intent)
        }
        // 결제화면으로 go
        payment.setOnClickListener{
            val intent= Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
        // 이 음식 좀 자세히 보고싶음
        food.setOnClickListener{
            val intent= Intent(this, FoodviewActivity::class.java)
            startActivity(intent)
        }
    }
}
