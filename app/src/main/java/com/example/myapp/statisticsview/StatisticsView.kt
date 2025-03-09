package com.example.myapp.statisticsview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class StatisticsView @JvmOverloads constructor(
    context:Context,attrs:AttributeSet?=null,defStyleAttr:Int=0
): View(context, attrs ,defStyleAttr){
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val mesafe = 10f
        repeat(5){
            canvas.drawRect(width -(50f+mesafe)*(it+1)+mesafe,80f*it,width.toFloat()-(50f+mesafe)*it,height.toFloat(), Paint().apply { color = Color.WHITE })

        }
        canvas.drawRect(0f,height-10f,width.toFloat()-(50f+mesafe)*4,height.toFloat(),Paint().apply { color = Color.WHITE })

    }
}