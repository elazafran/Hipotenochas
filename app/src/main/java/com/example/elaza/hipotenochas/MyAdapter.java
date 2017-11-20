package com.example.elaza.hipotenochas;


import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    //creamos los atributos que necesitamos

    private Context context;
    private int layout;
    private List<String> names;

    //creamos el contructor donde pasamos los datos para que sean rellenados
    public MyAdapter(Context context, int layout, List<String> names) {
        this.context = context;
        this.layout = layout;
        this.names = names;

    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    //estos dos metodos no se usan mucho
    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    //este metodo es clave y es donde está la chicha
    //un listview es una lista de view y cada view es un item que se dibuja

    /**
     * La idea es coger el view que nos llega por defecto lo inflamos y lo devolvemos
     *
     * @param position    cada vez que itere nos llegara la posicion con distinto valor
     * @param convertView aqui la vista que vamos a personalizar
     * @param viewGroup   esto es el grupo de vistas, en nuestro caso el ListView
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //Vamos a implementar un patron para mmejorar el rendimiento de la aplicacion
        //View Holder Pattern este nos permite aumentar en productividad
        ViewHolder holder;

        if (convertView == null) {
            //del contexto sacamos el layout inflado, inflamos la vista con el layout personalizamos
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            //hay que pasarle un int que es una referencia,
            // en el lugar del null del podríamos pasar un viewgroup pero luego nos daría problemas
            //convertView = layoutInflater.inflate(R.layout.list_item,null);
            //si pasamos nuestro layout como segundo parametro lo que hacemos llamos al layout desde aquí
            convertView = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();
            //referenciamos el elemento a modificar y lo rellenamos y aqui utilizamos la propiedad con la instancia del holder
            holder.nameTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
/*
        //creamos una vista que es la qeu vamos a inflar, la copiamos
            View v = convertView;

*/


        //Ya lo hemos inflado lo siguiente es rellanarlo con nuestros datos
        //cada vez que pase por aqui va a ir cogiendo el nombre de la posicion
        //nos traemos el valor actual dependiente de la posicion
        String currentName = names.get(position);
        // esto sería si utilizaramos el viewgroup que nos viene pero al devolver un object nos ahorramos el casteo
        //currentName = (String) getItem(position);

        //ahora queremos cargar los datos pero no podemos utilizar el finviewbyid pq no estamos en un activity
        //aquie tenemos ese text view R.layout.list_item, y lo hemos inflado en esta vista "v"
        //referenciamos el elemento a modificar y lo rellenamos
        // TextView textView = (TextView) v.findViewById(R.id.textView);
        //ahora le cargamos el texto que tenemos en el textView
        //textView.setText(currentName);
       // holder.nameTextView.setText(currentName);
        return convertView;
    }

    //creamo una clase aqui pq no la vamos a utilizar en otra parte de nuestro código

    static class ViewHolder {
        //solamente tenemos una propiedad por eso solo un atributo
        private TextView nameTextView;

    }
}
