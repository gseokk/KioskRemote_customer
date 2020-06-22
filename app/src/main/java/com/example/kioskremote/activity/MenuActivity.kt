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
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.WriteBatch

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
        /**
         * firestore는 비동기밖에 지원을 안함
         * 따라서 콜백 리스너(addOnSuccessListener)가 콜백을 받아서 뷰를 그려주기에 더 느린 문제
         * 그래서 그냥 맨 첫번째 데이터는 static하게 넣고 뷰를 그려준 후에 나중에 콜백받을때 그려주는 방법 채택(그냥 돌아가게 보이기만...)
         */

        makeViewData(R.drawable.jja, "짜장", "짜장 설명")

        // TODO "중국집" 이부분은 하드코딩!!! nfc, QR로 체크인 하면 해당 Store가 넘어가야함
        val storeName = "신수동_중국집"
        val docRef = db.collection("store").document(storeName)

        docRef.get().addOnSuccessListener { documentSnapshot ->
            println()
            val store = documentSnapshot.toObject(Store::class.java)
            Log.d(TAG, "store : $store")

            store!!.menuList!!.forEach {
                // TODO Menu 데이터객체 내의 image(String) 을 jja <- 부분에 넣어야 함!(이미지 경로)
                makeViewData(R.drawable.jja, it.name!!, it.menuAbout!!)
                Log.d(TAG, "it : $it")
            }
        }.addOnCanceledListener {
            Log.d(TAG, "?????")
        }

        Thread.sleep(300L)
    }

    fun makeViewData(photo: Int, title: String, description: String) {
        val data = FoodData(photo, title, description)
        adapter!!.addItem(data)
    }

    /**
     * 데이터 생성하는 로직(처음 초기화할때나 데이터가 꼬이면 쓰기)
     */
    fun dataInit(){
        val menu = listOf(
            Menu("짜장면", 3500, "abc.co.kr", "검은색의 면 종류 알레르기~~~", listOf(MenuRating(3, "보통"), MenuRating(2, "별로"))),
            Menu("짬뽕", 4000, "abd.co.kr", "빨간색의 면 종류 알레르기~~~", listOf(MenuRating(5, "짬뽕이맛있네요"), MenuRating(2, "별로네요")))
        )

        val store = Store(
            "신수동_중국집",
            menu,
            listOf(Table(0, true), Table(1, true), Table(2, true), Table(3, true), Table(4, true), Table(5, true)),
            "서울시"
        )

        db.collection("store").document(store.name.toString())
            .set(store, SetOptions.merge())
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

    }
}
