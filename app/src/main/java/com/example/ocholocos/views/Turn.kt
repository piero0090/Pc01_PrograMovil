package com.example.ocholocos.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.ocholocos.R

class Turn : View {
    var playerturn : Int = 1;
    var mensajeturn : String = "Le toca al jugador "
    private var tam: Int = 0;

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val atribute: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Jugador,
            0,
            0
        )
    }

    constructor(context: Context) : super(context) {
    }
    fun Jugadornext() {
        if(playerturn<3)
            playerturn = playerturn.plus(1)
        else {
            playerturn = 1
        }
    }
    fun saltarJugador(){
        Jugadornext()
        Jugadornext()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val tnwth = View.MeasureSpec.getSize(widthMeasureSpec);
        val tnhght = View.MeasureSpec.getSize(heightMeasureSpec);
        tam = Math.min(tnwth, tnhght);
        setMeasuredDimension(3*tam, tam)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas);
        var painttxt = Paint(Paint.ANTI_ALIAS_FLAG)
        painttxt.color = Color.WHITE
        painttxt.textSize = tam / 3f
        if(playerturn<4){
            canvas?.drawText((mensajeturn+playerturn).toString(), tam / 12f, tam / 2f, painttxt)
        }
        else {
            canvas?.drawText((mensajeturn).toString(), tam / 12f, tam / 2f, painttxt)
        }

    }
}