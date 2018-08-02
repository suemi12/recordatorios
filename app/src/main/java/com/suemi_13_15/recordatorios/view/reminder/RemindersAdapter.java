package com.suemi_13_15.recordatorios.view.reminder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suemi_13_15.recordatorios.R;
import com.suemi_13_15.recordatorios.model.Reminder;

import java.util.List;
                                                            //<tipo del holder>
public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ReminderHolder> {

    private Context context;
    private List<Reminder> reminders;

    //constructor DE LA CLASE
    public RemindersAdapter(Context context, List<Reminder> reminders) {
        this.context = context; // por si lo ocupo para acceder a algun recurso
        this.reminders = reminders; // elementos a amostrar

    }

    //crear vista
    @NonNull
    @Override  //logic para crear la vista de cada elemento
    public ReminderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflar la vista(de xml a View)
        View view = LayoutInflater.from(context).inflate(R.layout.item_reminder, parent, false);
        //crear instancia de holder, mandando view
        ReminderHolder holder = new ReminderHolder(view);
        // se retorna holder
        return holder;
    }

    //llenar vista con datos MODELO QUE RECIBE A TRAVES DEL CONSTRUCTOR
    @Override
    public void onBindViewHolder(@NonNull ReminderHolder holder, int position) {
        //ACCEDER AL ELEMENTO QUE CORRESPONDE QUE SE ESTA LLENANDO
        Reminder reminder = reminders.get(position);
        //LLENADO DE LA VISTA A TRAVEZ DE EL HOLDER
        holder.description.setText(reminder.getDescription()); //SETEAR TEXTO AL TEXTVIEW DE DESCRIPCION

    }

    //informar elementos en total
    @Override
    public int getItemCount() {
        return reminders.size();
    }

    //clase interna
    class ReminderHolder extends RecyclerView.ViewHolder {
       private TextView description;

        //referencia de los atributos que hay en cada elemento de la lista
        ReminderHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.reminder_desc);
        }
    }
}
