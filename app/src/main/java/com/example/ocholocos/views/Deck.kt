package com.example.ocholocos.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.ocholocos.R

class Deck:View  {
    private var pint : Paint = Paint()
    private var tam : Int = 0
    var cards: ArrayList<Cards> = ArrayList<Cards>()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val atribute : TypedArray = context!!.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Carta,
            0,
            0)
    }
    constructor(context: Context) : super (context){

    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val cardwidth = View.MeasureSpec.getSize(widthMeasureSpec)
        val cardheight = View.MeasureSpec.getSize(heightMeasureSpec)
        tam = Math.min(cardwidth, cardheight)
        setMeasuredDimension(2*tam/3, (tam*1.75f).toInt())
    }
    override fun onDraw(canvas: Canvas?){

        var pic : Bitmap? = BitmapFactory.decodeResource(resources,R.drawable.yugi)
        canvas?.drawBitmap(pic!!,null,Rect(0, 0, 2*tam/3,(tam*1.25f).toInt()), pint)
        pint.color = Color.BLACK
        pint.style = Paint.Style.STROKE
        pint.setStrokeWidth(tam/15f)
        val borders= RectF( 0f,tam*1.75f,2f*tam/3f,0f)
        canvas?.drawRect(borders,pint)
    }

}