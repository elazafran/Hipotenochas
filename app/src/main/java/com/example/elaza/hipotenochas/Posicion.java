package com.example.elaza.hipotenochas;

/**
 * Created by elaza on 23/11/2017.
 */

public class Posicion {


        private int fila;
        private int columna;
        public Posicion(int fila,int columna){
            this.fila = fila;
            this.columna = columna;
        }

        public int getFila() {
            return fila;
        }

        public int getColumna() {
            return columna;
        }


}
