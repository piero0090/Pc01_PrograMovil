package com.example.ocholocos.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.ocholocos.R

class Player : View {

    private var pint: Paint = Paint()
    var ManoPlayer: ArrayList<Cards> = ArrayList<Cards>()
    var id: Int? = 0
    private var tam: Int = 0

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val atribute: TypedArray = context!!.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Jugador, 0, 0
        )
        id = atribute.getInteger(R.styleable.Carta_valueNum, 1);
    }

    constructor(context: Context, valor: Int) : super(context) {
        id = valor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val plyhght = View.MeasureSpec.getSize(heightMeasureSpec)
        val plywth = View.MeasureSpec.getSize(widthMeasureSpec)
        tam = Math.min(plywth, plyhght);

        setMeasuredDimension(tam, tam)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas) //Pintado de circulo
        pint.style = Paint.Style.FILL
        pint.color = Color.argb(255,205,60,0)
        val radio = tam / 2
        canvas?.drawCircle(tam / 2f, tam / 2f, radio.toFloat(), pint)


        pint.style = Paint.Style.STROKE
        pint.color = Color.WHITE
        pint.setStrokeWidth(tam /38f)
        canvas?.drawCircle(tam / 2f, tam / 2f, radio.toFloat() - 2f, pint)

        //Pintado de texto dentro
        var painttxt = Paint(Paint.ANTI_ALIAS_FLAG)
        painttxt.color = Color.WHITE
        painttxt.textSize = tam / 11f
        canvas?.drawText("Player " + id.toString(), tam / 3f, 3 * tam / 8f, painttxt)
        if(ManoPlayer == null){
            canvas?.drawText("Numero de cartas: 0", tam / 20f, 5 * tam / 8f, painttxt)
        }
        else {
            canvas?.drawText("Numero de cartas: "+ ManoPlayer!!.size.toString(), tam / 8f, 5 * tam / 8f, painttxt)
        }

    }
}