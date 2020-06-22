package com.example.kioskremote.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskremote.R
import com.example.kioskremote.adapter.RecyclerViewerAdapter
import com.example.kioskremote.dto.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.time.LocalDate.now
import java.time.LocalDateTime

class MenuActivity : AppCompatActivity() {
    var adapter: RecyclerViewerAdapter? = null
    private val TAG = "Firestore"
    val db = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        init()
        getData()
    }

    fun init() {
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        adapter = RecyclerViewerAdapter()
        recyclerView.adapter = adapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getData() {

//        // TODO "중국집" 이부분은 하드코딩!!! nfc, QR로 체크인 하면 해당 Store가 넘어가야함
//        val docRef = db.collection("store").document("중국집")
//        docRef.get().addOnSuccessListener { documentSnapshot ->
//            val store = documentSnapshot.toObject(Store::class.java)
//            Log.d(TAG, "store : $store")
//            // 출력결과 : D/APP: CITY : City(name=Beijing, state=null, country=China, isCapital=null, population=21500000, regions=[jingjinji, hebei])
//            println()
//
////            store!!.menuList!!.forEach {
////                makeViewData(R.drawable.jja, it.name!!, it.menuAbout!!)
////            }
//        }



        // TODO 여기서 이제 메뉴판 데이터 읽고 iterate하게 데이터 추가하기만 하면 된다! (현재 하드코딩)
        makeViewData(R.drawable.jja, "짜장", "짜장 설명")
        makeViewData(R.drawable.jjam, "짬뽕", "짬뽕 설명")
        makeViewData(R.drawable.tang, "탕수육", "탕수육 설명")
    }

    fun makeViewData(photo: Int, title: String, description: String) {
        val data = FoodData(photo, title, description)
        adapter!!.addItem(data)
    }
}
