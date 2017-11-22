package com.example.elaza.hipotenochas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelecPersonajeDialogFragment.RespSeleccPersonaje{
    private GridView gridView;
    private List<String> names;
    // lo sacamos fuera para poder utilizarlo
    private MyAdapter myAdapter;
    private int counter = 0;
    private MenuItem itemClick;
    private int personaje = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        GameEngine.getInstance().createGrid(this);
        gridView = (GridView) findViewById(R.id.gridView);
        //Datos a mostrar
        names = new ArrayList<String>();
        for (int i =0;i<64;i++){
            names.add("btn"+i);
        }
        /*
        names.add("1");
        names.add("2");
        names.add("3");
        names.add("4");
        names.add("5");
        names.add("6");
        names.add("7");
        names.add("8");
        names.add("9");
        names.add("10");
        names.add("11");
        names.add("12");
        names.add("13");
        names.add("14");
        names.add("15");
        */

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Clicked: " + names.get(position) ,Toast.LENGTH_SHORT).show();
            }
        });

        //enlazamos con nuestro adaptador personalizado
        myAdapter = new MyAdapter(this,R.layout.grid_item,names);
        gridView.setAdapter(myAdapter);

        //aqui indicamos cuando queremos que se lance el Context Menu
        registerForContextMenu(gridView);
    }

    /**
     *este es para crear el menu, inflamos el layout del menu opciones
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    /**
     * este es para recoger los eventos del menu, manejamos los eventos
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //creamos un switch de cara al futuro
        switch (item.getItemId()){
            // case R.id.add_item:
                //utilizamos el preincremento para sumarle uno antes de imprimirlo
              //  this.names.add("Added number" + (++counter));
                //tenemos que aviar al adaptador que hemos incrementado en uno
                // y avisamos al adaptador porque tiene el metodo getView pq es donde llegaban
                //nuestra posiciones para uqe las renderice

                //notificamos al adaptador del cambio producido
                // this.myAdapter.notifyDataSetChanged();

                // return true;
            default:
                return true;
        }

    }

    /**
     * inflamos la vista que no vemos pero está ahí
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        //cogemos el menu que nos entra y lo casteamos
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.names.get(info.position));
        View changeBg = v;
        //TODO
        changeBg.getContext().getResources().getDrawable(R.drawable.ic_sheldom_background);


        inflater.inflate(R.menu.context_menu,menu);

    }

    /**
     *  Manejamos los eventos click en el context menu
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //los realizamos en switch por si crece la app
        switch (item.getItemId()){
            case R.id.delete_item:
                //borramos el item que hemos hecho click
                this.names.remove(info.position);

                // y notificamos los cambios
                this.myAdapter.notifyDataSetChanged();

                return true;
            case R.id.check_item:
                //cambiamos la imagen del item en el que hemos hecho click
                //TODO que cambie la imagen

                this.names.set(info.position,"cambiado");


                // y notificamos los cambios
                this.myAdapter.notifyDataSetChanged();

                return true;
            default:
                return  super.onContextItemSelected(item);

        }

    }

    /**
     * Muestra el cuadro de diálogo para la selección del personaje.
     *
     * @param item Elemento del menú asociado al método.
     */
    public void showSeleccionPersonaje(MenuItem item) {
        SelecPersonajeDialogFragment selecPersonaje = new SelecPersonajeDialogFragment();
        selecPersonaje.show(getFragmentManager(), null);
    }

    public void showInstrucciones(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.texto_instrucciones)
                .setTitle(R.string.titulo_instrucciones)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // El usuario pulsa OK.
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    @Override
    public void onPersonajeSeleccionado(int i) {
        personaje = i;
    }
}
