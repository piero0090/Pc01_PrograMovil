package com.example.ocholocos

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.ocholocos.views.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();
        juego()
    }
    var baraja : Baraja? = null;
    var cartaVolteada : Carta? = null;
    var jugador1 : Jugador? = null;
    var jugador2 : Jugador? = null;
    var jugador3 : Jugador? = null;
    var jugadores = ArrayList<Jugador>();
    var turno : Turno? = null;
    var puedeLanzarSim : Boolean = true;
    var puedeLanzarNum : Boolean = true;
    var jugarK : Int = 0;
    var jugarJ : Boolean = false;
    var butPasar : Button? = null;
    var butRobar : Button? = null;

    private fun juego() {
        baraja = Baraja(this);
        generarBaraja();
        jugador1 = repartirCartas(Jugador(this, 1));
        jugador2 = repartirCartas(Jugador(this, 2));
        jugador3 = repartirCartas(Jugador(this, 3));
        sacarCartaBaraja();
        var jugadorA = findViewById<LinearLayout>(R.id.jugadorA);
        var jugadorB = findViewById<LinearLayout>(R.id.jugadorB);
        var areaTurno = findViewById<LinearLayout>(R.id.areaTurno);
        var areaMano = findViewById<LinearLayout>(R.id.areaMano);
        var areaCartaVolteada = findViewById<LinearLayout>(R.id.areaCartaVolteada);
        turno = Turno(this);

        jugadores.add(jugador1!!);
        jugadores.add(jugador2!!);
        jugadores.add(jugador3!!);

        jugadorA.addView(jugador2);
        jugadorB.addView(jugador3);
        areaTurno.addView(turno);
        areaMano.addView(baraja!!);
        areaCartaVolteada.addView(cartaVolteada);
        mostrarCartas();

        butPasar = findViewById<Button>(R.id.butPasar);
        butRobar = findViewById<Button>(R.id.butRobar);

        butPasar!!.setOnClickListener{
            if(jugarJ==false){
                pasarTurno();
                mostrarCartas();
            }else{
                pasarTurno();
                pasarTurno();
                mostrarCartas();
                jugarJ=false;
            }
            butPasar!!.isEnabled = false;
            butRobar!!.isEnabled= true;

        };
        butPasar!!.isEnabled = false;

        butRobar!!.setOnClickListener{
            sacarCartaJugador();
            if(jugarK>0){
                for(i in 1..jugarK*3){
                    sacarCartaJugador();
                }
                jugarK=0;
            }
            butPasar!!.isEnabled = true;
            butRobar!!.isEnabled= false;
        }
    }

    private fun sacarCartaBaraja() {
        cartaVolteada = baraja!!.cartas[0];
        baraja!!.cartas.remove(baraja!!.cartas[0]);
    }

    private fun mostrarCartas() {
        var areaCartas = findViewById<LinearLayout>(R.id.areaCartas);
        var jugadorA = findViewById<LinearLayout>(R.id.jugadorA);
        var jugadorB = findViewById<LinearLayout>(R.id.jugadorB);
        areaCartas.removeAllViews();
        jugadorA.removeAllViews();
        jugadorB.removeAllViews();
        var isEmpty = true;
        for (i in jugadores){
            if(i.id == turno!!.turnJugador){
                for(j in i.Mano){
                    areaCartas.addView(j)
                }
            }else {
                if(isEmpty){
                    jugadorA.addView(i)
                    isEmpty =false
                }else{
                    jugadorB.addView(i)
                }
            }
        }
    }

    private fun generarBaraja() {
        var simbolos = arrayOf("diamante","trebol", "espada","corazon");
        for(i in simbolos){
            for(j in 1..13){
                var carta = Carta(this, j, i);
                carta.setOnClickListener{
                    dejarCarta(carta);
                }
                baraja!!.cartas.add(carta);
            }
        }
        baraja!!.cartas.shuffle();
    }

    private fun dejarCarta(carta : Carta) {
        if(puedeLanzarSim or puedeLanzarNum){
            var areaCartas = findViewById<LinearLayout>(R.id.areaCartas);
            var areaCartaVolteada = findViewById<LinearLayout>(R.id.areaCartaVolteada);
            if((carta.simbolo == cartaVolteada!!.simbolo) and puedeLanzarSim ){
                if(jugarK>0){
                    for(i in 1..jugarK*3){
                        sacarCartaJugador();
                    }
                    jugarK=0;
                }
                if(carta.numero== 13){
                    jugarK = jugarK.plus(1);
                }
                else if(carta.numero== 11){
                    jugarJ = true;
                }
                baraja!!.cartas.add(cartaVolteada!!);
                for (i in jugadores){
                    if(i.id == turno!!.turnJugador){
                        i.Mano.remove(carta);
                    }
                }
                puedeLanzarSim=false;
                butPasar!!.isEnabled = true;
                butRobar!!.isEnabled= false;
                cartaVolteada = carta;
                areaCartas.removeView(carta);
                areaCartaVolteada.removeAllViews();
                areaCartaVolteada.addView(cartaVolteada);

            }
            else if((carta.numero == cartaVolteada!!.numero) and puedeLanzarNum){
                if(jugarK>0){
                    if(carta.numero== 13){
                        jugarK = jugarK.plus(1);
                    }
                    else {
                        for(i in 1..jugarK*3){
                            sacarCartaJugador();
                        }
                        if(carta.numero== 11){
                            jugarJ = true;
                        }
                        baraja!!.cartas.add(cartaVolteada!!);
                        for (i in jugadores){
                            if(i.id == turno!!.turnJugador){
                                i.Mano.remove(carta);
                            }
                        }
                        puedeLanzarSim=false;
                        jugarK=0;
                    }
                }
                else {
                    if(carta.numero== 13){
                        jugarK = jugarK.plus(1);
                    }
                    else if(carta.numero== 11){
                        jugarJ = true;
                    }
                    baraja!!.cartas.add(cartaVolteada!!);
                    for (i in jugadores){
                        if(i.id == turno!!.turnJugador){
                            i.Mano.remove(carta);
                        }
                    }
                    puedeLanzarSim=false;
                }
                butPasar!!.isEnabled = true;
                butRobar!!.isEnabled= false;
                cartaVolteada = carta;
                areaCartas.removeView(carta);
                areaCartaVolteada.removeAllViews();
                areaCartaVolteada.addView(cartaVolteada);

            }

        }
        for (i in jugadores){
            if((i.id == turno!!.turnJugador) and (i.Mano.size == 1)){
                Toast.makeText(applicationContext, "El jugador "+turno!!.turnJugador+" va por una", Toast.LENGTH_SHORT).show()
            }
            else if((i.id == turno!!.turnJugador) and (i.Mano.size == 0)){
                butPasar!!.isEnabled = false;
                butRobar!!.isEnabled= false;
                var areaTurno = findViewById<LinearLayout>(R.id.areaTurno);
                var ganador = Turno(this);
                ganador.turnJugador = 4;
                ganador.frase = "El jugador "+turno!!.turnJugador+" gana!!"
                areaTurno.removeAllViews();
                areaTurno.addView(ganador);

            }
        }

    }

    private fun repartirCartas(jugador: Jugador) : Jugador {

        for(i in 1..7){
            jugador.Mano.add(baraja!!.cartas[i])
            baraja!!.cartas.remove(baraja!!.cartas[i]);
        }
        return jugador;
    }
    private fun pasarTurno() {
        turno!!.siguienteJugador();
        var areaTurno = findViewById<LinearLayout>(R.id.areaTurno);

        areaTurno.removeAllViews();
        areaTurno.addView(turno);
        puedeLanzarSim=true;
        puedeLanzarNum=true;

    }

    private fun sacarCartaJugador() {
        var areaCartas = findViewById<LinearLayout>(R.id.areaCartas);
        for (i in jugadores){
            if(i.id == turno!!.turnJugador){
                i.Mano.add(baraja!!.cartas[0]);
                areaCartas.addView(baraja!!.cartas[0]);
                baraja!!.cartas.remove(baraja!!.cartas[0]);
            }
        }


    }

}