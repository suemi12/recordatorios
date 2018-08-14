package com.suemi_13_15.recordatorios.view.reminder.add;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.chip.ChipGroup;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.suemi_13_15.recordatorios.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

public class AddReminderActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private FloatingActionButton btnSaveReminder;
    private Button btnDateReminder;
    private Button btnHourReminder;
    private BottomSheetDialog sheetDialog;
    private ChipGroup chipGroup;
    private TextInputLayout reminderDescription;
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";


    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Variables para obtener la hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);
    private RecyclerView recyclerConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        customToolbar();
        btnSaveReminder = findViewById(R.id.btn_save_reminder);
        btnDateReminder = findViewById(R.id.btn_add_date);
        btnHourReminder = findViewById(R.id.btn_add_hour);
        reminderDescription = findViewById(R.id.til_reminder_description);
        recyclerConfiguration = findViewById(R.id.recycler_configuration);
        chipGroup = findViewById(R.id.chipGroup);
        btnSaveReminder.setOnClickListener(this);
        btnDateReminder.setOnClickListener(this);
        btnHourReminder.setOnClickListener(this);
        reminderDescription.getEditText().addTextChangedListener(this);


        ArrayList<Configuration> configurations = new ArrayList<>();

        Configuration repetition = new Configuration();
        repetition.setTitle(R.string.title_config_repetition);
        repetition.setImage(R.drawable.ic_loop);
        configurations.add(repetition);

        Configuration sound = new Configuration();
        sound.setTitle(R.string.title_config_sound);
        sound.setImage(R.drawable.ic_soud);
        configurations.add(sound);

        Configuration vibration = new Configuration();
        vibration.setTitle(R.string.title_config_vibration);
        vibration.setImage(R.drawable.ic_vibration);
        configurations.add(vibration);

        ConfigurationAdapter adapter = new ConfigurationAdapter(this, configurations);

        recyclerConfiguration.setAdapter(adapter);
    }

    public void showDialogRepetition() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_repetition, null);
        Button btnOk = view.findViewById(R.id.btn_ok);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        NumberPicker numberPicker = view.findViewById(R.id.number_picker_repetition);
        final String[] arrayString = new String[]{"Diario","Semanal","Mensual"};
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(arrayString.length-1);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return arrayString[i];
            }
        });

        try {
            Method method = numberPicker.getClass().getDeclaredMethod("changeValueByOne", boolean.class);
            method.setAccessible(true);
            method.invoke(numberPicker, true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        sheetDialog = new BottomSheetDialog(this);
        sheetDialog.setContentView(view);
        sheetDialog.show();
    }


    //identifica cuando se le da click a un boton de la toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  // id del boton home
                onBackPressed();  // regresa a activity anterior
        }
        return super.onOptionsItemSelected(item);
    }

    private void customToolbar() {
        // action bar es el componente que contiene la toolbar
        ActionBar actionBar = getSupportActionBar();     //accedemos al action bar
        actionBar.setDisplayHomeAsUpEnabled(true);  //se habilita el icono de flecha por defecto
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_date:
                obtenerFecha();
                break;
            case R.id.btn_add_hour:
                obtenerHora();
                break;
            case R.id.btn_save_reminder:
                isValidData();
                break;
            case R.id.btn_ok:

                break;
            case R.id.btn_cancel:
                sheetDialog.dismiss();
                break;


        }

    }

    private void obtenerHora() {
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada = (hourOfDay < 10) ? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10) ? String.valueOf(CERO + minute) : String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if (hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                btnHourReminder.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
                btnHourReminder.setError(null);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }


    private void obtenerFecha() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                btnDateReminder.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                btnDateReminder.setError(null);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    private Boolean isValidData() {
        if (reminderDescription.getEditText().getText().toString().isEmpty()) {
            reminderDescription.setError(getString(R.string.error_reminder_description));
            return false;
        }
        if (btnDateReminder.getText().equals(getText(R.string.action_select_date))) {
            btnDateReminder.setError(getString(R.string.error_reminder_date));
            return false;
        }
        if (btnHourReminder.getText().equals(getText(R.string.action_select_time))) {
            btnHourReminder.setError(getString(R.string.error_reminder_hour));
            return false;
        }
        if (chipGroup.getCheckedChipId() == -1) {
            Snackbar snackbar = Snackbar
                    .make(reminderDescription, R.string.error_missing_priority, Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        clearError();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private void clearError() {
        reminderDescription.setError(null);
    }


    public void onClickConfiguration(Configuration configuration) {
        if (configuration.getTitle() == R.string.title_config_repetition) {
        showDialogRepetition();
        }



    }
}
