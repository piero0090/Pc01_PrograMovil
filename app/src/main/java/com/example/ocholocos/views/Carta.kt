package com.example.ocholocos.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.ocholocos.R

class Carta:View {
    private var paint : Paint = Paint();
    var numero: Int? = null;
    private var size : Int = 0;
    var simbolo: String? = null;

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val atribute : TypedArray = context!!.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Carta,
            0,
            0)
        numero = atribute.getInteger(R.styleable.Carta_valueNum, 2);
        simbolo = atribute.getString(R.styleable.Carta_valueSim);
    }
    constructor(context: Context, num : Int, sim : String) : super (context){
        numero = num;
        simbolo = sim;
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = View.MeasureSpec.getSize(widthMeasureSpec);
        val height = View.MeasureSpec.getSize(heightMeasureSpec);
        size = Math.min(width, height);
        setMeasuredDimension(2*size/3, (size*1.75f).toInt())
    }
    override fun onDraw(canvas: Canvas?){
        super.onDraw(canvas);
        paint.color = Color.WHITE;
        paint.style = Paint.Style.FILL;
        val wallpaper= RectF( 0f,size*1.75f,2f*size/3f,0f);
        canvas?.drawRect(wallpaper,paint)

        paint.color = Color.BLACK;
        paint.style = Paint.Style.STROKE;
        paint.setStrokeWidth(size/15f);
        val borders= RectF( 0f,size*1.75f,2f*size/3f,0f);
        canvas?.drawRect(borders,paint)

        if(numero != 0) {
            var paintText = Paint(Paint.ANTI_ALIAS_FLAG)
            paintText.color = Color.BLACK
            paintText.textSize = size/4f;
            if(numero==11){
                canvas?.drawText("J", size/15f, 4f*size/16f, paintText)
            }
            else if(numero==12){
                canvas?.drawText("Q", size/15f, 4f*size/16f, paintText)
            }
            else if(numero==13){
                canvas?.drawText("K", size/15f, 4f*size/16f, paintText)
            }
            else{
                canvas?.drawText(numero.toString(), size/15f, 4f*size/16f, paintText)
            }

        }
        var foto : Bitmap? = null;
        if(simbolo=="espada"){
            foto = BitmapFactory.decodeResource(resources,R.drawable.espada);
        }
        else if(simbolo=="trebol"){
            foto = BitmapFactory.decodeResource(resources,R.drawable.trebol);
        }
        else if(simbolo=="corazon") {
            foto = BitmapFactory.decodeResource(resources,R.drawable.corazon);
        }
        else if(simbolo=="diamante") {
            foto = BitmapFactory.decodeResource(resources,R.drawable.diamante);
        }
        else {
            foto = BitmapFactory.decodeResource(resources,R.drawable.espada);
        }

        canvas?.drawBitmap(foto!!,null, Rect(size/6, 2*size/5, size/2,4*size/5), paint)
    }

}