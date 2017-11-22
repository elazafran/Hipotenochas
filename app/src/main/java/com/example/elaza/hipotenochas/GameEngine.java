package com.example.elaza.hipotenochas;

import android.content.Context;
import android.util.Log;

import com.example.elaza.hipotenochas.util.Generator;
import com.example.elaza.hipotenochas.util.PrintGrid;

/**
 * Created by elaza on 22/11/2017.
 */

public class GameEngine {

    private static GameEngine instance;

    public static final int BOMB_NUMBER = 10;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 16;

    private Context context;



    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    private GameEngine(){

    }

    /**
     * creamos el grid y lo guardamos
     * @param context
     */
    public void createGrid(Context context){
            this.context = context;
        Log.e("GameEngine","createGrid is working");
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = Generator.generate(BOMB_NUMBER,WIDTH, HEIGHT);
        PrintGrid.print(GeneratedGrid,WIDTH,HEIGHT);
        //setGrid(context,GeneratedGrid);

    }
    //TODO pintar el grid correcto
}
