package com.example.ocholocos.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.ocholocos.R

class Turno : View {
    var turnJugador : Int = 1;
    var frase : String = "Turno del jugador "
    private var size: Int = 0;

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
    fun siguienteJugador() {
        if(turnJugador<3)
        turnJugador = turnJugador.plus(1)
        else {
            turnJugador = 1
        }
    }
    fun saltarJugador(){
        siguienteJugador();
        siguienteJugador();
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = View.MeasureSpec.getSize(widthMeasureSpec);
        val height = View.MeasureSpec.getSize(heightMeasureSpec);
        size = Math.min(width, height);
        setMeasuredDimension(3*size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas);
        var paintText = Paint(Paint.ANTI_ALIAS_FLAG)
        paintText.color = Color.BLACK
        paintText.textSize = size / 3f;
        if(turnJugador<4){
            canvas?.drawText((frase+turnJugador).toString(), size / 12f, size / 2f, paintText)
        }
        else {
            canvas?.drawText((frase).toString(), size / 12f, size / 2f, paintText)
        }

    }
}