package com.example.kioskremote.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskremote.R
import com.example.kioskremote.adapter.RecyclerViewerAdapter
import com.example.kioskremote.dto.FoodData
import com.google.firebase.firestore.FirebaseFirestore

class MenuActivity : AppCompatActivity() {
    var adapter: RecyclerViewerAdapter? = null
    private val TAG = "Firestore"
    val db = FirebaseFirestore.getInstance()

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

    fun getData() {

        // DB 연동 테스트
        db.collection("store")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        //Log.d(TAG, document.id + " => " + document.data)
                        if(document.id == "중국집"){
                            var mData = document.data["name"]
                            Log.d(TAG, mData.toString())
                            // TODO 데이터 읽어온 부분
                        }
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
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
