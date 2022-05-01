package com.example.ocholocos.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.ocholocos.R

class Cards:View {
    private var pint : Paint = Paint()
    var palo: String? = null
    var valor: Int? = null
    private var tam : Int = 0
    
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val atribute : TypedArray = context!!.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Carta,
            0,
            0)
        valor = atribute.getInteger(R.styleable.Carta_valueNum, 2)
        palo = atribute.getString(R.styleable.Carta_valueSim)
    }
    constructor(context: Context, valo: Int, pal : String) : super (context){
        palo = pal
        valor = valo 
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val wth = View.MeasureSpec.getSize(widthMeasureSpec)
        val hght = View.MeasureSpec.getSize(heightMeasureSpec)
        tam = Math.min(wth, hght);
        setMeasuredDimension(2*tam/3, (tam*1.75f).toInt())
    }
    override fun onDraw(canvas: Canvas?){
        super.onDraw(canvas)
        pint.color = Color.WHITE
        pint.style = Paint.Style.FILL
        val wallpaper= RectF( 0f,tam*1.75f,2f*tam/3f,0f);
        canvas?.drawRect(wallpaper,pint)

        pint.setStrokeWidth(tam/15f)
        pint.color = Color.BLACK
        pint.style = Paint.Style.STROKE

        val borders= RectF( 0f,tam*1.75f,2f*tam/3f,0f);
        canvas?.drawRect(borders,pint)

       /* if(valor != 0) {
            var paintText = Paint(Paint.ANTI_ALIAS_FLAG)
            paintText.color = Color.BLACK
            paintText.textSize = tam/4f;
            if(valor==11){
                canvas?.drawText("J", tam/15f, 4f*tam/16f, paintText)
            }
            else if(valor==12){
                canvas?.drawText("Q", tam/15f, 4f*tam/16f, paintText)
            }
            else if(valor==13){
                canvas?.drawText("K", tam/15f, 4f*tam/16f, paintText)
            }
            else{
                canvas?.drawText(valor.toString(), tam/15f, 4f*tam/16f, paintText)
            }

        }*/
        var foto : Bitmap? = null;
        if(palo=="espada" ){
            if(valor==1 ){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadaas)
            }
            else if(valor==2){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadados)
            }
            else if(valor==3){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadatres)
            }
            else if(valor==4){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadacuatro)
            }
            else if(valor==5){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadacinco)
            }
            else if(valor==6){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadaseis)
            }
            else if(valor==7){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadasiete)
            }
            else if(valor==8){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadaocho)
            }
            else if(valor==9){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadanueve)
            }
            else if(valor==10){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadadiez)
            }
            else if(valor==11){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadaonce)
            }
            else if(valor==12){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadadoce)
            }
            else if(valor==13){
                foto = BitmapFactory.decodeResource(resources,R.drawable.espadatrece)
            }
        }
        else if(palo=="trebol") {
            if (valor == 1) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.trebolas)
            } else if (valor == 2) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.treboldos)
            } else if (valor == 3) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.treboltres)
            } else if (valor == 4) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.trebolcuatro)
            } else if (valor == 5) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.trebolcinco)
            } else if (valor == 6) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.trebolseis)
            } else if (valor == 7) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.trebolsiete)
            } else if (valor == 8) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.trebolocho)
            } else if (valor == 9) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.trebolnueve)
            } else if (valor == 10) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.treboldiez)
            } else if (valor == 11) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.trebolonce)
            } else if (valor == 12) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.treboldoce)
            } else if (valor == 13) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.treboltrece)
            }
        }
        else if(palo=="diamante") {
            if (valor == 1) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.romboas)
            } else if (valor == 2) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.rombodos)
            } else if (valor == 3) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.rombotres)
            } else if (valor == 4) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.rombocuatro)
            } else if (valor == 5) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.rombocinco)
            } else if (valor == 6) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.romboseis)
            } else if (valor == 7) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.rombosiete)
            } else if (valor == 8) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.romboocho)
            } else if (valor == 9) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.rombonueve)
            } else if (valor == 10) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.rombodiez)
            } else if (valor == 11) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.romboonce)
            } else if (valor == 12) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.rombodoce)
            } else if (valor == 13) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.rombotrece)
            }
        }
        else if(palo=="corazon") {
            if (valor == 1) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazonas)
            } else if (valor == 2) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazondos)
            } else if (valor == 3) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazontres)
            } else if (valor == 4) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazoncuatro)
            } else if (valor == 5) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazoncinco)
            } else if (valor == 6) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazonseis)
            } else if (valor == 7) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazonsiete)
            } else if (valor == 8) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazonocho)
            } else if (valor == 9) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazonnueve)
            } else if (valor == 10) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazondiez)
            } else if (valor == 11) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazononce)
            } else if (valor == 12) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazondoce)
            } else if (valor == 13) {
                foto = BitmapFactory.decodeResource(resources, R.drawable.corazontrece)
            }
        }

       canvas?.drawBitmap(foto!!,null, Rect(tam/6, 2*tam/5, tam/2,4*tam/5), pint)


    }

}