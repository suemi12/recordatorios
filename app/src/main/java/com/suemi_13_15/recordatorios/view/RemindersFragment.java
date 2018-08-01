package com.suemi_13_15.recordatorios.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suemi_13_15.recordatorios.R;
import com.suemi_13_15.recordatorios.model.Reminder;

import java.util.ArrayList;


public class RemindersFragment extends Fragment implements View.OnClickListener {


    private RecyclerView recyclerReminders;
    private FloatingActionButton btnAddReminder;

    public RemindersFragment() {
    }


    @Override // se ejecuta para inflar la vista del fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //se infla la vista (se convierte xml a instancia de View)
        View view = inflater.inflate(R.layout.fragment_reminders, container, false);
        //accede y se guarda los componentes del xml que se van a ocupar
        recyclerReminders = view.findViewById(R.id.recycler_reminders);
        btnAddReminder = view.findViewById(R.id.btn_add_reminder);
        btnAddReminder.setOnClickListener(this);
        return view; // retorno de la vista inflada

    }

    @Override //cuando la vista ya esra disponible para usarse
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Reminder> reminders = new ArrayList<>();
        Reminder r1= new Reminder();
        r1.setId(1);
        r1.setDescription("pagar renta");

        Reminder r2 = new Reminder();
        r2.setId(2);
        r2.setDescription("Ir a correr");

        Reminder r3 = new Reminder();
        r3.setId(3);
        r3.setDescription("preguntar departamento");

        reminders.add(r1);
        reminders.add(r2);
        reminders.add(r3);

        RemindersAdapters adapters = new RemindersAdapters(getContext(), reminders);
        recyclerReminders.setAdapter(adapters);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_reminder:
                Intent intent = new Intent(getContext(), AddReminderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
