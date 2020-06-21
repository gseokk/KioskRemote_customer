package com.example.kioskremote.view

import android.animation.ValueAnimator
import android.util.SparseBooleanArray
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskremote.R
import com.example.kioskremote.dto.FoodData

class ViewHolderMovie(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var tv_movie_title: TextView
    var iv_movie: ImageView
    var iv_movie2: ImageView
    var linearlayout: LinearLayout
    var onViewHolderItemClickListener: OnViewHolderItemClickListener? = null
    fun onBind(data: FoodData, position: Int, selectedItems: SparseBooleanArray) {
        tv_movie_title.setText(data.title)
        iv_movie.setImageResource(data.image)
        iv_movie2.setImageResource(data.image)
        changeVisibility(selectedItems[position])
    }

    private fun changeVisibility(isExpanded: Boolean) {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        val va =
            if (isExpanded) ValueAnimator.ofInt(0, 600) else ValueAnimator.ofInt(600, 0)
        // Animation이 실행되는 시간, n/1000초
        va.duration = 500
        va.addUpdateListener { animation -> // imageView의 높이 변경
            iv_movie2.layoutParams.height = animation.animatedValue as Int
            iv_movie2.requestLayout()
            // imageView가 실제로 사라지게하는 부분
            iv_movie2.visibility = if (isExpanded) View.VISIBLE else View.GONE
        }
        // Animation start
        va.start()
    }

    init {
        iv_movie = itemView.findViewById(R.id.iv_movie)
        tv_movie_title = itemView.findViewById(R.id.tv_movie_title)
        iv_movie2 = itemView.findViewById(R.id.iv_movie2)
        linearlayout = itemView.findViewById(R.id.linearlayout)
        linearlayout.setOnClickListener { onViewHolderItemClickListener!!.onViewHolderItemClick() }
    }

    companion object {
        fun setOnViewHolderItemClickListener(
            viewHolderMovie: ViewHolderMovie,
            onViewHolderItemClickListener: OnViewHolderItemClickListener?) {
            viewHolderMovie.onViewHolderItemClickListener = onViewHolderItemClickListener
        }
    }
}