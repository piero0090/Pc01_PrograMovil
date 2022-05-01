package com.example.ocholocos.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.ocholocos.R

class Baraja:View  {
    private var paint : Paint = Paint();
    private var size : Int = 0;
    var cartas: ArrayList<Carta> = ArrayList<Carta>();

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
        val width = View.MeasureSpec.getSize(widthMeasureSpec);
        val height = View.MeasureSpec.getSize(heightMeasureSpec);
        size = Math.min(width, height);
        setMeasuredDimension(2*size/3, (size*1.75f).toInt())
    }
    override fun onDraw(canvas: Canvas?){

        var foto : Bitmap? = BitmapFactory.decodeResource(resources,R.drawable.yugi);
        canvas?.drawBitmap(foto!!,null,Rect(0, 0, 2*size/3,(size*1.25f).toInt()), paint)
        paint.color = Color.BLACK;
        paint.style = Paint.Style.STROKE;
        paint.setStrokeWidth(size/15f);
        val borders= RectF( 0f,size*1.75f,2f*size/3f,0f);
        canvas?.drawRect(borders,paint)
    }

}