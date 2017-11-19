package com.example.elaza.hipotenochas;


        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

public class ListActivity extends AppCompatActivity {

    //Cada vez que hacemos algo en la interfaz no lo traemos al código
    private ListView listView;

    //lo declaramos fuera para poder acceder a él
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listView);

        //Datos a mostrar
        names = new ArrayList<String>();
        names.add("Manolo");
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Ruben");
        names.add("Manolo");
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Ruben");
        names.add("Manolo");
        names.add("Alejandro");
        names.add("Fernando");
        names.add("Ruben");


        // otra forma de hacerlo que se parece más a C#
        /*List<String> names2 = new ArrayList<String>(){{
            add("");
            add("");
            add("");
            add("");
        }};*/

        //Adaptador, la forma visual en que mostraremos nuestros datos
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);

        //Enlazamos el adaptador con nuestro List View
        //listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this,"Clicked: " + names.get(position) ,Toast.LENGTH_SHORT).show();
            }
        });

        //enlazamos con nuestro adaptador personalizado
        MyAdapter myAdapter = new MyAdapter(this,R.id.list_item,names);
        listView.setAdapter(myAdapter);

    }
}

