package com.example.ocholocos.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.ocholocos.R

class Jugador : View {
    private var paint: Paint = Paint();
    var id: Int? = 0;
    var Mano: ArrayList<Carta> = ArrayList<Carta>();
    private var size: Int = 0;

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val atribute: TypedArray = context!!.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Jugador,
            0,
            0
        )
        id = atribute.getInteger(R.styleable.Carta_valueNum, 1);
    }

    constructor(context: Context, num: Int) : super(context) {
        id = num;
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = View.MeasureSpec.getSize(widthMeasureSpec);
        val height = View.MeasureSpec.getSize(heightMeasureSpec);
        size = Math.min(width, height);

        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas);
        paint.color = Color.WHITE;
        paint.style = Paint.Style.FILL;
        val radio = size / 2;
        canvas?.drawCircle(size / 2f, size / 2f, radio.toFloat(), paint);

        paint.color = Color.BLACK;
        paint.style = Paint.Style.STROKE;
        paint.setStrokeWidth(size /38f);
        canvas?.drawCircle(size / 2f, size / 2f, radio.toFloat() - 2f, paint)

        var paintText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintText.color = Color.BLACK
        paintText.textSize = size / 11f;
        canvas?.drawText("Jugador " + id.toString(), size / 4f, 3 * size / 8f, paintText)
        if(Mano == null){
            canvas?.drawText("Numero de cartas: 0", size / 20f, 5 * size / 8f, paintText)
        }
        else {
            canvas?.drawText("Numero de cartas: "+ Mano!!.size.toString(), size / 22f, 5 * size / 8f, paintText)
        }

    }
}