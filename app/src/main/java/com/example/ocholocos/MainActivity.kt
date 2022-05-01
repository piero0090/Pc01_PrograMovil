package com.example.ocholocos

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.ocholocos.views.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        juego()
    }
    var deck : Deck? = null
    var lastcard : Cards? = null
    var plyr01 : Player? = null
    var plyr02 : Player? = null
    var plyr03 : Player? = null
    var plyrs = ArrayList<Player>()
    var turn : Turn? = null
    var matchPalo : Boolean = true
    var matchValor : Boolean = true
    var trece : Int = 0
    var jack : Boolean = false
    var butPasar : Button? = null
    var butRobar : Button? = null

    private fun generarDeck() {
        var palos = arrayOf("diamante","trebol", "espada","corazon");
        for(i in palos){
            for(j in 1..13){
                var card = Cards(this, j, i);
                card.setOnClickListener{
                    dejarCarta(card);
                }
                deck!!.cards.add(card);
            }
        }
        deck!!.cards.shuffle();
    }

    private fun juego() {
        deck = Deck(this);
        generarDeck();
        plyr01 = repartirCartas(Player(this, 1))
        plyr02 = repartirCartas(Player(this, 2))
        plyr03 = repartirCartas(Player(this, 3))
        sacarCartaBaraja()
        var plyrA = findViewById<LinearLayout>(R.id.jugadorA);
        var plyrB = findViewById<LinearLayout>(R.id.jugadorB);
        var areaturn = findViewById<LinearLayout>(R.id.areaTurno);
        var areamano = findViewById<LinearLayout>(R.id.areaMano);
        var areaCartaVolteada = findViewById<LinearLayout>(R.id.areaCartaVolteada);
        turn = Turn(this);

        plyrs.add(plyr01!!)
        plyrs.add(plyr02!!)
        plyrs.add(plyr03!!)

        plyrA.addView(plyr02);
        plyrB.addView(plyr03);
        areaturn.addView(turn);
        areamano.addView(deck!!);
        areaCartaVolteada.addView(lastcard);
        mostrarCartas()

        butPasar = findViewById<Button>(R.id.butPasar);
        butRobar = findViewById<Button>(R.id.butRobar);

        butPasar!!.setOnClickListener{
            if(jack==false){
                pasarTurno();
                mostrarCartas();
            }else{
                pasarTurno();
                pasarTurno();
                mostrarCartas();
                jack=false;
            }
            butPasar!!.isEnabled = false;
            butRobar!!.isEnabled= true;

        };
        butPasar!!.isEnabled = false;

        butRobar!!.setOnClickListener{
            sacarCartaJugador();
            if(trece>0){
                for(i in 1..trece*3){
                    sacarCartaJugador()
                }
                trece=0
            }
            butPasar!!.isEnabled = true
            butRobar!!.isEnabled= false
        }
    }

    private fun sacarCartaBaraja() {
        lastcard = deck!!.cards[0]
        deck!!.cards.remove(deck!!.cards[0])
    }

    private fun mostrarCartas() {
        var areaCartas = findViewById<LinearLayout>(R.id.areaCartas);
        var plyA = findViewById<LinearLayout>(R.id.jugadorA);
        var plyB = findViewById<LinearLayout>(R.id.jugadorB);
        areaCartas.removeAllViews();
        plyA.removeAllViews();
        plyB.removeAllViews();
        var isEmpty = true
        for (i in plyrs){
            if(i.id == turn!!.playerturn){
                for(j in i.ManoPlayer){
                    areaCartas.addView(j)
                }
            }else {
                if(isEmpty){
                    plyA.addView(i)
                    isEmpty =false
                }else{
                    plyB.addView(i)
                }
            }
        }
    }



    private fun dejarCarta(carta : Cards) {
        if(matchPalo or matchValor){
            var areaCartas = findViewById<LinearLayout>(R.id.areaCartas);
            var areaCartaVolteada = findViewById<LinearLayout>(R.id.areaCartaVolteada);
            if((carta.palo == lastcard!!.palo) and matchPalo ){
                if(trece>0){
                    for(i in 1..trece*3){
                        sacarCartaJugador();
                    }
                    trece=0
                }
                if(carta.valor== 13){
                    trece = trece.plus(1);
                }
                else if(carta.valor== 11){
                    jack = true
                }
                deck!!.cards.add(lastcard!!);
                for (i in plyrs){
                    if(i.id == turn!!.playerturn){
                        i.ManoPlayer.remove(carta)
                    }
                }
                matchPalo=false
                butPasar!!.isEnabled = true
                butRobar!!.isEnabled= false
                lastcard = carta
                areaCartas.removeView(carta)
                areaCartaVolteada.removeAllViews()
                areaCartaVolteada.addView(lastcard)

            }
            else if((carta.valor == lastcard!!.valor) and matchValor){
                if(trece>0){
                    if(carta.valor== 13){
                        trece = trece.plus(1)
                    }
                    else {
                        for(i in 1..trece*3){
                            sacarCartaJugador()
                        }
                        if(carta.valor== 11){
                            jack = true
                        }
                        deck!!.cards.add(lastcard!!);
                        for (i in plyrs){
                            if(i.id == turn!!.playerturn){
                                i.ManoPlayer.remove(carta);
                            }
                        }
                        matchPalo=false
                        trece=0
                    }
                }
                else {
                    if(carta.valor== 13){
                        trece = trece.plus(1);
                    }
                    else if(carta.valor== 11){
                        jack = true
                    }
                    deck!!.cards.add(lastcard!!)
                    for (i in plyrs){
                        if(i.id == turn!!.playerturn){
                            i.ManoPlayer.remove(carta)
                        }
                    }
                    matchPalo=false
                }
                butPasar!!.isEnabled = true
                butRobar!!.isEnabled= false
                lastcard = carta
                areaCartas.removeView(carta)
                areaCartaVolteada.removeAllViews()
                areaCartaVolteada.addView(lastcard)

            }

        }
        for (i in plyrs){
            if((i.id == turn!!.playerturn) and (i.ManoPlayer.size == 1)){
                Toast.makeText(applicationContext, "El jugador "+turn!!.playerturn+" va por una", Toast.LENGTH_SHORT).show()
            }
            else if((i.id == turn!!.playerturn) and (i.ManoPlayer.size == 0)){
                butPasar!!.isEnabled = false
                butRobar!!.isEnabled= false
                var areaTurno = findViewById<LinearLayout>(R.id.areaTurno)
                var ganador = Turn(this)
                ganador.playerturn = 4
                ganador.mensajeturn = "El jugador "+turn!!.playerturn+" gana!!"
                areaTurno.removeAllViews()
                areaTurno.addView(ganador)

            }
        }

    }

    private fun repartirCartas(player: Player) : Player {

        for(i in 1..7){
            player.ManoPlayer.add(deck!!.cards[i])
            deck!!.cards.remove(deck!!.cards[i])
        }
        return player;
    }
    private fun pasarTurno() {
        turn!!.Jugadornext();
        var areaturn = findViewById<LinearLayout>(R.id.areaTurno);

        areaturn.removeAllViews()
        areaturn.addView(turn)
        matchPalo=true
        matchValor=true

    }

    private fun sacarCartaJugador() {
        var areaCartas = findViewById<LinearLayout>(R.id.areaCartas)
        for (i in plyrs){
            if(i.id == turn!!.playerturn){
                i.ManoPlayer.add(deck!!.cards[0])
                areaCartas.addView(deck!!.cards[0])
                deck!!.cards.remove(deck!!.cards[0])
            }
        }


    }

}