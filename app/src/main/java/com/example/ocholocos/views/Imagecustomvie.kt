package com.example.ocholocos.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.ocholocos.R

class Imagecustomvie @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr : Int=0
) : AppCompatImageView(context, attrs,defStyleAttr) {

    var ratio : Float =1f
    init {
        val a= context.obtainStyledAttributes(attrs, R.styleable.Imagecustomvie)
        ratio=a.getFloat(R.styleable.Imagecustomvie_ratio,1f)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var imgwidht=measuredWidth
        var imgheight=measuredHeight

        if(imgwidht==0 && imgheight==0){
            return
        }

        if(imgwidht>0){
            imgheight=(imgwidht*ratio).toInt()
        }else if(imgheight>0){
            imgwidht=(imgheight/ratio).toInt()
        }

        setMeasuredDimension(imgwidht,imgheight)
    }
}