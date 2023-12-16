package com.example.aplicacionnotenotify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnOpenDatePicker;
    TextView tvSelectedDate;
    TextView tvSelectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenDatePicker = findViewById(R.id.btnOpenDatePicker);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        tvSelectedTime = findViewById(R.id.tvSelectedTime);

        Button button = findViewById(R.id.BtnRecordatorios);
        ImageButton btnAgregar = findViewById(R.id.botonAgregar);

        TimePicker timePicker = findViewById(R.id.timePicker);

        //Colocar en español la configuracion
        Locale spanishLocale = new Locale("es");

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(spanishLocale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        Button btnOpenDatePicker = findViewById(R.id.btnOpenDatePicker);

        //Funcionalidad para mostrar fecha
        btnOpenDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // Mostrar la fecha seleccionada en el TextView
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        tvSelectedDate.setText("Fecha seleccionada: " + selectedDate);
                        tvSelectedDate.setVisibility(View.VISIBLE);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        // Funcionalidad para mostrar hora
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Mostrar la hora seleccionada en el TextView
                String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                tvSelectedTime.setText("Hora seleccionada: " + selectedTime);
                tvSelectedTime.setVisibility(View.VISIBLE);
            }
        });

        // Funcionalidad del boton del nav Recordatorios
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuRecordatorios.class);
                startActivity(intent);
            }
        });

    }
    // Funcionalidad de boton Agregar registro
    public void onBotonAgregarClick(View view) {
        Intent intent = new Intent(MainActivity.this, FormularioRegistro.class);
        startActivity(intent);
    }
}