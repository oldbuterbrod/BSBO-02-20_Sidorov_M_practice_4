package ru.mirea.sidorov_m.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.sidorov_m.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.poshitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    public void run() {
                        Log.d("thread", String.format("Запущен поток студентом группы %s номер по списку No %s ", "БСБО-02-20", "23"));
                        int dni = Integer.parseInt(String.valueOf(binding.editTextTextPersonName2.getText()));
                        int pari = Integer.parseInt(String.valueOf(binding.editTextTextPersonName.getText()));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.textView.setText(String.format("Среднее количество пар = %s", pari / dni));
                            }
                        });
                        Log.d("thread", "Поток выполнен!");
                    }
                }).start();
            }
        });
    }
}