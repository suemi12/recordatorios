package com.suemi_13_15.recordatorios.view.reminder.add;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suemi_13_15.recordatorios.R;

import java.util.List;

//<tipo del holder>
public class ConfigurationAdapter extends RecyclerView.Adapter<ConfigurationAdapter.ConfigurationHolder> {

    private AddReminderActivity activity;
    private List<Configuration> configurations;

    //constructor DE LA CLASE
    public ConfigurationAdapter(AddReminderActivity activity, List<Configuration> configurations) {
        this.activity = activity; // por si lo ocupo para acceder a algun recurso
        this.configurations = configurations; // elementos a amostrar

    }

    //crear vista
    @NonNull
    @Override  //logic para crear la vista de cada elemento
    public ConfigurationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//inflar la vista(de xml a View)
        View view = LayoutInflater.from(activity).inflate(R.layout.item_configuration, parent, false);
//crear instancia de holder, mandando view
        ConfigurationHolder holder = new ConfigurationHolder(view);
// se retorna holder
        return holder;
    }

    //llenar vista con datos MODELO QUE RECIBE A TRAVES DEL CONSTRUCTOR
    @Override
    public void onBindViewHolder(@NonNull ConfigurationHolder holder, int position) {
//ACCEDER AL ELEMENTO CONFIGURACION DE LA LISTA EN LA POSICION CORRESPONDIENTE
         final Configuration configuration = configurations.get(position);
//LLENADO DE LA VISTA A TRAVEZ DE EL HOLDER
        holder.title.setText(activity.getString(configuration.getTitle())); //Todo obtener strings con context.getString(configuration.getTitle())
        holder.value.setText(configuration.getValue());
        holder.icon.setImageResource(configuration.getImage());
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onClickConfiguration(configuration);

            }
        });

        //Todo agregar listener a los elementos del recycler

    }

    //informar elementos en total
    @Override
    public int getItemCount() {
        return configurations.size();
    }

    //clase interna
    class ConfigurationHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView value;
        private AppCompatImageView icon;

        //referencia de los atributos que hay en cada elemento de la lista
        ConfigurationHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_configuration_title);
            value = itemView.findViewById(R.id.txt_configuration_value);
            icon = itemView.findViewById(R.id.img_configuration_icon);


        }
    }
}
